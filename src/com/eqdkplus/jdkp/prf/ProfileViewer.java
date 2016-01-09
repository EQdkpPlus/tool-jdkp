package com.eqdkplus.jdkp.prf;

import java.io.IOException;

import com.eqdkplus.jdkp.control.Control;

public class ProfileViewer {
    public static void main(String[] args) throws IOException {
	Profile[] profiles = Profile.load(Control.PROFILE_PATH);
	for (Profile p : profiles) {
	    System.out.println(p.getInfo());
	    System.out.println("Password: "+p.getPassword());
	}
    }
}
