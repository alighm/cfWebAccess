package com.vmware.cf.web;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cloudfoundry.client.lib.CloudApplication;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.CloudService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vmware.cf.client.lib.CustomCloudFoundryClient;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	protected CustomCloudFoundryClient client;

	public void setCustomCloudFoundryClient(CustomCloudFoundryClient client) throws MalformedURLException {
		this.client = client;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/getApps",  method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> getApps() throws MalformedURLException {
		CloudFoundryClient cfClient = client.createClient();

		List<CloudApplication> apps = cfClient.getApplications();

		for (CloudApplication app : apps) {
			List<String> values = new ArrayList<String>();
			List<String> services = app.getServices();
			if (services.size() == 0) {
				values.add("NoService");
			} else {
				for (String service : services) {
					CloudService s = cfClient.getService(service);
					values.add(service + ":" + s.getVendor());
				}
			}
			app.setServices(values);
		}

		HashMap<String, Object> result = new HashMap<String, Object>();

		logger.info("Custom Cloud Applications are now added ");

		result.put("apps", apps);
		result.put("totalCount", apps.size());

		return result;
	}
}