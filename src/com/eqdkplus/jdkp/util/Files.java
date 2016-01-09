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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.FileChannel;

public class Files {

    /**
     * Writes a given String into a file.
     * 
     * Use this method to save a single String to file.
     * 
     * @param str
     *            the String to be saved
     * @param filename
     *            the filename of the file where the String should be saved
     * @throws IOException
     *             if the String could not be saved
     */
    public static void writeToFile(String str, String filename, boolean append) throws IOException {
	File file = new File(filename);
	file.createNewFile();
	FileWriter fos = new FileWriter(file, append);
	fos.write(str);
	fos.close();
    }

    public static void copy(File source, File destination) throws IOException {
	FileInputStream fis = new FileInputStream(source);
	FileOutputStream fos = new FileOutputStream(destination);
	FileChannel input = fis.getChannel();
	FileChannel output = fos.getChannel();
	transfer(input, output, source.length());
	fis.close();
	fos.close();
    }

    private static void transfer(FileChannel fileChannel, ByteChannel byteChannel, long length) throws IOException {
	int chunkSize = 1024 * 1024;
	long totalBytesTransfered = 0L;
	while (totalBytesTransfered < length) {
	    long bytesTransfered = 0L;
	    bytesTransfered = fileChannel.transferTo(totalBytesTransfered, Math.min(chunkSize, length
		    - totalBytesTransfered), byteChannel);
	    totalBytesTransfered += bytesTransfered;
	}
    }
}