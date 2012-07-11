package com.vmware.cf.web;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cloudfoundry.client.lib.CloudApplication;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.CloudService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.vmware.cf.client.lib.CustomCloudFoundryClient;

public class HomeControllerTest {

	private HomeController homeController;

	@Mock
	private CustomCloudFoundryClient client;

	@Mock
	private CloudFoundryClient cfClient;

	private List<CloudApplication> apps;

	@Mock
	private CloudService service;

	@Before
	public void setup() {
		initMocks(this);

		homeController = new HomeController();

		//Creating Cloud Apps for the cfClient Mock
		apps = new ArrayList<CloudApplication>();

		Map<String, Object> attributes;

		Map<String, String> staging = new HashMap<String, String>();
		staging.put("model", "spring");
		staging.put("stack", "java");

		Map<String, Integer> resources = new HashMap<String, Integer>();
		resources.put("memory", 512);

		List<String> serviceNames = new ArrayList<String>();
		serviceNames.add("testing");

		for (int i = 1; i <= 2; i++) {
			attributes = new HashMap<String, Object>();
			attributes.put("name" + i, "testApp");
			attributes.put("staging", staging);
			attributes.put("resources", resources);
			attributes.put("instances", 1);
			attributes.put("state", "STOPPED");
			attributes.put("services", serviceNames);

			apps.add(new CloudApplication(attributes));
		}
	}

	@Test
	public void homePageCall() {
		assertEquals("home", homeController.home());
	}

	@Test
	public void getApps() throws MalformedURLException {
		when(client.createClient()).thenReturn(cfClient);
		when(cfClient.getApplications()).thenReturn(apps);
		when(service.getVendor()).thenReturn("mysql");
		when(cfClient.getService("testing")).thenReturn(service);

		homeController.setCustomCloudFoundryClient(client);

		assertEquals(2, homeController.getApps().size());
	}
}