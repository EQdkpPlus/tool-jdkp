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

public abstract class OutputFormatter {

    private int tabDepth = 0;

    public abstract HashMap<Character, Pair<String, Integer>> getReplacements();

    public String tab() {
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < tabDepth; i++) {
	    sb.append('\t');
	}
	return sb.toString();
    }

    public void deltaTab(int delta) {
	tabDepth += delta;
    }

    public void incTab() {
	tabDepth++;
    }

    public void decTab() {
	tabDepth--;
    }

}
