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
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;

/**
 * Class providing a file class loader loading classes from .class files.
 * 
 * @author Henrik Schmutz
 * 
 */
@SuppressWarnings("nls")
public class FileClassLoader extends ClassLoader {

    private static FileClassLoader instance = null;

    private HashSet<String> loadedClasses;

    /**
     * Returns the (only) instance of this class loader.
     * 
     * @return the (only) instance of this class loader.
     */
    public static FileClassLoader getInstance() {
	if (instance == null) {
	    instance = new FileClassLoader();
	}
	return instance;
    }

    private FileClassLoader() {
	super(Thread.currentThread().getContextClassLoader());
	loadedClasses = new HashSet<String>();
    }

    /**
     * Returns a Class object representing the class specified in the given
     * .class file or null if the class was loaded before.
     * 
     * @param file
     *            the source .class file.
     * @return a Class object representing the class specified in the given
     *         .class file or null if the class was loaded before.
     * @throws IOException
     *             if the file could not be read.
     * @throws IllegalArgumentException
     *             if the file has a size > Integer.MAX_VALUE, or if its length
     *             is 0, or it is not a .class file.
     * @throws SecurityException
     *             if the package of the .class file is sealed.
     */
    public Class<?> loadFromFile(File file) throws IOException {
	if (file.length() == 0 || file.length() > Integer.MAX_VALUE) {
	    throw new IllegalArgumentException("bad file size");
	}
	if (!file.getName().endsWith(".class")) {
	    throw new IllegalArgumentException("not a .class file");
	}
	int length = (int) file.length();
	byte[] buf = new byte[length];
	FileInputStream fis = new FileInputStream(file);
	fis.read(buf);
	fis.close();
	ClassParser cp = new ClassParser(file.getAbsolutePath());
	JavaClass jc = cp.parse();
	Class<?> c = null;
	if (!loadedClasses.contains(jc.getClassName())) {
	    c = defineClass(jc.getClassName(), buf, 0, length);
	    loadedClasses.add(jc.getClassName());
	    if (c.getPackage() != null && c.getPackage().isSealed()) {
		throw new SecurityException("sealing violation: package " + c.getPackage().getName() + " is sealed.");
	    }
	}
	return c;
    }
}