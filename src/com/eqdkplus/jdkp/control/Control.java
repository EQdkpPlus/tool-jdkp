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

package com.eqdkplus.jdkp.control;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

public abstract class Control<T, V> extends SwingWorker<T, V> {

    public static final File JDKP_DIR = new File(System.getProperty("user.home") + '/' + "jdkp"); //$NON-NLS-1$ //$NON-NLS-2$
    public static final File PROFILE_PATH = new File(JDKP_DIR.getAbsolutePath() + "/profiles"); //$NON-NLS-1$
    public static final File GAME_INTERFACE_CLASS_PATH = new File(JDKP_DIR.getAbsolutePath() + "/interfaces"); //$NON-NLS-1$

    public static final String CHARSET = "utf8"; //$NON-NLS-1$
    public static final String EMPTY_STRING = ""; //$NON-NLS-1$
    public static final char NEW_LINE = '\n';
    public static final String CLASS=".class"; //$NON-NLS-1$
    public static final String LAST = "$last$"; //$NON-NLS-1$
    public static final String NULL = "null"; //$NON-NLS-1$
    

    public static final int DEFAULT_CONNECTION_TIMEOUT_MS = 10000;

    public static final Icon CHECKED = new ImageIcon(Control.class.getClassLoader().getResource("res/checked.png")); //$NON-NLS-1$
    public static final Icon FAILED = new ImageIcon(Control.class.getClassLoader().getResource("res/failed.png")); //$NON-NLS-1$
    public static final Icon LOADING = new ImageIcon(Control.class.getClassLoader().getResource("res/loading.gif")); //$NON-NLS-1$

    public static final Icon USERICON = new ImageIcon(Control.class.getClassLoader().getResource("res/user.png")); //$NON-NLS-1$
    public static final Icon PASSICON = new ImageIcon(Control.class.getClassLoader().getResource("res/pass.png")); //$NON-NLS-1$
    public static final Icon LOCK = new ImageIcon(Control.class.getClassLoader().getResource("res/lock.png")); //$NON-NLS-1$
    public static final Icon LOCK_OPEN = new ImageIcon(Control.class.getClassLoader().getResource("res/lock_open.png")); //$NON-NLS-1$
    public static final Icon CLOCK = new ImageIcon(Control.class.getClassLoader().getResource("res/clock.png")); //$NON-NLS-1$
    public static final Icon PROFILEICON = new ImageIcon(Control.class.getClassLoader().getResource("res/profile.png")); //$NON-NLS-1$
    public static final Icon DELETEICON = new ImageIcon(Control.class.getClassLoader().getResource("res/delete.png")); //$NON-NLS-1$
    public static final Icon REMOTE_ICON = new ImageIcon(Control.class.getClassLoader().getResource("res/remote.png")); //$NON-NLS-1$
    public static final Icon LOCAL_ICON = new ImageIcon(Control.class.getClassLoader().getResource("res/local.png")); //$NON-NLS-1$
    public static final Icon AUTH_ICON = new ImageIcon(Control.class.getClassLoader().getResource("res/auth.png")); //$NON-NLS-1$
    public static final Icon FORMAT_ICON = new ImageIcon(Control.class.getClassLoader().getResource("res/format.png")); //$NON-NLS-1$
    public static final Icon EXECUTE_ICON = new ImageIcon(Control.class.getClassLoader().getResource("res/execute.png")); //$NON-NLS-1$
    public static final Icon ADVANCED_ICON = new ImageIcon(Control.class.getClassLoader().getResource("res/advanced.png")); //$NON-NLS-1$
    public static final Icon FOLDER_ICON = new ImageIcon(Control.class.getClassLoader().getResource("res/folder.png")); //$NON-NLS-1$
    public static final Icon DEFAULT_ICON = new ImageIcon(Control.class.getClassLoader().getResource("res/default.png")); //$NON-NLS-1$

    protected Throwable pendingErr;
    public static final String MAC_OS = "Mac OS"; //$NON-NLS-1$
    public static final String WINDOWS = "Windows"; //$NON-NLS-1$
    public static final String HTML_CLOSE_TAG = "</html>"; //$NON-NLS-1$
    public static final String HTML_BREAK_TAG = "<br>"; //$NON-NLS-1$
    public static final String HTML_OPEN_TAG = "<html>"; //$NON-NLS-1$
    
    

    @Override
    protected T doInBackground() throws Exception {
	T ret = null;
	try {
	    ret = doWork();
	} catch (Throwable t) {
	    pendingErr = t;
	}
	return ret;
    }

    public Throwable getPendingErr() {
	return pendingErr;
    }

    protected abstract T doWork() throws Throwable;

    @Override
    protected void done() {
	if (pendingErr != null) {
	    pendingErr.printStackTrace();
	}
    }
}