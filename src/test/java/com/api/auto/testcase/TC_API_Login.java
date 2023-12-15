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

public class TC_API_Login {
	String account;
	String password;
	private Response response;
	private ResponseBody resBody;
	private JsonPath bodyJson;
	protected String token;
	@BeforeClass
	public void init() {
		String url = propertiesFileUtils.getProperties("baseurl");
		String path = propertiesFileUtils.getProperties("loginPath");
		account = propertiesFileUtils.getProperties("account");
		password = propertiesFileUtils.getProperties("password");
		Map <String, String> body = new HashMap();
		body.put("account",account);
		body.put("password", password);
		RestAssured.baseURI=url;
		RestAssured.basePath=path;
		RequestSpecification req= RestAssured.given().contentType(ContentType.JSON).body(body);
		response = req.post();
		resBody = response.getBody();
		bodyJson = resBody.jsonPath();
	} 
	@Test(priority=1)
	public void TC01_StatusCodeTest(){
		assertEquals(200, response.getStatusCode(), "Status Check Failed!");
	}
	@Test(priority=3)
	public void TC02_messageCheck(){
		String resMessage = bodyJson.get("message").toString();
		assertEquals(resMessage,"Đăng nhập thành công","Message Check Failed!");
	}
	@Test(priority=4)
	public void TC3_tokenCheck(){
		try {
			token = bodyJson.get("token").toString();
			propertiesFileUtils.setToken("token", token);
		}
		catch(Exception e) {
			System.out.print("Missing Token!");
		}
	}
	@Test(priority=3)
	public void TC4_inforCheck(){
		String resName, resPass, resType;
		resName = bodyJson.get("user.account");
		resPass = bodyJson.get("user.password");
		resType = bodyJson.get("user.type");
		assertEquals(resName,account,"Username Check Failed!");
		assertEquals(resPass,password,"Password Check Failed!");
		assertEquals(resType,"UNGVIEN","User Type Check Failed!");
	}
}
