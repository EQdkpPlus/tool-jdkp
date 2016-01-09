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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.eqdkplus.jdkp.control.Control;

@SuppressWarnings("serial")
public class Job extends JPanel {

    private JLabel icon;
    private JLabel description;
    private JProgressBar progressBar;
    private int progress;

    public Job() {
	this(null, Control.EMPTY_STRING, -1);
    }

    public Job(Icon icon, String description, int maxProgress) {
	super();

	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	this.setLayout(gbl);

	this.icon = new JLabel(icon);
	this.description = new JLabel(description);
	this.progressBar = new JProgressBar();
	this.progressBar.setMaximum(maxProgress);
	this.progress = maxProgress;
	this.progressBar.setVisible(maxProgress == -1 ? false : true);

	gbc.insets = new Insets(5, 5, 5, 5);
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.weighty = 0;

	gbl.setConstraints(this.icon, gbc);
	add(this.icon);

	gbc.weightx = 1;
	gbl.setConstraints(this.description, gbc);
	add(this.description);

	gbc.weightx = 0;
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbl.setConstraints(progressBar, gbc);
	add(this.progressBar);

    }

    /**
     * @return the icon
     */
    public Icon getIcon() {
	return this.icon.getIcon();
    }

    /**
     * @param icon
     *            the icon to set
     */
    public void setIcon(Icon icon) {
	this.icon.setIcon(icon);
    }

    /**
     * @return the description
     */
    public String getDescription() {
	return this.description.getText();
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
	this.description.setText(description);
    }

    /**
     * @return whether the progress bar is visible or not.
     */
    public boolean isProgressBarVisible() {
	return this.progressBar.isVisible();
    }

    public void setProgressBarVisible(boolean val) {
	this.progressBar.setVisible(val);
    }

    /**
     * @return the progress
     */
    public int getProgress() {
	return this.progress;
    }

    /**
     * @param progress
     *            the progress to set
     */
    public void setProgress(int progress) {
	this.progress = progress;
    }

    /**
     * @return the progressBar
     */
    public JProgressBar getProgressBar() {
	return progressBar;
    }
}