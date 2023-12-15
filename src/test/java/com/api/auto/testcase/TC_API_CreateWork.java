package com.api.auto.testcase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.api.auto.utils.propertiesFileUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC_API_CreateWork {
	private String nameWork = "Tester";
	private String experience = "2years";
	private String education = "Engineer";
	private Response response;
	private ResponseBody resBody;
	private JsonPath bodyJson;

	@BeforeClass
	public void init() {
		String url = propertiesFileUtils.getProperties("baseurl");
		String path = propertiesFileUtils.getProperties("createWorkPath");
		String token = propertiesFileUtils.getToken();
		Map<String, String> body = new HashMap();
		body.put("nameWork", nameWork);
		body.put("experience", experience);
		body.put("education", education);
		RestAssured.baseURI = url;
		RestAssured.basePath = path;
		RequestSpecification req = RestAssured.given().contentType(ContentType.JSON).header("Token", token).body(body);
		response = req.post();
		resBody = response.getBody();
		bodyJson = resBody.jsonPath();
	}

	@Test(priority=1)
	public void TC1_StatusCodeTest() {
		assertEquals(response.getStatusCode(), 201, "Status Check Failed!");
	}

	@Test(priority=2)
	public void TC2_jobIdCheck(){
		assertTrue(response.asString().contains("id"));
	}
	@Test(priority=3)
	public void TC3_inforCheck(){
		String resNameWork = bodyJson.get("nameWork");
		String resExperience = bodyJson.get("experience");
		String resEducation = bodyJson.get("education");
		assertEquals(resNameWork,nameWork,"NameWork Check Failed!");
		assertEquals(resExperience,experience,"Experience Check Failed!");
		assertEquals(resEducation,education,"Education Check Failed!");
	}
}
