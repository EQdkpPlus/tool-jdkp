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

package com.eqdkplus.jdkp.util.reflect;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.eqdkplus.jdkp.control.Control;

@SuppressWarnings("nls")
public class Reflection {

    /**
     * Loads classes in the specified directory and returns an array holding an
     * instance of every class.
     * 
     * To be instantiated by this method, those classes must (i) lay in the
     * default package, (ii) provide access to a nullary constructor, (iii) be a
     * subclass of the given superClass.
     * 
     * @param dir
     *            the directory to check.
     * @param superClass
     *            the common super class of every class to instantiate.
     * @return an instance of every class.
     */
    public static Object[] getClassInstances(File dir, Class<?> superClass) {
	URLClassLoader ucl = null;
	try {
	    ucl = new URLClassLoader(new URL[] { new URL("file:/" + dir.getAbsolutePath()) });
	} catch (MalformedURLException e) {
	    // can't happen
	    throw new AssertionError();
	}
	String[] classFiles = dir.list(new FilenameFilter() {
	    @Override
	    public boolean accept(File dir, String name) {
		return name.endsWith(Control.CLASS);
	    }
	});
	Object[] objects = new Object[classFiles.length];
	int count = 0;
	for (int i = 0; i < classFiles.length; i++) {
	    try {
		Object instance = ucl.loadClass(classFiles[i].substring(0, classFiles[i].lastIndexOf('.')))
			.newInstance();
		if (superClass.isAssignableFrom(instance.getClass())) {
		    objects[count] = instance;
		    count++;
		}
		objects[count] = instance;
	    } catch (Exception e) {
		// simply ignore this class...
	    }
	}
	Object[] toReturn;
	if (count < classFiles.length) {
	    toReturn = new Object[count];
	    System.arraycopy(objects, 0, toReturn, 0, count);
	} else {
	    toReturn = objects;
	}
	try {
	    ucl.close();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return toReturn;
    }

    public static Class<?> getClassFromFile(File file) throws ClassNotFoundException, IOException {
	return FileClassLoader.getInstance().loadFromFile(file);
    }
}