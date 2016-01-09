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

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.eqdkplus.jdkp.control.Control;
import com.eqdkplus.jdkp.output.GameInterface;

@SuppressWarnings("serial")
public class FormatComboBoxRenderer extends JLabel implements ListCellRenderer<GameInterface> {

    public FormatComboBoxRenderer() {
	setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends GameInterface> list, GameInterface value, int index, boolean isSelected,
	    boolean cellHasFocus) {

	if (isSelected) {
	    setBackground(list.getSelectionBackground());
	    setForeground(list.getSelectionForeground());
	} else {
	    setBackground(list.getBackground());
	    setForeground(list.getForeground());
	}

	if (value != null) {
	    setText(value.toString());
	    if (!GameInterface.class.getPackage().equals(value.getClass().getPackage())) {
		setFont(list.getFont().deriveFont(Font.ITALIC));
		setIcon(Control.LOCK_OPEN);
	    } else {
		setFont(list.getFont().deriveFont(Font.BOLD));
		setIcon(Control.LOCK);
	    }
	}
	return this;
    }
}