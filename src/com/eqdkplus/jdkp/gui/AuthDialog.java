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

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.eqdkplus.jdkp.control.Control;
import com.eqdkplus.jdkp.util.Pair;

@SuppressWarnings("serial")
public class AuthDialog extends JDialog {

    private JLabel head;
    private JLabel userLabel;
    private JTextField userField;
    private JLabel passLabel;
    private JPasswordField passField;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;

    private boolean cancelled = false;

    public synchronized Pair<String, String> getAuthentication() {
	this.setVisible(true);
	Pair<String, String> auth = new Pair<String, String>(userField.getText(), new String(passField.getPassword()));
	userField.setText(Control.EMPTY_STRING);
	passField.setText(Control.EMPTY_STRING);
	this.dispose();
	if (cancelled) {
	    return null;
	}
	return auth;
    }
    
    public boolean getCancelled() {
	return cancelled;
    }
    
    public String getUsername() {
	return userField.getText();
    }
    
    public String getPassword() {
	return new String(passField.getPassword());
    }

    public AuthDialog(Window owner, String site, String username) {
	super(owner, Messages.getString("AuthDialog.authNeeded"), ModalityType.APPLICATION_MODAL); //$NON-NLS-1$
	setLocationByPlatform(true);
	this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	this.head = new JLabel(Control.HTML_OPEN_TAG+String.format(Messages.getString("AuthDialog.pleaseEnterLogin"), site)+Control.HTML_BREAK_TAG + Messages.getString("AuthDialog.CMS")+Control.HTML_CLOSE_TAG); //$NON-NLS-1$ //$NON-NLS-2$
	this.userLabel = new JLabel(Messages.getString("Gui.user")); //$NON-NLS-1$
	this.userField = new JTextField(username);
	this.passLabel = new JLabel(Messages.getString("Gui.pass")); //$NON-NLS-1$
	this.passField = new JPasswordField();
	this.buttonBar = new JPanel(new FlowLayout());
	this.okButton = new JButton(Messages.getString("ExceptionDialog.ok")); //$NON-NLS-1$
	this.cancelButton = new JButton(Messages.getString("AuthDialog.cancel")); //$NON-NLS-1$
	getRootPane().setDefaultButton(okButton);

	userLabel.setIcon(Control.USERICON);
	passLabel.setIcon(Control.PASSICON);

	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	setLayout(gbl);

	gbc.insets = new Insets(5, 5, 5, 5);
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.weightx = 0.0;
	gbc.weighty = 0.0;
	gbc.gridwidth = 2;

	gbl.setConstraints(head, gbc);
	add(head);

	gbc.gridy = 1;
	gbc.gridwidth = 1;
	gbl.setConstraints(userLabel, gbc);
	add(userLabel);

	gbc.weightx = 1;
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbl.setConstraints(userField, gbc);
	add(userField);

	gbc.weightx = 0;
	gbc.gridy = GridBagConstraints.RELATIVE;
	gbc.gridwidth = 1;
	gbl.setConstraints(passLabel, gbc);
	add(passLabel);

	gbc.weightx = 1;
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbl.setConstraints(passField, gbc);
	add(passField);

	buttonBar.add(okButton);
	buttonBar.add(cancelButton);

	gbl.setConstraints(buttonBar, gbc);
	add(buttonBar);

	pack();

	this.okButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		AuthDialog.this.setVisible(false);
		
	    }
	});

	this.cancelButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		cancelled = true;
		AuthDialog.this.setVisible(false);
	    }
	});
    }
}