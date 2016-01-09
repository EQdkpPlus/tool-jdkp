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

package com.eqdkplus.jdkp.gui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.TransferHandler;

import com.eqdkplus.jdkp.control.ImportControl;

@SuppressWarnings("serial")
public class JDKPTransferHandler extends TransferHandler {

    private Gui gui;
    private File profileDir;
    private File interfaceDir;

    public JDKPTransferHandler(Gui gui, File profileDir, File interfaceDir) {
	super();
	this.gui = gui;
	this.profileDir = profileDir;
	this.interfaceDir = interfaceDir;
    }

    @Override
    public boolean canImport(TransferSupport support) {
	return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
    }

    @Override
    public boolean importData(TransferSupport support) {
	if (!canImport(support)) {
	    return false;
	}

	Transferable t = support.getTransferable();
	List<File> l = null;
	try {
	    l = getFiles(t);
	} catch (UnsupportedFlavorException e) {
	    return false;
	} catch (IOException e) {
	    return false;
	}
	if (l == null) {
	    return false;
	}
	ImportControl imp = new ImportControl(l, profileDir, interfaceDir, gui);
	imp.execute();
	return true;
    }

    @SuppressWarnings("unchecked")
    private static List<File> getFiles(Transferable t) throws UnsupportedFlavorException, IOException {
	return (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
    }
}