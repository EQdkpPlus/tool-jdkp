package com.eqdkplus.jdkp.output;

import java.util.HashMap;

import com.eqdkplus.jdkp.parse.bind.Item;
import com.eqdkplus.jdkp.parse.bind.MultidkpPoints;
import com.eqdkplus.jdkp.parse.bind.MultidkpPool;
import com.eqdkplus.jdkp.parse.bind.Player;
import com.eqdkplus.jdkp.parse.bind.Response;

@SuppressWarnings("nls")
public class WoWGetDKPClassicInterface extends WoWGetDKPInterface {

    public WoWGetDKPClassicInterface() {
	super();
    }
    
    @Override
    public String getName() {
	return "WoW GetDKP LUA (Classic)";
    }

    @Override
    public String format(Response data) {

	HashMap<Integer, MultidkpPool> multiDkpPools = new HashMap<Integer, MultidkpPool>();
	for (MultidkpPool mp : data.getMultidkpPools().getMultidkpPool()) {
	    multiDkpPools.put(mp.getId(), mp);
	}

	osb.append("multiTable = {");
	if (data.getMultidkpPools().getMultidkpPool().size() > 1) {
	    for (int i = 0; i < data.getMultidkpPools().getMultidkpPool().size(); i++) {
		MultidkpPool mp = data.getMultidkpPools().getMultidkpPool().get(i);
		itfield(i + 1);
		tfield(mp.getName());
		field("id").append(mp.getId()).append(END);
		sfield("name").append(mp.getName()).append(SEND);
		sfield("desc").append(mp.getDesc()).append(SEND);
		sfield("events");
		for (int j = 0; j < mp.getEvents().getEvent().size(); j++) {
		    osb.append(mp.getEvents().getEvent().get(j).getName());
		    if (j != mp.getEvents().getEvent().size() - 1) {
			osb.append(" , ");
		    } else {
			osb.append('\"');
		    }
		}
		tend();
		tend();
	    }
	    osb.append('}');
	} else {
	    osb.append("[1]= { [\"dkp\"] = {\n[\"name\"] = \"dkp\",\n[\"disc\"] = \"Raid DKP\",\n[\"events\"] = \" \"\n},\n},\n}");
	}
	osb.append("DKPInfo = {");
	sfield("date").append(data.getInfo().getDate()).append(SEND);
	sfield("timestamp").append(data.getInfo().getTimestamp()).append(SEND);
	sfield("process_dkp_ver").append(3.0).append(SEND);
	field("total_players").append(data.getInfo().getTotalPlayers()).append(END);
	field("total_items").append(data.getInfo().getTotalItems()).append(END);
	// TODO
	field("total_points").append(0).append(END);
	//
	osb.append('}');
	osb.append("gdkp = {");
	tfield("players");
	for (Player p : data.getPlayers().getPlayer()) {
	    tfield(p.getName());
	    int currentDKP = 0;
	    for (MultidkpPoints mp : p.getPoints().getMultidkpPoints()) {
		currentDKP += data.getInfo().getWithTwink() == 1 ? mp.getPointsCurrentWithTwink() : mp
			.getPointsCurrent();
	    }
	    field("DKP").append(currentDKP).append(END);
	    String mpName = "dkp";
	    for (MultidkpPoints mp : p.getPoints().getMultidkpPoints()) {

		if (data.getMultidkpPools().getMultidkpPool().size() > 1) {
		    mpName = multiDkpPools.get(mp.getMultidkpId()).getName();
		}
		field(mpName + "_earned").append(
			data.getInfo().getWithTwink() == 1 ? mp.getPointsEarnedWithTwink() : mp.getPointsEarned())
			.append(END);
		field(mpName + "_spend").append(
			data.getInfo().getWithTwink() == 1 ? mp.getPointsSpentWithTwink() : mp.getPointsSpent())
			.append(END);
		// TODO
		field(mpName + "_adjust").append(0).append(END);
		//
		field(mpName + "_current").append(
			data.getInfo().getWithTwink() == 1 ? mp.getPointsCurrentWithTwink() : mp.getPointsCurrent())
			.append(END);
	    }
	    sfield("class").append(p.getClassName()).append(SEND);
	    // TODO
	    field("rcount").append(0).append(END);
	    //
	    tend(); // close player
	}
	osb.append('}'); // close players
	osb.append('}'); // close gdkp
	osb.append("DKP_ITEMS = {");
	for (Player p : data.getPlayers().getPlayer()) {
	    tfield(p.getName());
	    tfield("Items");
	    for (int i = 0; i < p.getItems().getItem().size(); i++) {
		Item item = p.getItems().getItem().get(i);
		itfield(i + 1);
		sfield("name").append(item.getName()).append(SEND);
		field("dkp").append(item.getValue());
		tend();
	    }
	    tend();
	    tend();
	}
	osb.append('}');
	return osb.toString();
    }
}