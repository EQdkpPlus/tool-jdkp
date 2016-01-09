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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.eqdkplus.jdkp.parse.bind.Event;
import com.eqdkplus.jdkp.parse.bind.Item;
import com.eqdkplus.jdkp.parse.bind.Itempool;
import com.eqdkplus.jdkp.parse.bind.MultidkpPoints;
import com.eqdkplus.jdkp.parse.bind.MultidkpPool;
import com.eqdkplus.jdkp.parse.bind.Player;
import com.eqdkplus.jdkp.parse.bind.Response;
import com.eqdkplus.jdkp.util.RegistryReader;

@SuppressWarnings("nls")
public class WoWGetDKPInterface extends GameInterface {

    protected static final String SEND = "\",";
    protected static final char END = ',';

    private static final String XML_SAVE_PATH = "/Interface/Addons/GetDKP/GetDKPData/dkp_list.lua";

    protected OutputStringBuilder osb;

    private String standardWindowsExe;

    public WoWGetDKPInterface() {
	osb = new OutputStringBuilder(new LUAFormatter());
    }

    @Override
    public String format(Response data) {
	osb.append("getdkp_data = {");
	tfield("eqdkp");
	sfield("name").append(data.getEqdkp().getName()).append(SEND);
	sfield("guild").append(data.getEqdkp().getGuild()).append(SEND);
	sfield("dkp_name").append(data.getEqdkp().getDkpName()).append(SEND);
	sfield("version").append(data.getEqdkp().getVersion()).append(SEND);
	sfield("layout").append(data.getEqdkp().getLayout()).append(SEND);
	tend();
	tfield("game");
	sfield("name").append(data.getGame().getName()).append(SEND);
	sfield("version").append(data.getGame().getVersion()).append(SEND);
	sfield("language").append(data.getGame().getLanguage()).append(SEND);
	sfield("server_name").append(data.getGame().getServerName()).append(SEND);
	sfield("server_loc").append(data.getGame().getServerLoc()).append(SEND);
	tend();
	tfield("info");
	sfield("date").append(data.getInfo().getDate()).append(SEND);
	field("timestamp").append(data.getInfo().getTimestamp()).append(END);
	field("total_players").append(data.getInfo().getTotalPlayers()).append(END);
	field("total_items").append(data.getInfo().getTotalItems()).append(END);
	tend();
	tfield("players");
	for (Player p : data.getPlayers().getPlayer()) {
	    tfield(p.getName());
	    field("id").append(p.getId()).append(END);
	    sfield("name").append(p.getName()).append(SEND);
	    field("active").append(p.getActive()).append(END);
	    field("hidden").append(p.getHidden()).append(END);
	    field("main_id").append(p.getMainId()).append(END);
	    sfield("main_name").append(p.getMainName()).append(SEND);
	    field("class_id").append(p.getClassId()).append(END);
	    sfield("class_name").append(p.getClassName()).append(SEND);
	    tfield("points");
	    for (MultidkpPoints mp : p.getPoints().getMultidkpPoints()) {
		itfield(mp.getMultidkpId());
		field("multidkp_id").append(mp.getMultidkpId()).append(END);
		field("points_current").append(mp.getPointsCurrent()).append(END);
		field("points_earned").append(mp.getPointsEarned()).append(END);
		field("points_spent").append(mp.getPointsSpent()).append(END);
		tend();
	    }
	    tend();
	    HashMap<String, List<Item>> idItems = new HashMap<String, List<Item>>();
	    HashMap<String, List<Item>> noIdItems = new HashMap<String, List<Item>>();
	    for (Item i : p.getItems().getItem()) {
		if (!i.getGameId().equals("0")) {
		    if (idItems.containsKey(i.getGameId())) {
			idItems.get(i.getGameId()).add(i);
		    } else {
			List<Item> newList = new ArrayList<Item>();
			newList.add(i);
			idItems.put(i.getGameId(), newList);
		    }
		} else {
		    if (noIdItems.containsKey(i.getName())) {
			noIdItems.get(i.getName()).add(i);
		    } else {
			List<Item> newList = new ArrayList<Item>();
			newList.add(i);
			noIdItems.put(i.getName(), newList);
		    }

		}
	    }
	    if (idItems.size() > 0) {
		tfield("items");
		for (List<Item> l : idItems.values()) {
		    tfield(l.get(0).getGameId());
		    for (int i = 0; i < l.size(); i++) {
			itfield(i + 1);
			addItem(l.get(i));
		    }
		    tend();
		}
		tend();
	    }
	    if (noIdItems.size() > 0) {
		tfield("items1");
		for (List<Item> l : noIdItems.values()) {
		    tfield(l.get(0).getName());
		    for (int i = 0; i < l.size(); i++) {
			itfield(i + 1);
			addItem(l.get(i));
		    }
		    tend();
		}
		tend();
	    }
	    tend(); // player
	}
	tend();
	tfield("multidkp_pools");
	for (MultidkpPool mp : data.getMultidkpPools().getMultidkpPool()) {
	    itfield(mp.getId());
	    field("id").append(mp.getId()).append(END);
	    sfield("name").append(mp.getName()).append(SEND);
	    sfield("desc").append(mp.getDesc()).append(SEND);
	    tfield("events");
	    for (Event e : mp.getEvents().getEvent()) {
		itfield(e.getId());
		field("id").append(e.getId()).append(END);
		sfield("name").append(e.getName()).append(SEND);
		field("value").append(e.getValue()).append(END);
		tend();
	    }
	    tend();
	    tfield("mdkp_itempools");
	    for (int iid : mp.getMdkpItempools().getItempoolId()) {
		osb.append(iid + ", ");
	    }
	    tend(); // mdkp_itempools
	    tend(); // multidkp_pool
	}
	tend();
	tfield("itempools");
	for (Itempool ip : data.getItempools().getItempool()) {
	    itfield(ip.getId());
	    field("id").append(ip.getId()).append(END);
	    sfield("name").append(ip.getName()).append(SEND);
	    sfield("desc").append(ip.getDesc()).append(SEND);
	    tend();
	}
	tend();
	osb.append('}');
	return osb.toString();
    }

