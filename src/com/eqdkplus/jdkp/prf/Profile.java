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

package com.eqdkplus.jdkp.prf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Comparator;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import com.eqdkplus.jdkp.control.Control;
import com.eqdkplus.jdkp.control.DownloadControl;
import com.eqdkplus.jdkp.control.EQDKPException;
import com.eqdkplus.jdkp.gui.Messages;
import com.eqdkplus.jdkp.output.GameInterface;
import com.eqdkplus.jdkp.output.StandardInterface;
import com.eqdkplus.jdkp.rest.EqdkpRESTClient;

public class Profile implements Serializable, Comparable<Profile> {

    private static final long serialVersionUID = -2024156141008830517L;

    public static final String FILE_EXTENSION = ".jdp"; //$NON-NLS-1$

    private String name;
    private URL eqdkpURL;
    private int connectionTimeout;
    private String encoding;
    private File localPath;
    private String gameInterface;
    private transient GameInterface gi;
    private File executePath;
    private int used;
    private File profileFile;
    private String username;
    private String password;

    @Override
    public boolean equals(Object o) {
	if (o == null || !(o instanceof Profile)) {
	    return false;
	}
	Profile p = (Profile) o;
	if (this == p) {
	    return true;
	}
	return this.eqdkpURL.equals(p.eqdkpURL)
		&& this.encoding.equals(p.encoding)
		&& this.localPath.equals(p.localPath)
		&& this.gameInterface.equals(p.gameInterface)
		&& this.username.equals(p.username)
		&& ((this.executePath == null && p.executePath == null)
			|| (this.executePath != null && this.executePath.equals(p.executePath)) || (p.executePath != null && p.executePath
			.equals(this.executePath)))
		// connection timeout equality check because otherwise we can't
		// change it in existing profile
		&& this.connectionTimeout == p.connectionTimeout;
    }

    public Profile(String name, URL eqdkpURL, int connectionTimeout, String encoding, File localPath,
	    GameInterface gameInterface, File executePath, String username) {
	this.name = name;
	this.eqdkpURL = eqdkpURL;
	this.connectionTimeout = connectionTimeout;
	this.encoding = Charset.forName(encoding).name();
	this.localPath = localPath;
	this.gameInterface = gameInterface.getClass().getName();
	this.gi = gameInterface;
	this.executePath = executePath;
	this.used = 0;
	this.username = username;
	this.password = Control.EMPTY_STRING;
    }
    
    public static Profile getProfile(String name, URL eqdkpURL, int connectionTimeout, String encoding, File localPath,
	    GameInterface gameInterface, File executePath) {
	return new Profile(name, eqdkpURL, connectionTimeout, encoding, localPath, gameInterface, executePath,"");
    }
    
    public static Profile getProfile(String name, URL eqdkpURL, int connectionTimeout, String encoding, File localPath,
	    GameInterface gameInterface, File executePath, String username, String plainPassword) throws NoSuchAlgorithmException, JAXBException, SAXException, EQDKPException, FileNotFoundException, IOException {
	
	Profile p = new Profile(name, eqdkpURL, connectionTimeout, encoding, localPath, gameInterface, executePath, username);
	EqdkpRESTClient rest = new EqdkpRESTClient(p);
	String encryptedPassword = DownloadControl.getEncryptedPassword(p.encoding, rest, username, plainPassword);
	plainPassword=null;
	p.setPassword(encryptedPassword);
	rest.close();
	return p;
    }

