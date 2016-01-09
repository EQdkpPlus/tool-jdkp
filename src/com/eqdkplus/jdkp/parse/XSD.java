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

package com.eqdkplus.jdkp.parse;

import java.net.MalformedURLException;
import java.net.URL;

public final class XSD {

    private static final String REST_XSD_PATH = "/core/xsd/rest.xsd"; //$NON-NLS-1$
    private static final String DKP_XSD_PATH = "/core/xsd/data_export.xsd"; //$NON-NLS-1$

    public static URL getRESTXSDURL(URL root) {
	// return getXSDURL(root, REST_XSD_PATH);
	return XSD.class.getClassLoader().getResource("res/xsd/rest.xsd"); //$NON-NLS-1$
    }

    public static URL getDKPXSDURL(URL root) {
	//return getXSDURL(root, DKP_XSD_PATH);
	return XSD.class.getClassLoader().getResource("res/xsd/data_export.xsd"); //$NON-NLS-1$
    }

    private static URL getXSDURL(URL root, String XSDPath) {
	try {
	    return new URL(root.getProtocol(), root.getHost(), root.getPath().substring(0,
		    root.getPath().lastIndexOf('/'))
		    + XSDPath);
	} catch (MalformedURLException e) {
	    // can't happen
	    e.printStackTrace();
	    return null;
	}
    }
}
