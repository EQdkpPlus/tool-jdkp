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

package com.eqdkplus.jdkp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.eqdkplus.jdkp.control.Control;
import com.eqdkplus.jdkp.control.DownloadControl;
import com.eqdkplus.jdkp.output.GameInterface;
import com.eqdkplus.jdkp.output.GameInterfaceUtil;
import com.eqdkplus.jdkp.prf.Profile;

@SuppressWarnings("serial")
public class Gui extends JFrame {

    private static final String PW_DUMMY = "********"; //$NON-NLS-1$
    private static final String JDKP = "JDKP"; //$NON-NLS-1$
    private static final String JDKP_HP = "http://eqdkp-plus.eu"; //$NON-NLS-1$
    private static final String EXCHANGE_PHP = "/api.php"; //$NON-NLS-1$
    private static final String ARROW_RIGHT = " >>"; //$NON-NLS-1$
    private static final String ARROW_LEFT = " <<"; //$NON-NLS-1$
    private static final String LICENSE = "<html>JDKP &copy; 2011-2015 - Henrik \"kirax\" Schmutz - kirax@eqdkp-plus.eu<br>licensed under Creative Commons - Attribution Non-Commercial No Derivatives 3.0 Unported</html>"; //$NON-NLS-1$
    private static final String EQDKP = "EQDKP"; //$NON-NLS-1$
    private static final String MAINICON_PATH = "res/mainicon.png"; //$NON-NLS-1$
    private static final String JDKPICON_PATH = "/res/jdkp.png"; //$NON-NLS-1$
    private static final String PHP = ".php"; //$NON-NLS-1$
    private static final String REGEX_INTEGER = "[0-9]*"; //$NON-NLS-1$

    //private static final String[] PROTOCOLS_ARRAY = new String[] {"http://", "https://"};  //$NON-NLS-1$ //$NON-NLS-2$
    private static List<String> PROTOCOLS = new ArrayList<String>(Arrays.asList(new String[] {"http://", "https://"})); //$NON-NLS-1$ //$NON-NLS-2$

    private static final char QUOTE = '\"';
    private boolean pwModified = false;
    private boolean executeFieldIsStd = false;
    private boolean localFieldIsStd = false;
    private boolean advancedVisible = false;
    private Map<JComponent, Color> coloredElements;

    // --------------------- GUI ------------------------
    private JLabel logo;
    private JLabel placeholder;
    private JLabel profileLabel;
    private JComboBox<GameInterface> formatChooser;
    private JLabel remoteLabel;
    private JComboBox<String> protocolChooser;
    private JTextField remoteField;
    private JLabel exchangeLabel;
    private JLabel localLabel;
    private JTextField localField;
    private JButton localStdPath;
    private JButton localChooser;
    private JButton delProfileButton;
    private JLabel executeLabel;
    private JTextField executeField;
    private JButton executeChooser;
    private JButton executeStdPath;
    private JLabel formatLabel;
    private JComboBox<Profile> profileChooser;
    private JButton advancedButton;
    private JLabel authLabel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel timeoutLabel;
    private JTextField timeoutField;
    private JButton downloadButton;
    private JLabel hintLabel;
    private JPanel field;
    private JPanel field2;
    private JPanel topfield;
    private JPanel authfield;
    private JPanel timeoutPanel;
    private JPanel bottom;
    private JFileChooser fileChooser;
    private Job currentJob;
    private JLabel copyright;

    // ------------------------- end GUI -----------------------

