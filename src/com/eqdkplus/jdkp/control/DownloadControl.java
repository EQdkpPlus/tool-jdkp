/*
 * Project:	EQdkp-Plus jdkp
 * License:	Creative Commons - Attribution Non-Commercial No Derivatives 3.0 Unported
 * Link:	http://creativecommons.org/licenses/by-nc-nd/3.0/
 *
 * Began:	2010
 * Date:	$Date$
 *
 * Author:	$Author$
 * Copyright:	2010-2011 kirax (kirax@eqdkp-plus.com)
 * Link:	http://eqdkp-plus.com
 * Package:	jdkp
 * Version:	$Rev$
 *
 * $Id$
 */

package com.eqdkplus.jdkp.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.net.ssl.SSLHandshakeException;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.eqdkplus.jdkp.gui.AuthDialog;
import com.eqdkplus.jdkp.gui.Gui;
import com.eqdkplus.jdkp.gui.Messages;
import com.eqdkplus.jdkp.parse.Parser;
import com.eqdkplus.jdkp.parse.XSD;
import com.eqdkplus.jdkp.parse.bind.Response;
import com.eqdkplus.jdkp.prf.Profile;
import com.eqdkplus.jdkp.rest.EqdkpRESTClient;

public class DownloadControl extends Control<Void, Object> {

    private static final int DEBUG_LEVEL = 0;

    private static final String HTTP = "http"; //$NON-NLS-1$
    private static final String HTTPS = "https"; //$NON-NLS-1$
    private static final String JDKP_LOG = "jdkp.log"; //$NON-NLS-1$

    private static final String[] SUGG = new String[] { "/dkp", "/eqdkp" }; //$NON-NLS-1$ //$NON-NLS-2$

    private final Profile profile;
    private final Gui gui;
    private int contentLength;
    private String plainPassword;
    private String encryptedPassword;
    private FileWriter logFile;
    private EqdkpRESTClient rest;
    private String sid;

    public DownloadControl(Profile profile, Gui gui, String plainPassword) {
	this.profile = profile;
	this.gui = gui;
	this.contentLength = -1;
	this.pendingErr = null;
	this.plainPassword = plainPassword;
	this.rest = null;
	this.sid = Control.EMPTY_STRING;
    }

    private HttpURLConnection connect(URL url) throws IOException, EQDKPException {
	return connectRec(null, url, -1);
    }

    private HttpURLConnection connectRec(HttpURLConnection conn, URL url, int suggIdx) throws IOException,
	    EQDKPException {
	try {
	    URL newUrl = null;
	    if (suggIdx > -1) {
		newUrl = new URL(url.getProtocol(), url.getHost(), SUGG[suggIdx % SUGG.length] + url.getFile());
	    }
	    if (newUrl != null) {
		conn = (HttpURLConnection) newUrl.openConnection();
	    } else {
		conn = (HttpURLConnection) url.openConnection();
	    }
	    conn.setConnectTimeout(profile.getConnectionTimeout());
	    conn.connect();
	    contentLength = conn.getContentLength();
	    // test connectivity
	    conn.getInputStream();
	    return conn;
	} catch (FileNotFoundException e) {
	    // search in suggested paths
	    if (suggIdx < SUGG.length - 1) {
		suggIdx++;
		publish(String
			.format(Messages.getString("DownloadControl.connectingTo"), new URL(url.getProtocol(), url.getHost(), SUGG[suggIdx] + url.getFile()))); //$NON-NLS-1$
		return connectRec(conn, url, suggIdx);
	    }
	    // search in ./
	    if (suggIdx < 2 * SUGG.length) {
		URL newUrl = new URL(url.getProtocol(), url.getHost(), "/api.php"); //$NON-NLS-1$
		publish(String.format(Messages.getString("DownloadControl.connectingTo"), newUrl)); //$NON-NLS-1$
		return connectRec(conn, newUrl, ++suggIdx);
	    } else {
		throw new EQDKPException(Messages.getString("DownloadControl.noEQDKPFound")); //$NON-NLS-1$
	    }
	} catch (SSLHandshakeException e) {
	    // if the user "accidently" entered https instead of http
	    if (!url.getProtocol().equals(HTTP)) {
		URL newUrl = new URL(HTTP, url.getHost(), url.getFile());
		publish(String.format(Messages.getString("DownloadControl.connectingTo"), newUrl)); //$NON-NLS-1$
		return connectRec(conn, newUrl, suggIdx);
	    }
	} catch (ConnectException e) {
	    if (url.getProtocol().equals(HTTPS)) {
		URL newUrl = new URL(HTTP, url.getHost(), url.getFile());
		publish(String.format(Messages.getString("DownloadControl.connectingTo"), newUrl)); //$NON-NLS-1$
		return connectRec(conn, newUrl, suggIdx);
	    } else {
		throw e;
	    }
	} catch (IOException e) {
	    publish(FAILED);
	    publish(false);
	    throw e;
	}
	throw new IllegalStateException();
    }