    /**
     * @return the name
     */
    public String getName() {
	return this.name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the eqdkpURL
     */
    public URL getEqdkpURL() {
	return this.eqdkpURL;
    }

    /**
     * @param eqdkpURL
     *            the eqdkpURL to set
     */
    public void setEqdkpURL(URL eqdkpURL) {
	this.eqdkpURL = eqdkpURL;
    }

    /**
     * @return the connectionTimeout
     */
    public int getConnectionTimeout() {
	return connectionTimeout;
    }

    /**
     * @return the encoding
     */
    public String getEncoding() {
	return this.encoding;
    }

    /**
     * @return the localPath
     */
    public File getLocalPath() {
	return this.localPath;
    }

    /**
     * @return the game interface
     */
    public GameInterface getGameInterface() {

	return this.gi;
    }

    /**
     * @return the executePath
     */
    public File getExecutePath() {
	return this.executePath;
    }

    /**
     * @return the profileFile
     */
    public File getProfileFile() {
	return profileFile;
    }

    /**
     * @return the used
     */
    public int getUsed() {
	return used;
    }

    /**
     * @return the username
     */
    public String getUsername() {
	return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
	this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
	this.password = password;
    }

    public void isUsed() {
	used++;
    }
    
    public void loadInterface() {
	if (this.gi==null && this.gameInterface!="") {
	    try {
		this.gi=(GameInterface) Class.forName(this.gameInterface).newInstance();
	    } catch (Exception e) {
		this.gi=new StandardInterface();
	    }
	}
    }

    public void save(File dir, boolean valid) throws FileNotFoundException, IOException {
	if (!valid) {
	    this.password = Control.EMPTY_STRING;
	}
	dir.mkdirs();
	FileOutputStream fos = new FileOutputStream(dir.getAbsolutePath() + '/' + name + FILE_EXTENSION);
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	oos.writeObject(this);
	fos.close();
	oos.close();
    }

    public static Profile[] load(File directory) throws IOException {
	File[] files = directory.listFiles(new FilenameFilter() {
	    @Override
	    public boolean accept(File dir, String name) {
		return name.endsWith(FILE_EXTENSION);
	    }
	});
	int length = 0;
	if (files != null) {
	    length = files.length;
	}
	Profile[] profilesLazy = new Profile[length];
	ObjectInputStream ois = null;
	FileInputStream fis = null;
	int count = 0;
	for (int i = 0; i < length; i++) {
	    try {
		fis = new FileInputStream(files[i]);
		ois = new ObjectInputStream(fis);
		profilesLazy[count] = (Profile) ois.readObject();
		fis.close();
		ois.close();
	    } catch (IOException e) {
		// simply ignore it
		continue;
	    } catch (ClassNotFoundException e) {
		// ignore it either
		continue;
	    }
	    profilesLazy[count].profileFile = files[i];
	    try {
		profilesLazy[count].gi = (GameInterface) Class.forName(profilesLazy[count].gameInterface).newInstance();
	    } catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		count--;
		fis.close();
		ois.close();
	    } catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		count--;
		fis.close();
		ois.close();
	    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		count--;
		fis.close();
		ois.close();
	    }
	    count++;
	}
	Profile[] profiles;
	if (count != length) {
	    profiles = new Profile[count];
	    System.arraycopy(profilesLazy, 0, profiles, 0, count);
	} else {
	    profiles = profilesLazy;
	}
	// sort, that the most used is the first one
	Arrays.sort(profiles, new Comparator<Profile>() {
	    @Override
	    public int compare(Profile p1, Profile p2) {
		return p1.used - p2.used;
	    }
	});
	if (fis != null) {
	    fis.close();
	}
	if (ois != null) {
	    ois.close();
	}
	return profiles;
    }

    public boolean delete() {
	return this.profileFile.delete();
    }

    @Override
    public String toString() {
	return this.name.equals(Control.LAST) ? Messages.getString("Profile.lastUsed") : this.name; //$NON-NLS-1$
    }

    @SuppressWarnings("nls")
    public String getInfo() {
	StringBuilder sb = new StringBuilder();
	sb.append("Name: ").append(name).append(Control.NEW_LINE); //$NON-NLS-1$
	sb.append("Encoding: ").append(encoding).append(Control.NEW_LINE); //$NON-NLS-1$
	sb.append("EQDKP URL: ").append(eqdkpURL).append(Control.NEW_LINE); //$NON-NLS-1$
	sb.append("Connection timeout: ").append(connectionTimeout).append(Control.NEW_LINE); //$NON-NLS-1$
	sb.append("Local path: ").append(localPath.getAbsolutePath()).append(Control.NEW_LINE); //$NON-NLS-1$
	sb.append("Game interface: ").append(gameInterface).append(Control.NEW_LINE); //$NON-NLS-1$
	sb.append("Execute path: ").append(executePath == null ? Control.NULL : executePath.getAbsolutePath()).append(Control.NEW_LINE); //$NON-NLS-1$ //$NON-NLS-2$
	sb.append("Used: ").append(used).append('\n'); //$NON-NLS-1$
	sb.append("Profile file: ").append(profileFile == null ? Control.NULL : profileFile.getAbsolutePath()).append(Control.NEW_LINE); //$NON-NLS-1$ //$NON-NLS-2$
	return sb.toString();
    }

    @Override
    public int compareTo(Profile p) {
	return p.used - this.used;
    }
}