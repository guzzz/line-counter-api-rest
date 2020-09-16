package com.linecounter.apirest.services;

import java.util.ArrayList;
import com.linecounter.apirest.entities.Info;


public class InfoService {
	
	public InfoService() {}
	
	public static ArrayList<Info> organize(ArrayList<Info> allInfo, Info archiveInfo) {
		if(allInfo.isEmpty() || allInfo == null) {
			allInfo.add(archiveInfo);
		} else {
			boolean create = true;			
			for(int i=0; i<allInfo.size(); i++) {
				if(allInfo.get(i).getExtension().equals(archiveInfo.getExtension())) {
					create = false;
					allInfo.get(i).addBytes(archiveInfo.getBytes());
					allInfo.get(i).addLines(archiveInfo.getLines());
				}
			}
			if(create) {
				allInfo.add(archiveInfo);
			}
		}
		return allInfo;
	}

}
