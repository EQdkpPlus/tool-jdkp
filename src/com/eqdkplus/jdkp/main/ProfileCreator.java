package com.eqdkplus.jdkp.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import com.eqdkplus.jdkp.control.Control;
import com.eqdkplus.jdkp.control.DownloadControl;
import com.eqdkplus.jdkp.control.EQDKPException;
import com.eqdkplus.jdkp.output.WoWGetDKPClassicInterface;
import com.eqdkplus.jdkp.prf.Profile;

public class ProfileCreator {

    /**
     * @param args
     * @throws IOException 
     * @throws EQDKPException 
     * @throws SAXException 
     * @throws JAXBException 
     * @throws MalformedURLException 
     * @throws FileNotFoundException 
     * @throws NoSuchAlgorithmException 
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, MalformedURLException, JAXBException, SAXException, EQDKPException, IOException {
	// TODO Auto-generated method stub
	Profile p = Profile.getProfile("Invictus", new URL("http://invictus-xv.de/eqdkp/api.php"), DownloadControl.DEFAULT_CONNECTION_TIMEOUT_MS, Control.CHARSET, new File("X:\\World of Warcraft\\Interface\\addons\\GetDKP\\dkp_list.lua"), new WoWGetDKPClassicInterface(), null);
	p.save(Control.PROFILE_PATH,true);
	System.out.print("Profile created and saved at: ");
	System.out.println(Control.PROFILE_PATH.getAbsolutePath() + '/' + p.getName() + Profile.FILE_EXTENSION);
	System.out.println(p.getInfo());
    }

}