    private void log(String str) throws IOException {
	logFile.write(str + Control.NEW_LINE);
    }

    private void initializeLog() throws IOException {
	String logFileName = profile.getLocalPath().getAbsolutePath();
	logFile = new FileWriter(logFileName.substring(0, logFileName.lastIndexOf(profile.getLocalPath().getName()))
		+ JDKP_LOG);
    }

    public void download() throws IOException {
	initializeLog();
	log("JDKP download log file"); //$NON-NLS-1$
	log("System time: " + DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL).format(new Date())); //$NON-NLS-1$
	log("Profile:"); //$NON-NLS-1$
	log(profile.getInfo());
	URL url = profile.getEqdkpURL();
	publish(LOADING);
	publish(String.format(Messages.getString("DownloadControl.connectingTo"), url)); //$NON-NLS-1$
	publish(false);
	HttpURLConnection conn = null;
	try {
	    conn = connect(url);
	} catch (Exception e) {
	    publish(FAILED);
	    pendingErr = e;
	    return;
	}

	publish(CHECKED);

	if (!conn.getURL().equals(url)) {
	    publish(LOADING);
	    int option = JOptionPane.showConfirmDialog(gui,
		    String.format(Messages.getString("DownloadControl.invalidURLsugg"), conn.getURL())); //$NON-NLS-1$
	    if (option == JOptionPane.YES_OPTION) {
		url = conn.getURL();
		profile.setEqdkpURL(url);
		profile.save(Control.PROFILE_PATH, true);
	    } else {
		publish(FAILED);
		publish(Messages.getString("DownloadControl.abortByUser")); //$NON-NLS-1$
		return;
	    }
	}

	publish(LOADING);

	publish(String.format(Messages.getString("DownloadControl.downloadFrom"), url.getHost())); //$NON-NLS-1$

	rest = new EqdkpRESTClient(profile);

	url = new URL(url.toString() + "?function=points"); //$NON-NLS-1$

	String dataString = null;
	try {
	    dataString = downloadData(url);
	} catch (Exception e) {
	    publish(FAILED);
	    pendingErr = e;
	    return;
	}
	assert dataString != null;

	publish(Messages.getString("DownloadControl.parsing")); //$NON-NLS-1$

	Response data = null;

	try {
	    data = parseData(dataString);
	} catch (UnmarshalException e) {
	    publish(FAILED);
	    if (e.getLinkedException() instanceof SAXParseException) {
		SAXParseException spe = (SAXParseException) (e.getLinkedException());
		SAXExceptionWrap f;
		if (dataString.length() >= spe.getColumnNumber() + 120 && spe.getColumnNumber() >= 120) {
		    f = new SAXExceptionWrap(e, Control.NEW_LINE
			    + String.format(Messages.getString("DownloadControl.errorData"),
				    dataString.substring(spe.getColumnNumber() - 120, spe.getColumnNumber() + 20),
				    dataString.substring(spe.getColumnNumber() - 20, spe.getColumnNumber() + 120)));
		} else {
		    f = new SAXExceptionWrap(e, Control.NEW_LINE
			    + String.format(Messages.getString("DownloadControl.errorDataSingle"), dataString));
		}
		pendingErr = f;
	    } else {
		pendingErr = e;
	    }
	    return;
	} catch (Exception e) {
	    publish(FAILED);
	    pendingErr = e;
	    return;
	}
	assert data != null;

	

