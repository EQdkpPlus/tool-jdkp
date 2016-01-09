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

import com.eqdkplus.jdkp.parse.bind.Response;

@SuppressWarnings("nls")
public class StandardInterface extends GameInterface {

    @Override
    public String format(Response data) {
	// TODO Auto-generated method stub
	return data.toString();
    }

    @Override
    public String getName() {
	// TODO Auto-generated method stub
	return "Standard";
    }

    @Override
    public String getStandardMacExecutable() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getStandardWindowsExecutable() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getStandardMacSavePath() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getStandardWindowsSavePath() {
	// TODO Auto-generated method stub
	return null;
    }
}