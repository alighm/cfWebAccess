package com.vmware.cf.client.lib;

import java.net.MalformedURLException;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;

import com.vmware.cf.auth.cloudcontroller.CloudFoundryUser;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CustomCloudFoundryClientTest {

	private CustomCloudFoundryClient client;

	private String cloudControllerUrl;

	@Mock
	private Authentication auth;

	@Mock
	private CloudFoundryUser activeUser;

	@Before
	public void setup() throws MalformedURLException {
		initMocks(this);

		client = new CustomCloudFoundryClient();
		cloudControllerUrl = "http://api.cloudfoundry.com";
	}

	@Test
	public void createClient() throws MalformedURLException {
		when (auth.getPrincipal()).thenReturn(activeUser);
		when (activeUser.getAuthToken()).thenReturn("123456789");

		client.setCloudControllerUrl(cloudControllerUrl);
		client.setPrincipal(auth);

		CloudFoundryClient c1  = new CloudFoundryClient("123456789", "http://api.cloudfoundry.com");

		assertEquals(c1.getCloudControllerUrl(), client.createClient().getCloudControllerUrl());
		assertEquals(c1.getClass(), client.createClient().getClass());
	}
}