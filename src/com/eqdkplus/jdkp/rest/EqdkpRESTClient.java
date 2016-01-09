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

package com.eqdkplus.jdkp.rest;

import java.net.MalformedURLException;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import com.eqdkplus.jdkp.control.EQDKPException;
import com.eqdkplus.jdkp.parse.Parser;
import com.eqdkplus.jdkp.parse.XSD;
import com.eqdkplus.jdkp.prf.Profile;
import com.eqdkplus.jdkp.rest.bind.Config;
import com.eqdkplus.jdkp.rest.bind.Response;
import com.eqdkplus.jdkp.util.Pair;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;

@SuppressWarnings("nls")
public class EqdkpRESTClient {

    private Client client;
    private WebResource wr;
    private boolean open;
    private Profile p;

    /**
     * Creates a new Eqdkp REST client.
     * 
     * @param url
     *            the URL to api.php.
     * @throws MalformedURLException
     *             if another protocol as http or https is specified or the
     *             resource is not an api.php.
     */
    public EqdkpRESTClient(Profile p) throws MalformedURLException {
	if (!p.getEqdkpURL().getProtocol().equals("http") && !p.getEqdkpURL().getProtocol().equals("https")) {
	    throw new MalformedURLException("bad protocol");
	}
	if (!p.getEqdkpURL().getPath().endsWith("api.php")) {
	    throw new MalformedURLException("must point to api.php");
	}
	this.p = p;
	client = new Client();
	wr = client.resource(
		p.getEqdkpURL().getProtocol() + "://" + p.getEqdkpURL().getHost() + p.getEqdkpURL().getPath());
	open = true;
    }

    public void close() {
	client.destroy();
	open = false;
    }

    /**
     * Returns the salt of the specified user.
     * 
     * @param user
     *            username of the user for which the salt should be returned.
     * @return the salt of the specified user.
     * @throws SAXException
     * @throws JAXBException
     * @throws EQDKPException 
     * @throws MalformedURLException
     */
    public String getSalt(String user) throws JAXBException, SAXException, EQDKPException {
	if (!open) {
	    throw new IllegalStateException("client is already closed.");
	}
	Response re = query("get_salt", "<request><user>" + user + "</user></request>");
	if (re.getStatus().equals("0")) {
	    throw new EQDKPException(re.getError());
	}
	return Base64.base64Decode(re.getSalt());
    }

    /**
     * Returns a Pair(sid,sid_end) for the specified login, both empty if the
     * login failed.
     * 
     * @param user
     *            username
     * @param pw
     *            password
     * @return a Pair(sid,sid_end) for the specified login, both empty if the
     *         login failed.
     * @throws MalformedURLException
     * @throws JAXBException
     * @throws SAXException
     * @throws EQDKPException 
     */
    public Pair<String, Long> login() throws JAXBException, SAXException, EQDKPException {
	if (!open) {
	    throw new IllegalStateException("client is already closed.");
	}
	Response re = query("login", "<request><user>" + p.getUsername() + "</user><password>" + p.getPassword()
		+ "</password></request>");
	if (re.getStatus().equals("0")) {
	    if (re.getError().equals("access denied")) {
		return new Pair<String,Long>("0",0L);
	    }
	    throw new EQDKPException(re.getError());
	}
	return new Pair<String, Long>(re.getSid(), re.getEnd());
    }

    private Response query(String function, String xml) throws JAXBException, SAXException, EQDKPException {
	String out = wr.queryParam("function", function).entity(xml).post(String.class);
	try {
	    String status = Parser.parse(out, XSD.getRESTXSDURL(p.getEqdkpURL()), Config.class).getEqdkp().getStatus();
	    if (status.equals("maintenance")) {
		throw new EQDKPException("EQDKP is in maintenance mode.");
	    }
	} catch (ClassCastException e) {
	    // proceed
	}
	return Parser.parse(out, XSD.getRESTXSDURL(p.getEqdkpURL()), Response.class);
    }

    public void logout(String sid) throws JAXBException, SAXException, EQDKPException {
	if (!open) {
	    throw new IllegalStateException("client is already closed.");
	}
	query("logout", "<request><sid>" + sid + "</sid></request>");
    }
}