	if (data.getStatus().equals("0") && data.getError().equals("access denied")) { //$NON-NLS-1$ //$NON-NLS-2$

	    publish(Messages.getString("DownloadControl.authenticating")); //$NON-NLS-1$
	    
	    if (!profile.getUsername().equals(Control.EMPTY_STRING)
		    && profile.getPassword().equals(Control.EMPTY_STRING)) {
		// <obsolete> if (!plainPassword.equals(Control.EMPTY_STRING)) {
		try {
		    profile.setPassword(getEncryptedPassword(profile.getEncoding(), rest, profile.getUsername(),
			    plainPassword));
		} catch (Exception e) {
		    publish(FAILED);
		    pendingErr = e;
		    return;
		}
	    }
	    try {
		sid = rest.login().getV1();
		int round = 0;
		while (sid.equals("0") && round < 3) {
		    // login info is wrong
		    profile.setPassword(Control.EMPTY_STRING);
		    rest = rest == null ? new EqdkpRESTClient(profile) : rest;
		    // while (round < 3 && sid.equals(Control.EMPTY_STRING)) {
		    boolean a = false;
		    try {
			a = getAuth(rest, url);
		    } catch (Exception e) {
			publish(FAILED);
			pendingErr = e;
			return;
		    }
		    if (!a) {
			publish(FAILED);
			publish(Messages.getString("DownloadControl.aborted")); //$NON-NLS-1$
			return;
		    }
		    try {
			sid = rest.login().getV1();
		    } catch (Exception e) {
			publish(FAILED);
			pendingErr = e;
			return;
		    }
		    round++;
		}
		gui.setUsername(profile.getUsername());
		if (!profile.getPassword().equals(Control.EMPTY_STRING)) {
		    gui.setPassword();
		}
		if (sid.equals(Control.EMPTY_STRING)) {
		    profile.setPassword(Control.EMPTY_STRING);
		    publish(Messages.getString("DownloadControl.authFailed")); //$NON-NLS-1$
		    publish(FAILED);
		    return;
		}
		//	    url = new URL(url.toString() + "&s=" + sid); //$NON-NLS-1$
	    } catch (Exception e) {
		publish(FAILED);
		pendingErr = e;
		return;
	    }
	    // }

	    if (!sid.equals(Control.EMPTY_STRING)) {
		url = new URL(url.toString() + "&s=" + sid); //$NON-NLS-1$
	    }
	    // } else {
	    //		 //$NON-NLS-1$
	    // }
	    // conn = (HttpURLConnection) url.openConnection();
	    // int rcode = conn.getResponseCode();
	    // conn.disconnect();
	    /*
	     * **************************** access determination NOT via
	     * response code!! if (rcode == 403) { assert
	     * sid.equals(Control.EMPTY_STRING); // login info is wrong
	     * profile.setPassword(Control.EMPTY_STRING); rest = rest == null ?
	     * new EqdkpRESTClient(profile) : rest; int round = 0; while (round
	     * < 3 && sid.equals(Control.EMPTY_STRING)) { boolean a = false; try
	     * { a = getAuth(rest, url); } catch (Exception e) {
	     * publish(FAILED); pendingErr = e; return; } if (!a) {
	     * publish(FAILED);
	     * publish(Messages.getString("DownloadControl.aborted"));
	     * //$NON-NLS-1$ return; } try { sid = rest.login().getV1(); } catch
	     * (Exception e) { publish(FAILED); pendingErr = e; return; }
	     * round++; } gui.setUsername(profile.getUsername()); if
	     * (!profile.getPassword().equals(Control.EMPTY_STRING)) {
	     * gui.setPassword(); } if (sid.equals(Control.EMPTY_STRING)) {
	     * profile.setPassword(Control.EMPTY_STRING);
	     * publish(Messages.getString("DownloadControl.authFailed"));
	     * //$NON-NLS-1$ publish(FAILED); return; } url = new
	     * URL(url.toString() + "&s=" + sid); //$NON-NLS-1$ } if
	     * (gui.getLastUsedProfile() != null) {
	     * gui.getLastUsedProfile().setPassword(profile.getPassword());
	     * gui.getLastUsedProfile().save(Control.PROFILE_PATH, true); }
	     * profile.save(Control.PROFILE_PATH, true);
	     * publish(String.format(Messages
	     * .getString("DownloadControl.downloadFrom"), url.getHost()));
	     * //$NON-NLS-1$ if (contentLength != -1) { publish(true); } conn =
	     * (HttpURLConnection) url.openConnection(); rcode =
	     * conn.getResponseCode(); conn.disconnect(); if (rcode == 403) {
	     * publish(Messages.getString("DownloadControl.authFailed"));
	     * //$NON-NLS-1$ publish(FAILED); pendingErr = new
	     * EQDKPException(Messages
	     * .getString("DownloadControl.notApprPerm")); //$NON-NLS-1$ return;
	     * } **********************************************
	     */
	    publish(String.format(Messages.getString("DownloadControl.downloadFrom"), url.getHost())); //$NON-NLS-1$

	    try {
		dataString = downloadData(url);
	    } catch (Exception e) {
		publish(FAILED);
		pendingErr = e;
		return;
	    }

	    publish(LOADING);
	    publish(Messages.getString("DownloadControl.parsing")); //$NON-NLS-1$

	    data = null;

	    try {
		data = parseData(dataString);
	    } catch (UnmarshalException e) {
		publish(FAILED);
		if (e.getLinkedException() instanceof SAXParseException) {
		    SAXParseException spe = (SAXParseException) (e.getLinkedException());
		    SAXExceptionWrap f = new SAXExceptionWrap(e, Control.NEW_LINE
			    + String.format(Messages.getString("DownloadControl.errorData"),
				    dataString.substring(spe.getColumnNumber() - 120, spe.getColumnNumber() + 20),
				    dataString.substring(spe.getColumnNumber() - 20, spe.getColumnNumber() + 120)));
		    pendingErr = f;
		} else {
		    pendingErr = e;
		}
		return;
	    } catch (Exception e) {
		publish(FAILED);
		pendingErr = e;
		return;
	    }

	    if (data.getStatus().equals("0") && data.getError().equals("access denied")) { //$NON-NLS-1$ //$NON-NLS-2$
		publish(Messages.getString("DownloadControl.authFailed")); //$NON-NLS-1$
		publish(FAILED);
		pendingErr = new EQDKPException(Messages.getString("DownloadControl.notApprPerm")); //$NON-NLS-1$
		return;
	    }
	}

