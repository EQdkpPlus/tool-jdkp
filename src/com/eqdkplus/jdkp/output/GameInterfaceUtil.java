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

package com.eqdkplus.jdkp.output;

import java.io.File;
import java.io.FilenameFilter;

import com.eqdkplus.jdkp.util.reflect.FileClassLoader;

/**
 * Utility class for game interfaces
 * 
 * @author Henrik Schmutz
 * 
 */
public final class GameInterfaceUtil {

    private static final GameInterface[] STANDARD_INTERFACES = new GameInterface[] {
	    new com.eqdkplus.jdkp.output.StandardInterface(), new com.eqdkplus.jdkp.output.WoWGetDKPInterface(), new com.eqdkplus.jdkp.output.WoWGetDKPClassicInterface() };

    public static final GameInterface[] getStandardInterfaces() {
	return STANDARD_INTERFACES;
    }

    // prevents instantiation
    private GameInterfaceUtil() {
    }

    /**
     * Loads instances of GameInterface from .class files inside the specified
     * directory.
     * 
     * @param dir
     *            the directory to scan for class files.
     * @return an array that contains an instance of every GameInterface found.
     */
    public static GameInterface[] loadFromDir(File dir) {
	File[] files = dir.listFiles(new FilenameFilter() {
	    @Override
	    public boolean accept(File dir, String name) {
		return name.endsWith(".class"); //$NON-NLS-1$
	    }
	});
	GameInterface[] instances = new GameInterface[files.length];
	int count = 0;
	for (File f : files) {
	    Class<?> c;
	    try {
		c = FileClassLoader.getInstance().loadFromFile(f);
	    } catch (Exception e) {
		// simply ignore this file...
		continue;
	    }
	    if (GameInterface.class.isAssignableFrom(c)) {
		try {
		    instances[count] = (GameInterface) c.newInstance();
		    count++;
		} catch (Exception e) {
		    // simply ignore it then...
		}
	    }
	}
	if (count != files.length) {
	    GameInterface[] toReturn = new GameInterface[count];
	    System.arraycopy(instances, 0, toReturn, 0, count);
	    return toReturn;
	}
	return instances;
    }
}
