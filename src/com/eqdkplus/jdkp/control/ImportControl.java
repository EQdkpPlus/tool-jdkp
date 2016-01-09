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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import com.eqdkplus.jdkp.gui.Gui;
import com.eqdkplus.jdkp.gui.Messages;
import com.eqdkplus.jdkp.output.GameInterface;
import com.eqdkplus.jdkp.prf.Profile;
import com.eqdkplus.jdkp.util.Files;
import com.eqdkplus.jdkp.util.reflect.Reflection;


public class ImportControl extends Control<Void, Object> {

    
   
    private List<File> files;
    private final File profileDir;
    private final File interfaceDir;
    private Gui gui;

    public ImportControl(List<File> files, File profileDir, File interfaceDir, Gui gui) {
	this.files = files;
	this.profileDir = profileDir;
	this.interfaceDir = interfaceDir;
	this.gui = gui;
    }

    private Void doImport() throws Exception {
	publish(LOADING);
	for (File f : files) {
	    publish(String.format(Messages.getString("ImportControl.importing"), f.getName())); //$NON-NLS-1$
	    if (f.getParentFile() != null
		    && (f.getParentFile().equals(Control.GAME_INTERFACE_CLASS_PATH) || f.getParentFile().equals(
			    Control.PROFILE_PATH))) {
		publish(FAILED);
		publish(String.format(Messages.getString("ImportControl.importing"), f.getName() + Messages.getString("ImportControl.failed"))); //$NON-NLS-1$ //$NON-NLS-2$
		JOptionPane.showMessageDialog(gui, String.format(
			Messages.getString("ImportControl.cannotImportFrom"), f.getParent()), Messages.getString("Gui.error"), //$NON-NLS-1$ //$NON-NLS-2$
			JOptionPane.ERROR_MESSAGE);
	    } else if (f.getName().endsWith(Profile.FILE_EXTENSION)) {
		File target = new File(profileDir, f.getName());
		Files.copy(f, target);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(target));
		Profile p = (Profile) ois.readObject();
		p.loadInterface();
		gui.loadProfiles();
		ois.close();
		publish(CHECKED);
		publish(String.format(Messages.getString("ImportControl.importSuccess"), p.getName())); //$NON-NLS-1$
	    } else if (f.getName().endsWith(Control.CLASS)) {
		File target = new File(interfaceDir, f.getName());
		Files.copy(f, target);
		addInterfaceFile(target);
		//
		// DEACTIVATED
		// 
		// } else if (f.getName().endsWith(".java")) {
		// File classFile = SourceCompiler.compile(f);
		// File target = new File(interfaceDir, classFile.getName());
		// Files.copy(classFile, target);
		// addInterfaceFile(target);
	    }
	}
	return null;
    }

    @Override
    protected void process(List<Object> states) {
	for (Object o : states) {
	    if (o instanceof Icon) {
		gui.getJob().setIcon((Icon) o);
	    } else if (o instanceof String) {
		gui.getJob().setDescription((String) o);
	    } else {
		throw new AssertionError();
	    }
	}
	gui.getJob().setVisible(true);
	gui.pack();
    }

    private void addInterfaceFile(File f) throws IOException, InstantiationException, IllegalAccessException,
	    ClassNotFoundException {
	Class<?> newInterface = Reflection.getClassFromFile(f);
	if (newInterface == null) {
	    publish(FAILED);
	    publish(String.format(Messages.getString("ImportControl.importing"), f.getName() + Messages.getString("ImportControl.failed"))); //$NON-NLS-1$ //$NON-NLS-2$
	    JOptionPane.showMessageDialog(gui, String.format(Messages
		    .getString("ImportControl.cannotImportAlreadyLoaded"), f.getName()), //$NON-NLS-1$
		    Messages.getString("Gui.error"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
	} else if (GameInterface.class.isAssignableFrom(newInterface)) {
	    GameInterface gi = (GameInterface) newInterface.newInstance();
	    gui.addInterface(gi);
	    publish(CHECKED);
	    publish(String.format(Messages.getString("ImportControl.importSuccess"), gi.getName())); //$NON-NLS-1$
	} else {
	    publish(FAILED);
	    publish(String.format(Messages.getString("ImportControl.importing"), f.getName() + Messages.getString("ImportControl.failed"))); //$NON-NLS-1$ //$NON-NLS-2$
	    JOptionPane.showMessageDialog(gui, String.format(
		    Messages.getString("ImportControl.cannotImportNoSubclass"), f.getName()), Messages.getString("Gui.error"), //$NON-NLS-1$ //$NON-NLS-2$
		    JOptionPane.ERROR_MESSAGE);
	    f.delete();
	}
    }

    @Override
    protected Void doWork() throws Throwable {
	return doImport();
    }
}