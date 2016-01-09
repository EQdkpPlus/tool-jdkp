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

import java.io.File;
import java.io.StringReader;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Parser {

    @SuppressWarnings("unchecked")
    public static <T> T parse(URL xml, URL xsd, Class<T> clazz) throws JAXBException, SAXException {
	// safe cast because unmarshal returns T
	return (T) setSchema(getUnmarshaller(clazz), getSchema(xsd)).unmarshal(xml);
    }

    @SuppressWarnings("unchecked")
    public static <T> T parse(File xml, File xsd, Class<T> clazz) throws JAXBException, SAXException {
	// safe cast because unmarshal returns T
	return (T) setSchema(getUnmarshaller(clazz), getSchema(xsd)).unmarshal(xml);
    }

    @SuppressWarnings("unchecked")
    public static <T> T parse(String xml, URL xsd, Class<T> clazz) throws JAXBException, SAXException {
	// safe cast because unmarshal returns T
	return (T) setSchema(getUnmarshaller(clazz), getSchema(xsd)).unmarshal(new InputSource(new StringReader(xml)));
    }

    private static <T> Unmarshaller getUnmarshaller(Class<T> clazz) throws JAXBException, SAXException {
	return JAXBContext.newInstance(clazz.getPackage().getName()).createUnmarshaller();
    }

    private static Unmarshaller setSchema(Unmarshaller um, Schema schema) {
	um.setSchema(schema);
	return um;
    }

    private static Schema getSchema(URL xsd) throws SAXException {
	return SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(xsd);
    }

    private static Schema getSchema(File xsd) throws SAXException {
	return SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(xsd);
    }
}