    public Gui() throws ClassNotFoundException, IOException {
	super();
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setIconImage(ImageIO.read(getClass().getClassLoader().getResource(MAINICON_PATH)));
	setTitle(JDKP);
	setLocationByPlatform(true);
	logo = new JLabel();
	placeholder = new JLabel(Control.HTML_OPEN_TAG
		+ String.format(
			Messages.getString("Gui.fInfo"), Control.HTML_BREAK_TAG + JDKP_HP + Control.HTML_CLOSE_TAG)); //$NON-NLS-1$
	profileLabel = new JLabel(Messages.getString("Gui.profile")); //$NON-NLS-1$
	// combobox that displays "own" interfaces before "foreign" ones
	formatChooser = new JComboBox<GameInterface>() {
	    @Override
	    public void addItem(GameInterface gi) {
		int size = getModel().getSize();
		Object obj;
		boolean added = false;
		for (int i = 0; i < size; i++) {
		    obj = getModel().getElementAt(i);
		    int compare = 0;
		    if (obj instanceof GameInterface) {
			compare = gi.compareTo((GameInterface) obj);
		    } else {
			continue;
		    }
		    if (compare <= 0) {
			insertItemAt(gi, i);
			added = true;
			break;
		    }
		}
		if (!added) {
		    super.addItem(gi);
		}

	    }
	};
	remoteLabel = new JLabel(Messages.getString("Gui.remote")); //$NON-NLS-1$
	protocolChooser = new JComboBox<String>(PROTOCOLS.toArray(new String[0]));
	remoteField = new JTextField();
	exchangeLabel = new JLabel(EXCHANGE_PHP);
	localLabel = new JLabel(Messages.getString("Gui.local")); //$NON-NLS-1$
	localField = new JTextField();
	localStdPath = new JButton(Messages.getString("Gui.default")); //$NON-NLS-1$
	localChooser = new JButton();
	delProfileButton = new JButton(Messages.getString("Gui.delete")); //$NON-NLS-1$
	executeLabel = new JLabel(Messages.getString("Gui.execute")); //$NON-NLS-1$
	executeField = new JTextField();
	executeChooser = new JButton();
	executeStdPath = new JButton(Messages.getString("Gui.default")); //$NON-NLS-1$
	formatLabel = new JLabel(Messages.getString("Gui.format")); //$NON-NLS-1$
	// own combobox sorting its elements
	profileChooser = new JComboBox<Profile>() {
	    @Override
	    public void addItem(Profile p) {
		int size = getModel().getSize();
		boolean added = false;
		Object obj;
		for (int i = 0; i < size; i++) {
		    obj = getModel().getElementAt(i);
		    int compare = 0;
		    if (obj instanceof Profile) {
			compare = p.compareTo((Profile) obj);
		    } else {
			continue;
		    }
		    if (compare <= 0) {
			insertItemAt(p, i);
			added = true;
			break;
		    }
		}
		if (!added) {
		    super.addItem(p);
		}
	    }
	};
	advancedButton = new JButton(Messages.getString("Gui.advanced") + ARROW_RIGHT); //$NON-NLS-1$
	authLabel = new JLabel(Messages.getString("Gui.auth")); //$NON-NLS-1$
	usernameLabel = new JLabel(Messages.getString("Gui.user")); //$NON-NLS-1$
	usernameField = new JTextField();
	passwordLabel = new JLabel(Messages.getString("Gui.pass")); //$NON-NLS-1$
	passwordField = new JPasswordField();
	timeoutLabel = new JLabel(Messages.getString("Gui.cTimeOut")); //$NON-NLS-1$
	timeoutField = new JTextField();
	downloadButton = new JButton(Messages.getString("Gui.download")); //$NON-NLS-1$
	hintLabel = new JLabel();
	field = new JPanel();
	field2 = new JPanel();
	topfield = new JPanel();
	authfield = new JPanel();
	timeoutPanel = new JPanel();
	bottom = new JPanel();
	fileChooser = new JFileChooser();
	currentJob = new Job(Control.CHECKED, Messages.getString("Gui.ready"), -1); //$NON-NLS-1$
	coloredElements = new HashMap<JComponent, Color>();
	copyright = new JLabel(LICENSE);
	copyright.setFont(copyright.getFont().deriveFont(Font.PLAIN, 9f));

	field.setBorder(BorderFactory.createTitledBorder(EQDKP));
	field2.setBorder(BorderFactory.createTitledBorder(Messages.getString("Gui.game"))); //$NON-NLS-1$
	downloadButton.setAlignmentX(0.5f);
	hintLabel.setAlignmentX(0.5f);
	formatChooser.setRenderer(new FormatComboBoxRenderer());
	logo.setIcon(new ImageIcon(this.getClass().getResource(JDKPICON_PATH)));
	authLabel.setVisible(false);
	authfield.setVisible(false);
	timeoutPanel.setVisible(false);
	hintLabel.setVisible(false);
	usernameLabel.setIcon(Control.USERICON);
	passwordLabel.setIcon(Control.PASSICON);
	timeoutLabel.setIcon(Control.CLOCK);
	profileLabel.setIcon(Control.PROFILEICON);
	delProfileButton.setIcon(Control.DELETEICON);
	remoteLabel.setIcon(Control.REMOTE_ICON);
	localLabel.setIcon(Control.LOCAL_ICON);
	authLabel.setIcon(Control.AUTH_ICON);
	formatLabel.setIcon(Control.FORMAT_ICON);
	executeLabel.setIcon(Control.EXECUTE_ICON);
	advancedButton.setIcon(Control.ADVANCED_ICON);
	localChooser.setIcon(Control.FOLDER_ICON);
	localStdPath.setIcon(Control.DEFAULT_ICON);
	executeChooser.setIcon(Control.FOLDER_ICON);
	executeStdPath.setIcon(Control.DEFAULT_ICON);
	fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	getRootPane().setDefaultButton(downloadButton);

	layoutGUI();

	registerListeners();

	// load game interfaces from home dir
	for (GameInterface g : GameInterfaceUtil.loadFromDir(Control.GAME_INTERFACE_CLASS_PATH)) {
	    formatChooser.addItem(g);
	}

	// load game interfaces from prog
	for (GameInterface g : GameInterfaceUtil.getStandardInterfaces()) {
	    if (!g.getName().equals("WoW GetDKP (LUA) (not yet compatible)") && !g.getName().equals("Standard")) { //$NON-NLS-1$ //$NON-NLS-2$
		formatChooser.addItem(g);
	    }
	}

	loadProfiles();

	// set font
	if (formatChooser.getItemCount() > 0) {
	    formatChooser.setSelectedIndex(0);
	    setFormatChooserFont();
	}

	fillFieldsByProfile();

	if ((Control.PROFILE_PATH.exists() || Control.PROFILE_PATH.mkdirs())
		&& (Control.GAME_INTERFACE_CLASS_PATH.exists() || Control.GAME_INTERFACE_CLASS_PATH.mkdirs())) {
	    setTransferHandler(new JDKPTransferHandler(this, Control.PROFILE_PATH, Control.GAME_INTERFACE_CLASS_PATH));
	}

	pack();
    }

