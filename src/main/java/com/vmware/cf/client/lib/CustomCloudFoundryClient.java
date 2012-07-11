package com.vmware.cf.client.lib;

import java.net.MalformedURLException;
import java.security.Principal;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.springframework.security.core.Authentication;

import com.vmware.cf.auth.cloudcontroller.CloudFoundryUser;

public class CustomCloudFoundryClient {

	private Principal principal;
	private String cloudControllerUrl;

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public void setCloudControllerUrl(String cloudControllerUrl) {
		this.cloudControllerUrl = cloudControllerUrl;
	}

	public CloudFoundryClient createClient() throws MalformedURLException {
		CloudFoundryUser activeUser = (CloudFoundryUser) ((Authentication) this.principal).getPrincipal();

		return new CloudFoundryClient(activeUser.getAuthToken(), this.cloudControllerUrl);
	}
}