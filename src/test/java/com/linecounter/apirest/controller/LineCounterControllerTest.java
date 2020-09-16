package com.linecounter.apirest.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpStatus;

import com.linecounter.apirest.controllers.LineCounterController;
import com.linecounter.apirest.services.GitRepositoryService;
import com.linecounter.apirest.services.LineCounterService;

import io.restassured.http.ContentType;


@WebMvcTest
public class LineCounterControllerTest {

	@Autowired
	private LineCounterController lineCounterController;
	
	@MockBean
	private LineCounterService lineCounterService;
	
	@MockBean
	private GitRepositoryService gitRepositoryService;
	
	public static Map<String, ArrayList<Map<String, String>>> response = new HashMap<String, ArrayList<Map<String, String>>>();
	static {
		ArrayList<Map<String, String>> testList = new ArrayList<Map<String, String>>();
		Map<String, String> item = new HashMap<String, String>();
		item.put("extension", "js");
		item.put("lines", "1");
		item.put("bytes", "20");
		testList.add(item);
		response.put("data", testList);
	}
	
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.lineCounterController);
	}
	
	@Test
	public void shouldReturnSucess_WhenAnalyseDataThatExists() throws IOException {
		when(this.lineCounterService.gitRepositoryAlreadyDownloaded())
			.thenReturn(true);
		when(this.lineCounterService.getGitRepositoryData())
			.thenReturn(response);
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/api/analyse")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void shouldReturnNotFound_WhenAnalyseDataThatNotExists() {
		when(this.lineCounterService.gitRepositoryAlreadyDownloaded())
			.thenReturn(false);
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/api/analyse")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
}