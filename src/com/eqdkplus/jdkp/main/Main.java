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

package com.eqdkplus.jdkp.main;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.SwingUtilities;

import com.eqdkplus.jdkp.control.Control;
import com.eqdkplus.jdkp.gui.ExceptionDialog;
import com.eqdkplus.jdkp.gui.Gui;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
	Gui gui = null;
	final ExceptionDialog ed = new ExceptionDialog(gui);

	System.setErr(new PrintStream(new OutputStream() {
	    StringBuilder sb = new StringBuilder();

	    @Override
	    public void write(int b) {
		ed.setVisible(true);
		sb.append((char) b);
		if ((char) b == '\n') {
		    ed.append(sb.toString());
		    sb.delete(0, sb.length());
		}
	    }
	}));
	class Starter implements Runnable {
	    private Gui gui;

	    Starter(Gui gui) {
		this.gui = gui;
	    }

	    @Override
	    public void run() {
		try {
		    gui = new Gui();
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		gui.setVisible(true);
	    }
	}
	Control.GAME_INTERFACE_CLASS_PATH.mkdirs();
	Control.PROFILE_PATH.mkdirs();
	SwingUtilities.invokeLater(new Starter(gui));
    }
}