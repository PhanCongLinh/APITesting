API Testing Project Readme
Overview
  Welcome to the API Testing Project! 
  This project is designed to facilitate automated testing of APIs, ensuring their functionality, reliability, and performance. 
  It is implemented as a Java Maven project using Eclipse. Auto-initialized files from Eclipse are skipped to focus on the main project files.

Project Structure
  The project is organized as follows:
    /src/.../testcase: This directory contains test cases.
    /configuration: Configuration files for managing environment-specific variables and settings.
    /src/.../utils: Utility functions and helpers for common tasks.
    
How it works
  "propertiesFileUtils" java file in /src/.../utils used to get and save data such as baseURL, account, password,... for your API request. Ensure that it is written in key=value format.
  
  The test files are in /src/.../testcase. After obtaining data from using propertiesFileUtils, you send a api request using RestAssured, as shown in the code below:
    RestAssured.baseURI=url;
		RestAssured.basePath=path;
		RequestSpecification req= RestAssured.given().contentType(ContentType.JSON).body(body);
    response = req.post();
  
  convert the response body to json:
    resBody = response.getBody();
		bodyJson = resBody.jsonPath();
  
  and finally write your testcases. For example:
    public void TC01_StatusCodeTest(){
		assertEquals(200, response.getStatusCode(), "Status Check Failed!");
	}
 
 
Getting Started
After clone this project to your local workspace:
1. Open eclipse
2. Click on "File" (alt+f)
3.  Open project from file systems > Diretory > select project folder.

