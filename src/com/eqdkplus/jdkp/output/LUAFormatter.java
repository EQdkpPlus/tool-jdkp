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

public class LUAFormatter extends OutputFormatter {

    private HashMap<Character, Pair<String, Integer>> repl;

    @Override
    public HashMap<Character, Pair<String, Integer>> getReplacements() {
	if (repl == null) {
	    repl = new HashMap<Character, Pair<String, Integer>>();
	    repl.put('{', new Pair<String, Integer>("{\n", 1)); //$NON-NLS-1$
	    repl.put('}', new Pair<String, Integer>("}\n", -1)); //$NON-NLS-1$
	    repl.put(',', new Pair<String, Integer>(",\n", 0)); //$NON-NLS-1$
	}
	return repl;
    }

}
