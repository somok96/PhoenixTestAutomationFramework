package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.Search;
import com.api.services.JobService;

@Listeners(com.listeners.APITestListener.class)
public class SearchAPITest {

 	private JobService jobService;
	private String jobNumber = "JOB_175761";
	private Search searchPayload;
	
	@BeforeMethod(description = "Instantiating the JobService and creating the Search Payload")
	public void setup() {
		
		searchPayload = new Search(jobNumber);
		jobService = new JobService();
			
	}
	
	@Test(description = "Verify if the searchAPI is working properly", groups = {"e2e", "smoke", "api"})
	public void searchJobAPITest() {
		jobService.search(Role.FD, searchPayload).then()
		.body("message", Matchers.equalTo("Success"));
	}
	
}