    private void addItem(Item i) {
	field("game_id").append(i.getGameId()).append(END);
	sfield("name").append(i.getName()).append(SEND);
	field("value").append(i.getValue()).append(END);
	field("itempool_id").append(i.getItempoolId()).append(END);
	tend();
    }

    protected OutputStringBuilder sfield(String name) {
	return field(name).append('\"');
    }

    protected OutputStringBuilder field(String name) {
	return osb.append("[\"").append(name).append("\"] = ");
    }

    protected OutputStringBuilder ifield(int name) {
	return osb.append('[').append(name).append("] = ");
    }

    protected OutputStringBuilder itfield(int name) {
	return ifield(name).append('{');
    }

    protected OutputStringBuilder tfield(String name) {
	return field(name).append('{');
    }

    protected OutputStringBuilder tend() {
	return osb.append('}').append(',');
    }

    @Override
    public String getName() {
	return "WoW GetDKP (LUA) (not yet compatible)";
    }

    @Override
    public String getStandardMacExecutable() {
	File target = new File("/Applications/World of Warcraft/World of Warcraft Launcher.app");
	if (target.exists()) {
	    return target.getAbsolutePath();
	}
	return "";
    }

    @Override
    public String getStandardWindowsExecutable() {
	if (standardWindowsExe == null) {
	    String exe = "";
	    try {
		exe = RegistryReader.readString(RegistryReader.HKEY_LOCAL_MACHINE,
			"SOFTWARE\\Blizzard Entertainment\\World of Warcraft", "GamePath");
	    } catch (Exception e) {
		// ignore it
	    }
	    standardWindowsExe = exe;
	}
	return standardWindowsExe;
    }

    private String getSavePath(char separator, char pathSeparator) {
	String dir = getPlatformExecutable();
	if (dir.lastIndexOf(separator) == -1) {
	    return "";
	}
	dir = dir.substring(0, dir.lastIndexOf(separator));
	dir += XML_SAVE_PATH.replace('/', pathSeparator);
	return dir;
    }

    @Override
    public String getStandardWindowsSavePath() {
	return getSavePath('\\', '\\');
    }

    @Override
    public String getStandardMacSavePath() {
	return getSavePath('/', '/');
    }
}