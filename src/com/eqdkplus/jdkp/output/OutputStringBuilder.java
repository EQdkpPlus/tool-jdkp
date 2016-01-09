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

public class OutputStringBuilder {

    private static final String NEW_LINE="\n"; //$NON-NLS-1$
    
    private StringBuilder sb;
    private OutputFormatter of;
    
    public OutputStringBuilder(OutputFormatter of) {
	this.sb = new StringBuilder();
	this.of = of;
    }

    public OutputStringBuilder append(String str) {
	boolean match = false;
	if (str.length() > 0) {
	    char lastChar = str.charAt(str.length() - 1);
	    for (char c : of.getReplacements().keySet()) {
		if (c == lastChar) {
		    match = true;
		    of.deltaTab(of.getReplacements().get(c).getV2());
		    String repl = of.getReplacements().get(c).getV1().replace(NEW_LINE, NEW_LINE + of.tab());
		    sb.append(str.substring(0, str.length() - 1)).append(repl);
		    break;
		}
	    }
	    if (!match) {
		sb.append(str);
	    }
	}
	return this;
    }

    public OutputStringBuilder append(char c) {
	boolean match = false;
	for (char d : of.getReplacements().keySet()) {
	    if (d == c) {
		match = true;
		of.deltaTab(of.getReplacements().get(d).getV2());
		String repl = of.getReplacements().get(d).getV1().replace(NEW_LINE, NEW_LINE + of.tab());
		sb.append(repl);
		break;
	    }
	}
	if (!match) {
	    sb.append(c);
	}
	return this;
    }

    public OutputStringBuilder append(long l) {
	sb.append(l);
	return this;
    }

    public OutputStringBuilder append(int i) {
	sb.append(i);
	return this;
    }

    public OutputStringBuilder append(double d) {
	sb.append(d);
	return this;
    }

    @Override
    public String toString() {
	return sb.toString();
    }
}