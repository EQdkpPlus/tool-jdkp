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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.eqdkplus.jdkp.control.Control;

@SuppressWarnings("serial")
public class ExceptionDialog extends JDialog {

    private static final String ERROR_LOG_PATH = "/error.log"; //$NON-NLS-1$
    private static final char BRACE_OPEN = '[';
    private static final String BRACE_CLOSE = "] "; //$NON-NLS-1$
    private static final String TAB_AT = "\tat "; //$NON-NLS-1$

    private JTextArea detail;
    private JScrollPane detailScrollPane;
    private JPanel okPanel;
    private JButton okButton;
    private JButton cbButton;
    private FileWriter errorFile;

    private void closeAction() throws IOException {
	errorFile = new FileWriter(Control.JDKP_DIR + ERROR_LOG_PATH);
	errorFile.append(this.detail.getText());
	errorFile.close();
	this.setVisible(false);
	this.detail.setText(Control.EMPTY_STRING);
    }

    public ExceptionDialog(Gui gui) {
	super(gui, Control.EMPTY_STRING);
	setLocationByPlatform(true);
	this.detail = new JTextArea();
	this.detailScrollPane = new JScrollPane(detail);
	this.detail.setEditable(false);
	this.okButton = new JButton(Messages.getString("ExceptionDialog.ok")); //$NON-NLS-1$
	this.cbButton = new JButton(Messages.getString("ExceptionDialog.toClipboard")); //$NON-NLS-1$
	this.okButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		try {
		    ExceptionDialog.this.closeAction();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	});
	this.cbButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		try {
		    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(detail.getText()),
			    null);
		    JOptionPane
			    .showMessageDialog(
				    ExceptionDialog.this,
				    Messages.getString("ExceptionDialog.copiedToClipboard"), Messages.getString("ExceptionDialog.done"), JOptionPane.INFORMATION_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (IllegalStateException e) {
		    JOptionPane.showMessageDialog(ExceptionDialog.this, Messages
			    .getString("ExceptionDialog.clipboardUnavailable"), Messages.getString("Gui.error"), //$NON-NLS-1$ //$NON-NLS-2$
			    JOptionPane.ERROR_MESSAGE);
		}
	    }
	});
	this.add(detailScrollPane, BorderLayout.CENTER);
	this.okPanel = new JPanel();
	this.okPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	this.okPanel.add(cbButton);
	this.okPanel.add(okButton);
	this.add(okPanel, BorderLayout.SOUTH);
	this.setPreferredSize(new Dimension(600, 400));
	this.pack();
    }

    public void showExc(Throwable t) {
	this.detail.setText(getExcString(t));
	pack();
	this.setVisible(true);
    }

    public void append(String str) {
	this.detail.append(str);
    }

    private static String getExcString(Throwable t) {
	StringBuilder sb = new StringBuilder(BRACE_OPEN);
	sb.append(DateFormat.getTimeInstance(DateFormat.FULL).format(new Date()));
	sb.append(BRACE_CLOSE);
	sb.append(t.toString());
	sb.append(Control.NEW_LINE);
	for (StackTraceElement ste : t.getStackTrace()) {
	    sb.append(TAB_AT + ste + Control.NEW_LINE);
	}
	return sb.toString();
    }
}