    private void startDownload(Profile profile) {
	String pw = new String(passwordField.getPassword());
	if (pwModified) {
	    if (!pw.equals(Control.EMPTY_STRING)) {
		passwordField.setText(PW_DUMMY);
	    } else {
		profile.setPassword(Control.EMPTY_STRING);
	    }
	    pwModified = false;
	}
	DownloadControl ctrl = new DownloadControl(profile, this, pw.equals(PW_DUMMY) ? Control.EMPTY_STRING : pw);
	pw = null;
	ctrl.execute();
    }

    private ActionListener getShowFileChooserActionListener(final JTextField target) {
	return new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		Gui.this.fileChooser.showOpenDialog(Gui.this);
		if (Gui.this.fileChooser.getSelectedFile() != null) {
		    target.setText(Gui.this.fileChooser.getSelectedFile().getPath());
		}
		// to ensure that the whole path is visible in the text area
		Gui.this.pack();
	    }
	};
    }

    private void showHint(String msg, JComponent comp, Color col) {
	hintLabel.setText(msg);
	colorElement(comp, col);
	hintLabel.setVisible(true);
	pack();
    }

    /*
     * Maintains that the elements always know their standard color.
     */
    private synchronized void colorElement(JComponent comp, Color col) {
	if (!coloredElements.containsKey(comp)) {
	    coloredElements.put(comp, comp.getBackground());
	}
	comp.setBackground(col);
    }

    private synchronized void decolor() {
	for (JComponent comp : coloredElements.keySet()) {
	    comp.setBackground(coloredElements.get(comp));
	}
	coloredElements.clear();
    }

    private synchronized void deHint() {
	if (!coloredElements.isEmpty()) {
	    decolor();
	}
	if (hintLabel.isVisible()) {
	    hintLabel.setVisible(false);
	    pack();
	}
    }

    private URL getFixedURL() {
	for (int i = 0; i < protocolChooser.getItemCount(); i++) {
	    if (remoteField.getText().toLowerCase().startsWith(protocolChooser.getItemAt(i))) {
		remoteField.setText(remoteField.getText().substring(protocolChooser.getItemAt(i).length()));
		protocolChooser.setSelectedIndex(i);
		break;
	    }
	}
	URL url = null;
	try {
	    url = new URL((String) protocolChooser.getSelectedItem() + remoteField.getText());

	    if (!url.getPath().endsWith(exchangeLabel.getText())) {
		if (url.getPath().endsWith(PHP)) {
		    url = new URL(url.getProtocol(), url.getHost(), url.getPath().substring(0,
			    url.getPath().lastIndexOf('/'))
			    + exchangeLabel.getText());
		} else {
		    url = new URL(url.getProtocol(), url.getHost(), url.getPath() + exchangeLabel.getText());
		}
	    }
	} catch (MalformedURLException e) {
	    // can't happen
	    throw new AssertionError();
	}
	return url;
    }

    public Job getJob() {
	return currentJob;
    }

    public void addProfile(Profile p) {
	this.profileChooser.addItem(p);
//	if (profileChooser.getItemCount()>1) {
//	    profileLabel.setVisible(true);
//	    profileChooser.setVisible(true);
//	    this.pack();
//	}
    }

    public void addInterface(GameInterface gi) {
	this.formatChooser.addItem(gi);
    }

    private void setFormatChooserFont() {
	if (!GameInterface.class.getPackage().equals(formatChooser.getSelectedItem().getClass().getPackage())) {
	    formatChooser.setFont(formatChooser.getFont().deriveFont(Font.ITALIC));
	} else {
	    formatChooser.setFont(formatChooser.getFont().deriveFont(Font.BOLD));
	}
    }

    public void setUsername(String user) {
	this.usernameField.setText(user);
    }

    public void setPassword() {
	this.passwordField.setText(PW_DUMMY);
    }

    private void fillFieldsByProfile() {
	if (profileChooser.getSelectedItem() != null) {
	    Profile p = (Profile) profileChooser.getSelectedItem();
	    protocolChooser.setSelectedIndex(PROTOCOLS.indexOf(p.getEqdkpURL().getProtocol()+"://")); //$NON-NLS-1$
	    //protocolChooser.setSelectedItem(p.getEqdkpURL().getProtocol());
	    remoteField.setText(p.getEqdkpURL().getHost()
		    + p.getEqdkpURL().getPath().substring(0, p.getEqdkpURL().getPath().lastIndexOf('/')));
	    // localField.setText(p.getLocalPath().getAbsolutePath());
	    formatChooser.setSelectedItem(p.getGameInterface());
	    if (p.getExecutePath() != null) {
		executeField.setText(p.getExecutePath().getAbsolutePath());
	    } else {
		executeField.setText(Control.EMPTY_STRING);
	    }
	    if (p.getLocalPath() != null) {
		localField.setText(p.getLocalPath().getAbsolutePath());
	    } else {
		localField.setText(Control.EMPTY_STRING);
	    }
	    usernameField.setText(p.getUsername());
	    passwordField
		    .setText(p.getPassword() == null || p.getPassword().equals(Control.EMPTY_STRING) ? Control.EMPTY_STRING
			    : PW_DUMMY);
	    timeoutField
		    .setText(p.getConnectionTimeout() == Control.DEFAULT_CONNECTION_TIMEOUT_MS ? Control.EMPTY_STRING
			    : Integer.toString(p.getConnectionTimeout() / 1000));
	    pack();
	} else {
	    fillFieldsByFormat();
	}
    }

    private void fillFieldsByFormat() {
	setFormatChooserFont();
	GameInterface gi = (GameInterface) formatChooser.getSelectedItem();
	if (gi != null) {
	    executeField.setText(gi.getPlatformExecutable());
	    executeFieldIsStd = true;
	    localField.setText(gi.getPlatformSavePath());
	    localFieldIsStd = true;
	    executeStdPath.setEnabled(false);
	    localStdPath.setEnabled(false);
	}
    }

    public void loadProfiles() throws IOException {
	profileChooser.removeAllItems();
	// load profiles from home dir
	for (Profile p : Profile.load(Control.PROFILE_PATH)) {
	    profileChooser.addItem(p);
	}
	if (profileChooser.getItemCount() == 0) {
	    profileLabel.setVisible(false);
	    profileChooser.setVisible(false);
	    delProfileButton.setVisible(false);
	} else {
	    profileLabel.setVisible(true);
	    profileChooser.setVisible(true);
	    delProfileButton.setVisible(true);
	    profileChooser.setSelectedIndex(0);
	}
    }

    public Profile getLastUsedProfile() {
	for (int i = 0; i < profileChooser.getItemCount(); i++) {
	    if (profileChooser.getItemAt(i).getName().equals(Control.LAST)) {
		return profileChooser.getItemAt(i);
	    }
	}
	return null;
    }

    private void layoutGUI() {
	final Insets FOURFIVE = new Insets(5, 5, 5, 5);
	final Insets THREEFIVEZERO = new Insets(5, 5, 5, 0);
	final Insets FIVEZEROFIVEZERO = new Insets(5, 0, 5, 0);
	final Insets FIVEZEROFIVEFIVE = new Insets(5, 0, 5, 5);
	final Insets THREEZEROFIVE = new Insets(0, 0, 0, 5);
	final Insets ZEROFIVEZEROFIVE = new Insets(0, 5, 0, 5);
	final Insets ZEROFIVEZEROZERO = new Insets(0, 5, 0, 0);

	GridBagLayout gbl = new GridBagLayout();
	GridBagLayout fl = new GridBagLayout();
	GridBagLayout tfl = new GridBagLayout();
	GridBagLayout afl = new GridBagLayout();
	GridBagLayout tofl = new GridBagLayout();
	GridBagLayout f2l = new GridBagLayout();
	BoxLayout bfl = new BoxLayout(bottom, BoxLayout.Y_AXIS);

	field2.setLayout(f2l);
	authfield.setLayout(afl);
	timeoutPanel.setLayout(tofl);
	topfield.setLayout(tfl);
	bottom.setLayout(bfl);
	field.setLayout(fl);
	setLayout(gbl);

	GridBagConstraints gbc = new GridBagConstraints();

	gbc.insets = FOURFIVE;
	gbc.fill = GridBagConstraints.NONE;
	gbc.gridwidth = 1;
	gbc.weightx = 1;
	gbc.weighty = 0;
	gbc.anchor = GridBagConstraints.NORTHWEST;
	gbl.setConstraints(logo, gbc);
	add(logo);

	gbc.anchor = GridBagConstraints.NORTHEAST;
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbl.setConstraints(placeholder, gbc);
	add(placeholder);

	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.anchor = GridBagConstraints.CENTER;
	gbc.weighty = 1;
	gbl.setConstraints(topfield, gbc);
	add(topfield);

	gbl.setConstraints(field, gbc);
	add(field);

	gbc.weighty = 0;
	gbc.gridwidth = 1;
	gbc.weightx = 0;
	tfl.setConstraints(profileLabel, gbc);
	topfield.add(profileLabel);

	gbc.weightx = 1;
	tfl.setConstraints(profileChooser, gbc);
	topfield.add(profileChooser);

	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbc.weightx = 0;
	tfl.setConstraints(delProfileButton, gbc);
	topfield.add(delProfileButton);

	gbc.gridwidth = 1;
	fl.setConstraints(remoteLabel, gbc);
	field.add(remoteLabel);

	gbc.insets = THREEFIVEZERO;
	fl.setConstraints(protocolChooser, gbc);
	field.add(protocolChooser);

	gbc.insets = FIVEZEROFIVEZERO;
	gbc.gridwidth = 2;
	gbc.ipadx = 100;
	gbc.weightx = 1;
	fl.setConstraints(remoteField, gbc);
	field.add(remoteField);

	gbc.insets = FIVEZEROFIVEFIVE;
	gbc.gridwidth = 1;
	gbc.weightx = 0;
	gbc.ipadx = 0;
	fl.setConstraints(exchangeLabel, gbc);
	field.add(exchangeLabel);

	gbc.gridy = 2;
	// gbc.gridwidth = 1;
	fl.setConstraints(authLabel, gbc);
	field.add(authLabel);

	gbc.weightx = 1;
	gbc.gridwidth = 4;
	fl.setConstraints(authfield, gbc);
	field.add(authfield);

	gbc.gridy = 0;
	gbc.weightx = 0;
	gbc.gridwidth = 1;
	gbc.insets = THREEZEROFIVE;
	afl.setConstraints(usernameLabel, gbc);
	authfield.add(usernameLabel);

	gbc.insets = ZEROFIVEZEROFIVE;
	gbc.weightx = 1;
	afl.setConstraints(usernameField, gbc);
	authfield.add(usernameField);

	gbc.weightx = 0;
	afl.setConstraints(passwordLabel, gbc);
	authfield.add(passwordLabel);

	gbc.insets = ZEROFIVEZEROZERO;
	gbc.weightx = 1;
	afl.setConstraints(passwordField, gbc);
	authfield.add(passwordField);

	gbc.insets = FOURFIVE;
	gbc.weightx = 0;
	gbc.gridy = 4;
	gbc.gridwidth = 3;
	gbc.anchor = GridBagConstraints.SOUTHWEST;
	gbc.fill = GridBagConstraints.NONE;
	fl.setConstraints(advancedButton, gbc);
	field.add(advancedButton);

	gbc.insets = THREEFIVEZERO;
	gbc.weightx = 0;
	gbc.gridwidth = 1;
	fl.setConstraints(timeoutPanel, gbc);
	field.add(timeoutPanel);

	gbc.anchor = GridBagConstraints.CENTER;
	gbc.insets = THREEZEROFIVE;
	gbc.gridy = GridBagConstraints.RELATIVE;
	tofl.setConstraints(timeoutLabel, gbc);
	timeoutPanel.add(timeoutLabel);

	gbc.insets = THREEFIVEZERO;
	gbc.ipadx = 55;
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	tofl.setConstraints(timeoutField, gbc);
	timeoutPanel.add(timeoutField);

	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.weightx = 1;
	gbc.ipadx = 0;
	gbc.insets = FOURFIVE;
	gbc.weighty = 1;
	gbl.setConstraints(field2, gbc);
	add(field2);

	gbc.weightx = 0;
	gbc.gridwidth = 1;
	f2l.setConstraints(formatLabel, gbc);
	field2.add(formatLabel);

	gbc.gridwidth = 2;
	gbc.weightx = 1;
	f2l.setConstraints(formatChooser, gbc);
	field2.add(formatChooser);

	gbc.weightx = 0;
	gbc.gridwidth = 1;
	gbc.gridy = 1;
	f2l.setConstraints(localLabel, gbc);
	field2.add(localLabel);

	// gbc.gridwidth = 1;
	gbc.weightx = 1;
	f2l.setConstraints(localField, gbc);
	field2.add(localField);

	// gbc.gridwidth = 1;
	gbc.weightx = 0;
	f2l.setConstraints(localStdPath, gbc);
	field2.add(localStdPath);

	gbc.gridwidth = GridBagConstraints.REMAINDER;
	f2l.setConstraints(localChooser, gbc);
	field2.add(localChooser);

	gbc.gridwidth = 1;
	gbc.gridy = 2;
	gbc.weightx = 0;
	f2l.setConstraints(executeLabel, gbc);
	field2.add(executeLabel);

	gbc.weightx = 1;
	f2l.setConstraints(executeField, gbc);
	field2.add(executeField);

	gbc.weightx = 0;
	f2l.setConstraints(executeStdPath, gbc);
	field2.add(executeStdPath);

	gbc.gridwidth = GridBagConstraints.REMAINDER;
	f2l.setConstraints(executeChooser, gbc);
	field2.add(executeChooser);

	gbc.anchor = GridBagConstraints.SOUTH;
	gbc.weightx = 1;
	gbc.gridy = GridBagConstraints.RELATIVE;
	gbl.setConstraints(bottom, gbc);
	add(bottom);

	bottom.add(downloadButton);
	bottom.add(hintLabel);
	bottom.add(currentJob);

	gbc.anchor = GridBagConstraints.SOUTHEAST;
	gbc.weightx = 0;
	gbl.setConstraints(copyright, gbc);
	add(copyright);
    }

    private void registerListeners() {
	downloadButton.addActionListener(new ActionListener() {
	    private boolean checkInputs(URL url, File target, File execute) {
		if (target.exists()) {
		    if (target.isFile()) {
			return checkExecuteFile(execute);
		    }
		    showHint(Messages.getString("Gui.notAFile"), localField, Color.RED); //$NON-NLS-1$
		    return false;
		}
		if (target.getParentFile() != null) {
		    target.getParentFile().mkdirs();
		    if (target.getParentFile().canWrite()) {
			return checkExecuteFile(execute);
		    }
		}
		showHint(Messages.getString("Gui.cantWrite"), localField, Color.RED); //$NON-NLS-1$
		return false;
	    }

	    private boolean checkExecuteFile(File file) {
		if (file == null || file.getPath().equals(Control.EMPTY_STRING)) {
		    return true;
		}
		if (!file.canExecute()) {
		    showHint(Messages.getString("Gui.cantExecute"), executeField, Color.RED); //$NON-NLS-1$
		    return false;
		}
		return true;
	    }

	    private Profile getEqualProfile(Profile p, String name, boolean contain) {
		for (int i = 0; i < profileChooser.getItemCount(); i++) {
		    Profile tempProfile = profileChooser.getItemAt(i);
		    if (p.equals(tempProfile) && (contain == tempProfile.getName().equals(name))) {
			return tempProfile;
		    }
		}
		return null;
	    }

	    @Override
	    public void actionPerformed(ActionEvent ae) {
		// clears every hint from an eventually former call
		deHint();
		if (remoteField.getText().equals(Control.EMPTY_STRING)) {
		    colorElement(protocolChooser, Color.YELLOW);
		    showHint(Messages.getString("Gui.enterURL"), remoteField, Color.YELLOW); //$NON-NLS-1$
		} else if (localField.getText().equals(Control.EMPTY_STRING)) {
		    showHint(Messages.getString("Gui.enterFile"), localField, Color.YELLOW); //$NON-NLS-1$
		} else if (!timeoutField.getText().matches(REGEX_INTEGER)) {
		    showHint(Messages.getString("Gui.enterInt"), timeoutField, Color.YELLOW); //$NON-NLS-1$
		} else if (!usernameField.getText().equals(Control.EMPTY_STRING)
			&& passwordField.getPassword().length == 0) {
		    showHint(Messages.getString("Gui.enterPassword"), passwordField, Color.YELLOW); //$NON-NLS-1$
		    if (!advancedVisible) {
			advancedButton.doClick();
		    }
		} else {
		    URL url = getFixedURL();
		    File target = new File(localField.getText()).getAbsoluteFile();
		    File execute = !executeField.getText().equals(Control.EMPTY_STRING) ? new File(executeField
			    .getText()) : null;

		    if (checkInputs(url, target, execute)) {
			Profile currentProfile = new Profile(Control.LAST, url, timeoutField.getText().equals(
				Control.EMPTY_STRING) ? DownloadControl.DEFAULT_CONNECTION_TIMEOUT_MS : Integer
				.parseInt(timeoutField.getText()) * 1000, Control.CHARSET, target,
				(GameInterface) formatChooser.getSelectedItem(), execute, usernameField.getText());
			Profile equalProfile = null;
			// check whether we have a new profile and ask to save
			// it
			Profile tempProfile = getEqualProfile(currentProfile, Control.LAST, false);
			if (tempProfile != null) {
			    equalProfile = tempProfile;
			}
			if (equalProfile == null) {
			    int option = JOptionPane.showConfirmDialog(Gui.this,
				    Messages.getString("Gui.profileNotYetSaved")); //$NON-NLS-1$
			    if (option == JOptionPane.YES_OPTION) {
				String selectedProfileName = Control.EMPTY_STRING;
				if (profileChooser.getSelectedItem() != null) {
				    selectedProfileName = ((Profile) profileChooser.getSelectedItem()).getName();
				}
				String name = JOptionPane.showInputDialog(
					Gui.this,
					Messages.getString("Gui.enterProfileName"), selectedProfileName.equals(Control.LAST) ? Control.EMPTY_STRING : selectedProfileName); //$NON-NLS-1$
				if (name == null) {
				    return;
				}
				while (name.equals(Control.LAST)) {
				    name = JOptionPane.showInputDialog(
					    Gui.this,
					    Messages.getString("Gui.enterProfileName") + Control.NEW_LINE + String.format(Messages.getString("Gui.notAllowed"), QUOTE + Control.LAST + QUOTE)); //$NON-NLS-1$ //$NON-NLS-2$
				}
				currentProfile.setName(name);
				currentProfile.isUsed();
				try {
				    currentProfile.save(Control.PROFILE_PATH, true);
				    loadProfiles();
				    tempProfile = getEqualProfile(currentProfile, Control.LAST, true);
				    if (tempProfile != null) {
					tempProfile.delete();
				    }
				    loadProfiles();
				} catch (IOException e) {
				    JOptionPane.showMessageDialog(
					    Gui.this,
					    Messages.getString("Gui.cantSaveProfile") + Control.NEW_LINE + e.toString(), //$NON-NLS-1$
					    Messages.getString("Gui.error"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
				}
			    } else if (option == JOptionPane.CANCEL_OPTION) {
				return;
			    }
			    startDownload(currentProfile);
			} else {
			    equalProfile.isUsed();
			    startDownload(equalProfile);
			}
		    }
		}
	    }
	});
	delProfileButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		Profile p = (Profile) profileChooser.getSelectedItem();
		if (JOptionPane.showConfirmDialog(
			Gui.this,
			Messages.getString("Gui.reallyDeleteProfile") + Control.NEW_LINE //$NON-NLS-1$
				+ p.toString(),
			Messages.getString("Gui.deleteProfile"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) { //$NON-NLS-1$
		    if (!p.delete()) {
			JOptionPane.showMessageDialog(Gui.this,
				Messages.getString("Gui.cantDeleteProfile") + Control.NEW_LINE + p.toString()); //$NON-NLS-1$
		    } else {
			try {
			    loadProfiles();
			    fillFieldsByProfile();
			} catch (IOException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
		    }
		}
	    }
	});
	profileChooser.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		fillFieldsByProfile();
	    }
	});
	formatChooser.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		if (profileChooser.getSelectedItem() == null || executeFieldIsStd || localFieldIsStd) {
		    fillFieldsByFormat();
		}
	    }
	});
	advancedButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		if (advancedVisible) {
		    authLabel.setVisible(false);
		    authfield.setVisible(false);
		    timeoutPanel.setVisible(false);
		    advancedButton.setText(Messages.getString("Gui.advanced") + ARROW_RIGHT); //$NON-NLS-1$
		    advancedVisible = false;
		    // to ensure that the window is being shrunk after
		    // removing user/pass fields
		    Gui.this.pack();
		} else {
		    authLabel.setVisible(true);
		    authfield.setVisible(true);
		    timeoutPanel.setVisible(true);
		    advancedButton.setText(Messages.getString("Gui.advanced") + ARROW_LEFT); //$NON-NLS-1$
		    advancedVisible = true;
		    // to ensure that the window is being enlarged after adding
		    // user/pass fields
		    Gui.this.pack();
		}
	    }
	});
	localChooser.addActionListener(getShowFileChooserActionListener(localField));
	executeChooser.addActionListener(getShowFileChooserActionListener(executeField));
	executeStdPath.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		GameInterface gi = (GameInterface) formatChooser.getSelectedItem();
		if (gi != null) {
		    executeField.setText(gi.getPlatformExecutable());
		    executeStdPath.setEnabled(false);
		}
	    }
	});
	localStdPath.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		GameInterface gi = (GameInterface) formatChooser.getSelectedItem();
		if (gi != null) {
		    localField.setText(gi.getPlatformSavePath());
		    localStdPath.setEnabled(false);
		}
	    }
	});
	passwordField.getDocument().addDocumentListener(new DocumentListener() {
	    @Override
	    public void changedUpdate(DocumentEvent arg0) {
		// ignore
	    }

	    @Override
	    public void insertUpdate(DocumentEvent arg0) {
		pwModified = true;
	    }

	    @Override
	    public void removeUpdate(DocumentEvent arg0) {
		pwModified = true;
	    }
	});
	localField.getDocument().addDocumentListener(new DocumentListener() {
	    @Override
	    public void changedUpdate(DocumentEvent e) {
		// ignore
	    }

	    @Override
	    public void insertUpdate(DocumentEvent e) {
		changeFont();
	    }

	    @Override
	    public void removeUpdate(DocumentEvent e) {
		changeFont();
	    }

	    private void changeFont() {
		if ((GameInterface) formatChooser.getSelectedItem() != null) {
		    if (localField.getText().equalsIgnoreCase(
			    ((GameInterface) formatChooser.getSelectedItem()).getPlatformSavePath())) {
			localField.setFont(localField.getFont().deriveFont(Font.BOLD));
			localFieldIsStd = true;
			localStdPath.setEnabled(false);
		    } else {
			localField.setFont(localField.getFont().deriveFont(Font.PLAIN));
			localFieldIsStd = false;
			localStdPath.setEnabled(true);
		    }
		}
	    }
	});
	executeField.getDocument().addDocumentListener(new DocumentListener() {
	    @Override
	    public void changedUpdate(DocumentEvent e) {
		// ignore
	    }

	    @Override
	    public void insertUpdate(DocumentEvent e) {
		changeFont();
	    }

	    @Override
	    public void removeUpdate(DocumentEvent e) {
		changeFont();
	    }

	    private void changeFont() {
		if ((GameInterface) formatChooser.getSelectedItem() != null) {
		    if (executeField.getText().equalsIgnoreCase(
			    ((GameInterface) formatChooser.getSelectedItem()).getPlatformExecutable())) {
			executeField.setFont(executeField.getFont().deriveFont(Font.BOLD));
			executeFieldIsStd = true;
			executeStdPath.setEnabled(false);
		    } else {
			executeField.setFont(executeField.getFont().deriveFont(Font.PLAIN));
			executeFieldIsStd = false;
			executeStdPath.setEnabled(true);
		    }
		}
	    }
	});
    }
}