	if (gui.getLastUsedProfile() != null) {
	    gui.getLastUsedProfile().setPassword(profile.getPassword());
	    gui.getLastUsedProfile().save(Control.PROFILE_PATH, true);
	}
	profile.save(Control.PROFILE_PATH, true);

	// if (contentLength != -1) {
	// publish(true);
	// }
	// conn = (HttpURLConnection) url.openConnection();
	// rcode = conn.getResponseCode();
	// conn.disconnect();

	// br = new BufferedReader(new InputStreamReader(url.openStream(),
	// profile.getEncoding()));
	// result = new StringBuilder();
	// line = br.readLine();
	// bytesRead = 0;
	// while (line != null) {
	// result.append(line);
	// bytesRead += line.length();
	// publish(bytesRead);
	// line = br.readLine();
	// }
	// br.close();

	publish(Messages.getString("DownloadControl.saving")); //$NON-NLS-1$
	FileOutputStream fos = new FileOutputStream(profile.getLocalPath());
	OutputStreamWriter osw = new OutputStreamWriter(fos, profile.getEncoding());
	osw.write(profile.getGameInterface().format(data));
	osw.close();
	if (profile.getExecutePath() != null) {
	    publish(Messages.getString("DownloadControl.startingGame")); //$NON-NLS-1$
	    if (!execCmd()) {
		publish(FAILED);
		return;
	    }
	}
	publish(String.format(Messages.getString("DownloadControl.downloadSuccFrom"), profile.getEqdkpURL())); //$NON-NLS-1$
	publish(CHECKED);
    }

    private boolean execCmd() {
	String cmd = profile.getExecutePath().getAbsolutePath();
	ProcessBuilder pb = new ProcessBuilder(cmd);
	try {
	    pb.start();
	} catch (IOException e) {
	    if (System.getProperty("os.name").startsWith(Control.WINDOWS)) { //$NON-NLS-1$
		pb = new ProcessBuilder(new String[] { "cmd.exe", "/C", profile.getExecutePath().getAbsolutePath() }); //$NON-NLS-1$ //$NON-NLS-2$
		try {
		    pb.start();
		} catch (IOException f) {
		    pendingErr = f;
		    return false;
		}
	    } else {
		pendingErr = e;
		return false;
	    }
	}
	return true;
    }

    @SuppressWarnings("unused")
    public static String getEncryptedPassword(String encoding, EqdkpRESTClient rest, String username,
	    String plainPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException, JAXBException,
	    SAXException, EQDKPException {
	MessageDigest md = MessageDigest.getInstance("SHA-512"); //$NON-NLS-1$
	if (DEBUG_LEVEL == 1) {
	    System.out.println(username);
	}
	String salt = rest.getSalt(username);
	if (DEBUG_LEVEL == 1) {
	    System.out.println(salt);
	}
	byte[] mb = md.digest((salt + plainPassword).getBytes(encoding));
	salt = null;
	StringBuilder password = new StringBuilder();
	for (byte b : mb) {
	    String s = Integer.toHexString(b);
	    while (s.length() < 2) {
		s = "0" + s; //$NON-NLS-1$
	    }
	    s = s.substring(s.length() - 2);
	    password.append(s);
	}
	if (DEBUG_LEVEL == 1) {
	    System.out.println(password.toString());
	}
	return password.toString();
    }

    private boolean getAuth(EqdkpRESTClient rest, URL url) throws NoSuchAlgorithmException,
	    UnsupportedEncodingException, JAXBException, SAXException, EQDKPException {
	AuthDialog ad = new AuthDialog(gui, url.getHost(), profile.getUsername());
	ad.setVisible(true);
	String user = ad.getUsername();
	String pw = ad.getPassword();
	// Pair<String, String> authInfo = ad.getAuthentication(); unsafe
	if (user.equals(Control.EMPTY_STRING) || pw.equals(Control.EMPTY_STRING)) {
	    user = null;
	    pw = null;
	    return false;
	}
	profile.setUsername(user);
	profile.setPassword(getEncryptedPassword(profile.getEncoding(), rest, user, pw));

	ad.dispose();
	user = null;
	pw = null;
	return true;
    }

    @Override
    protected void done() {
	if (pendingErr != null) {
	    publish(false);
	    publish(FAILED);
	    publish(Messages.getString("DownloadControl.downloadFailed")); //$NON-NLS-1$
	    if (pendingErr instanceof SocketException || pendingErr instanceof SocketTimeoutException
		    || pendingErr instanceof UnknownHostException) {
		errorMessage(String.format(
			Messages.getString("DownloadControl.connNotEstablished"), profile.getEqdkpURL().toString()) //$NON-NLS-1$
			+ pendingErr.toString());
	    } else if (pendingErr instanceof UnmarshalException) {
		errorMessage(String.format(Messages.getString("DownloadControl.unexpXMLFormat"), pendingErr.toString())); //$NON-NLS-1$
	    } else if (pendingErr instanceof SAXExceptionWrap) {
		errorMessage(String.format(Messages.getString("DownloadControl.unexpXMLFormat"), pendingErr.toString()));
	    } else if (pendingErr instanceof SAXException || pendingErr instanceof JAXBException) {
		errorMessage(String.format(Messages.getString("DownloadControl.XMLExc"), pendingErr.toString())); //$NON-NLS-1$
	    } else if (pendingErr instanceof EQDKPException) {
		errorMessage(String.format(
			Messages.getString("DownloadControl.EQDKPExc"), pendingErr.getLocalizedMessage())); //$NON-NLS-1$
	    } else if (pendingErr instanceof IOException) {
		errorMessage(String.format(
			Messages.getString("DownloadControl.IOExc"), pendingErr.getLocalizedMessage())); //$NON-NLS-1$
	    } else {
		pendingErr.printStackTrace();
	    }
	}
	if (rest != null) {
	    try {
		rest.logout(sid);
		rest.close();
	    } catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (EQDKPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	try {
	    profile.save(Control.PROFILE_PATH, true);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	try {
	    logFile.close();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private void errorMessage(String str) {
	try {
	    log(str);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	JOptionPane.showMessageDialog(gui, str, Messages.getString("Gui.error"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
    }

    @Override
    protected void process(List<Object> states) {
	for (Object o : states) {
	    if (o instanceof Icon) {
		gui.getJob().setIcon((Icon) o);
	    } else if (o instanceof String) {
		gui.getJob().setDescription((String) o);
		try {
		    log((String) o);
		} catch (IOException e) {
		    // well ... what a tragedy ;)
		}
	    } else if (o instanceof Boolean) {
		boolean b = (Boolean) o;
		if (b) {
		    gui.getJob().getProgressBar().setMaximum(contentLength);
		}
		gui.getJob().getProgressBar().setValue(0);
		gui.getJob().getProgressBar().setVisible((Boolean) o);
	    } else if (o instanceof Integer) {
		gui.getJob().getProgressBar().setValue((Integer) o);
	    } else {
		throw new AssertionError();
	    }
	}
	gui.getJob().setVisible(true);
	gui.pack();
    }

    @Override
    protected Void doWork() throws Throwable {
	download();
	return null;
    }

    private String downloadData(URL url) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), profile.getEncoding()));
	StringBuilder result = new StringBuilder();
	String line = br.readLine();
	int bytesRead = 0;
	while (line != null) {
	    result.append(line);
	    bytesRead += line.length();
	    publish(bytesRead);
	    line = br.readLine();
	}
	br.close();
	return result.toString();
    }

    private Response parseData(String str) throws Exception {
	Response data = null;

	data = Parser.parse(str.toString(), XSD.getDKPXSDURL(profile.getEqdkpURL()), Response.class);
	assert data != null;
	return data;
    }
}