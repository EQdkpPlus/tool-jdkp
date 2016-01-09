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

import java.util.HashMap;

import com.eqdkplus.jdkp.util.Pair;

public class StandardOutputFormatter extends OutputFormatter {

    HashMap<Character, Pair<String, Integer>> repl = new HashMap<Character, Pair<String, Integer>>();
    
    @Override
    public HashMap<Character, Pair<String, Integer>> getReplacements() {
	return repl;
    }

}
