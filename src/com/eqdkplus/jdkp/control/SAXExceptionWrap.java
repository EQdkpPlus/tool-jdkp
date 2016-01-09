package com.eqdkplus.jdkp.control;

import org.xml.sax.SAXException;

public class SAXExceptionWrap extends SAXException {

    private static final long serialVersionUID = -5965163090675821176L;

    private String additionalString;
    private Exception exception;

    public SAXExceptionWrap(Exception exception, String msg, String additionalString) {
	super(msg);
	this.exception = exception;
	this.additionalString = additionalString;
    }

    public SAXExceptionWrap(Exception exception, String additionalString) {
	super();
	this.exception = exception;
	this.additionalString = additionalString;
    }

    public String getAdditionalString() {
	return this.additionalString;
    }

    @Override
    public String toString() {
	if (exception != null) {
	    return super.toString() + "\n" + exception.toString() + "\n" + additionalString;
	} else {
	    return super.toString();
	}
    }

}
