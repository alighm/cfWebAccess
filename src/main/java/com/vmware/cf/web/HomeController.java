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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

	@Autowired
	public void setCustomCloudFoundryClient(CustomCloudFoundryClient client) throws MalformedURLException {
		this.client = client;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newHome() {
		return "new";
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

		logger.debug("Custom Cloud Applications are now added ");

		result.put("apps", apps);
		result.put("totalCount", apps.size());

		return result;
	}

	@RequestMapping(value = "/stopApp/{appName}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> stopApp(@PathVariable("appName") String appName) throws MalformedURLException {
		HashMap<String, Object> result = new HashMap<String, Object>();
		CloudFoundryClient cfClient = client.createClient();

		try {
			cfClient.stopApplication(appName);
		} catch (Exception e) {
			result.put("status", false);
			result.put("message", appName + " was not able to stop!");

			return result;
		}

		result.put("status", true);
		result.put("message", appName + " stopped successfully!");

		return result;
	}

	@RequestMapping(value = "/startApp/{appName}", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> startApp(@PathVariable("appName") String appName) throws MalformedURLException {
		HashMap<String, Object> result = new HashMap<String, Object>();
		CloudFoundryClient cfClient = client.createClient();

		try {
			cfClient.startApplication(appName);
		} catch (Exception e) {
			result.put("status", false);
			result.put("message", appName + " was not able to start!");

			return result;
		}

		result.put("status", true);
		result.put("message", appName + " started successfully!");
		return result;
	}

	@RequestMapping(value = "/updateInstance/{appName}/{instances}", method = RequestMethod.GET)
	@ResponseBody
	public boolean modifyInstance(@PathVariable("appName") String appName, @PathVariable("instances") int instances) throws MalformedURLException {
		CloudFoundryClient cfClient = client.createClient();

		try {
			cfClient.updateApplicationInstances(appName, instances);
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}