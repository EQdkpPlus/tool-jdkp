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

public class Pair<P, Q> {

    private P v1;
    private Q v2;

    public Pair(P v1, Q v2) {
	this.v1 = v1;
	this.v2 = v2;
    }

    public P getV1() {
	return v1;
    }

    public void setV1(P v1) {
	this.v1 = v1;
    }

    public Q getV2() {
	return v2;
    }

    public void setV2(Q v2) {
	this.v2 = v2;
    }
}