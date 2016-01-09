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

import com.eqdkplus.jdkp.control.Control;
import com.eqdkplus.jdkp.parse.bind.Response;

/**
 * Subclasses must have an accessible nullary constructor.
 * 
 * @author Henrik_2
 * 
 */
public abstract class GameInterface implements Comparable<GameInterface> {

    /**
     * Converts the given Config struct into a String that fits the target
     * platform constraints.
     * 
     * @param data
     *            the data object retrieved from the XML.
     * @return the formatted String.
     */
    public abstract String format(Response data);

    /**
     * Returns the name of this output formatter.
     * 
     * @return the name of this output formatter.
     */
    public abstract String getName();

    /**
     * Returns the standard executable String on a Windows machine.
     * 
     * @return the standard executable String on a Windows machine, null if no
     *         such standard exists or it could not be determined.
     */
    protected abstract String getStandardWindowsExecutable();

    /**
     * Returns the standard executable String on a Mac machine.
     * 
     * @return the standard executable String on a Mac machine, null if no such
     *         standard exists or it could not be determined.
     */
    protected abstract String getStandardMacExecutable();

    /**
     * Returns the standard executable String on other machine.
     * 
     * @return the standard executable String on other machine, null if no such
     *         standard exists or it could not be determined.
     */
    protected String getDefaultExecutable() {
	return null;
    }

    /**
     * Returns the standard executable String for the running OS.
     * 
     * @return the standard executable String for the running OS, null if no
     *         such standard exists or it could not be determined.
     */
    public final String getPlatformExecutable() {
	if (System.getProperty("os.name").startsWith(Control.WINDOWS)) { //$NON-NLS-1$
	    return getStandardWindowsExecutable();
	}
	if (System.getProperty("os.name").startsWith(Control.MAC_OS)) { //$NON-NLS-1$
	    return getStandardMacExecutable();
	}
	return getDefaultExecutable();
    }

    /**
     * Returns the standard save path of the XML data on a Windows machine.
     * 
     * @return the standard save path of the XML data on a Windows machine, null
     *         if no such standard exists or it could not be determined.
     */
    protected abstract String getStandardWindowsSavePath();

    /**
     * Returns the standard save path of the XML data on a Mac machine.
     * 
     * @return the standard save path of the XML data on a Mac machine, null if
     *         no such standard exists or it could not be determined.
     */
    protected abstract String getStandardMacSavePath();

    /**
     * Returns the standard save path of the XML data on other machine.
     * 
     * @return the standard save path of the XML data on other machine, null if
     *         no such standard exists or it could not be determined.
     */
    protected String getDefaultSavePath() {
	return null;
    }

    /**
     * Returns the standard save path of the XML data for the running OS.
     * 
     * @return the standard save path of the XML data for the running OS, null
     *         if no such standard exists or it could not be determined.
     */
    public final String getPlatformSavePath() {
	if (System.getProperty("os.name").startsWith(Control.WINDOWS)) { //$NON-NLS-1$
	    return getStandardWindowsSavePath();
	}
	if (System.getProperty("os.name").startsWith(Control.MAC_OS)) { //$NON-NLS-1$
	    return getStandardMacSavePath();
	}
	return getDefaultSavePath();
    }

    /**
     * Returns true, if o is not null and o is of the same class as this, false
     * otherwise.
     * 
     * @return true, if o is not null and o is of the same class as this, false
     *         otherwise.
     */
    @Override
    public final boolean equals(Object o) {
	return this.getClass().equals(o.getClass());
    }

    @Override
    public final String toString() {
	return this.getName();
    }

    private int compareToPackage() {
	return GameInterface.class.getPackage().equals(this.getClass().getPackage()) ? 0 : 1;
    }

    @Override
    public final int compareTo(GameInterface gi) {
	return this.compareToPackage() - gi.compareToPackage();
    }
}