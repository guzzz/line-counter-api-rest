package com.linecounter.apirest.controllers;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linecounter.apirest.services.LineCounterService;
import com.linecounter.apirest.services.GitRepositoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping(value="/api")
@Api(value="API REST Line Counter")
@CrossOrigin(origins="*")
public class LineCounterController {
	
	@Autowired
	private LineCounterService lineCounterService;
	
	@Autowired
	private GitRepositoryService gitRepositoryService;
	

	@GetMapping("/clean")
	@ApiOperation(value="Clean Git Repository temporary directory",
			notes="Additional call that delete temporary directory. No need to use it before \"/api/download\".")
	public ResponseEntity<Map<String,String>> clean(){
		
		boolean gitRepository = this.lineCounterService.gitRepositoryAlreadyDownloaded();
		Map<String, String> message = new HashMap<String,String>();
		
		if (gitRepository) {
			String directoryPath = "repository";
			this.gitRepositoryService.deleteFolder(Paths.get(directoryPath).toFile());
			message.put("message", "Git repository directory deleted!");
			return ResponseEntity.ok(message);
		}else {
			message.put("message", "Git repository directory is already empty!");
			return ResponseEntity.ok(message);
		}
	}
	
	
	@PostMapping("/download")
	@ApiOperation(value="Download Git Repository to temporary directory",
			notes="In this POST method, the user have to send a valid repository URL. _This call already clean temporary directory before download._")
	public ResponseEntity<Map<String,String>> download(
			@ApiParam(value = "Insert a valid HTTPS repository clone URL." , 
			example = "{\"url\":\"https://github.com/guzzz/studioJ3D_V2.git\"}") 
			@RequestBody Map<String, String> payload) {
		
		try {
			this.gitRepositoryService.download(payload.get("url"));
			Map<String, String> message = new HashMap<String,String>();
			message.put("message", "Download completed!");
			return ResponseEntity.ok(message);
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	@GetMapping("/analyse")
	@ApiOperation(value="Analyse Git Repository",
			notes="In this GET method, the API do some analysis in the repository already downloaded.")
	public ResponseEntity<Map<String, ArrayList<Map<String, String>>>> getAnalysis() throws IOException {
		
		boolean gitRepository = this.lineCounterService.gitRepositoryAlreadyDownloaded();
		
		if (gitRepository) {
			Map<String, ArrayList<Map<String, String>>> response = this.lineCounterService.getGitRepositoryData();
			return ResponseEntity.ok(response);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

}
