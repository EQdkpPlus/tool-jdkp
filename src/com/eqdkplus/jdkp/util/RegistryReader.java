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

package com.eqdkplus.jdkp.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.prefs.Preferences;

import com.eqdkplus.jdkp.control.Control;

public final class RegistryReader {

    public static final int HKEY_CURRENT_USER = 0x80000001;
    public static final int HKEY_LOCAL_MACHINE = 0x80000002;

    private static final int REG_SUCCESS = 0;

    private static final int KEY_READ = 0x20019;

    private static Preferences userRoot = Preferences.userRoot();
    private static Preferences systemRoot = Preferences.systemRoot();

    private static Method regOpenKey = null;
    private static Method regCloseKey = null;
    private static Method regQueryValueEx = null;

    static {
	try {
	    regOpenKey = Preferences.userRoot().getClass().getDeclaredMethod("WindowsRegOpenKey", //$NON-NLS-1$
		    new Class[] { int.class, byte[].class, int.class });
	    regCloseKey = Preferences.userRoot().getClass().getDeclaredMethod("WindowsRegCloseKey", //$NON-NLS-1$
		    new Class[] { int.class });
	    regQueryValueEx = Preferences.userRoot().getClass().getDeclaredMethod("WindowsRegQueryValueEx", //$NON-NLS-1$
		    new Class[] { int.class, byte[].class });
	    regOpenKey.setAccessible(true);
	    regCloseKey.setAccessible(true);
	    regQueryValueEx.setAccessible(true);
	} catch (NoSuchMethodException e) {
	    e.printStackTrace();
	}
    }

    private RegistryReader() {
    }

    public static String readString(int hkey, String key, String valueName) throws IllegalArgumentException,
	    IllegalAccessException, InvocationTargetException {
	if (hkey == HKEY_LOCAL_MACHINE) {
	    return readString(systemRoot, hkey, key, valueName);
	} else if (hkey == HKEY_CURRENT_USER) {
	    return readString(userRoot, hkey, key, valueName);
	} else {
	    throw new IllegalArgumentException("hkey=" + hkey); //$NON-NLS-1$
	}
    }

    private static String readString(Preferences root, int hkey, String key, String value)
	    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
	int[] handles = (int[]) regOpenKey.invoke(root, new Object[] { new Integer(hkey), toCstr(key),
		new Integer(KEY_READ) });
	if (handles[1] != REG_SUCCESS) {
	    return Control.EMPTY_STRING;
	}
	byte[] valb = (byte[]) regQueryValueEx.invoke(root, new Object[] { new Integer(handles[0]), toCstr(value) });
	regCloseKey.invoke(root, new Object[] { new Integer(handles[0]) });
	return (valb != null ? new String(valb).trim() : Control.EMPTY_STRING);
    }

    private static byte[] toCstr(String str) {
	byte[] result = new byte[str.length() + 1];

	for (int i = 0; i < str.length(); i++) {
	    result[i] = (byte) str.charAt(i);
	}
	result[str.length()] = 0;
	return result;
    }
}