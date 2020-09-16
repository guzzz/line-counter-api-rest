package com.linecounter.apirest.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.linecounter.apirest.entities.Info;


@Service
public class LineCounterService {
	
	public LineCounterService() {}
	
	public Map<String, ArrayList<Map<String, String>>> getGitRepositoryData() throws IOException {
		Map<String, ArrayList<Map<String, String>>> response = new HashMap<String, ArrayList<Map<String, String>>>();
		ArrayList<Map<String, String>> allInfo = getRepositoryInformation();
		response.put("data", allInfo);
		return response;
	}
	
	private static ArrayList<Map<String, String>> getRepositoryInformation() throws IOException {
		ArrayList<Map<String, String>> allInfosJson = new ArrayList<Map<String, String>>();
		ArrayList<Info> allInfos = AnalysisService.getAllInfos();
		for(Info item: allInfos) {
			Map<String, String> infoJson = new HashMap<String, String>();
			infoJson.put("extension", item.getExtension());
			infoJson.put("lines", String.valueOf(item.getLines()));
			infoJson.put("bytes", String.valueOf(item.getBytes()));
			allInfosJson.add(infoJson);
	    }
		return allInfosJson;
	}
	
	public boolean gitRepositoryAlreadyDownloaded() {
		String folder = "repository";
		Path gitRepositoryPath = (Paths.get(folder));
		
		if (Files.exists(gitRepositoryPath)) {
			return true;
		} else {
			return false;
		}
	}

}
