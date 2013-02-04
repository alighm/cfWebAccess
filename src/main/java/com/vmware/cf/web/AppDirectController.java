package com.vmware.cf.web;

import java.net.URI;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.*;

@Controller
@RequestMapping("/market")
public class AppDirectController {

	private OAuthRestTemplate appDirectRestTemplate;

	@Autowired
	public void setAppDirectRestTemplate(OAuthRestTemplate appDirectRestTemplate) {
		this.appDirectRestTemplate = appDirectRestTemplate;
	}

	@RequestMapping("/create")
	public String create(@RequestParam("url") URI uri, Map<String, Object> model) {
		String orderEventJSON = appDirectRestTemplate.getForObject(uri, String.class);
		JsonParser parser = new JsonParser();

		JsonObject eventJson = parser.parse(orderEventJSON).getAsJsonObject();
		JsonObject company = eventJson.get("payload").
				getAsJsonObject().get("company").getAsJsonObject();

		model.put("success", true);
		model.put("message", "Account creation successful for " + company.get("name"));
		model.put("accountIdentifier", "testing123456");

		System.out.println(eventJson.get("returnUrl"));
		return "redirect:" + eventJson.get("returnUrl").toString().replaceAll("^\"|\"$", "");
	}

	@RequestMapping("/change")
	public void change() {}

	@RequestMapping(value = "/cancel", produces = "application/xml")
	@ResponseBody
	public String cancel(@RequestParam("url") URI uri, final HttpServletResponse response) {
		String cancelEventJSON = appDirectRestTemplate.getForObject(uri, String.class);

		response.setContentType("application/xml");

		return "<result><success>true</success></result>";
	}

	@RequestMapping("/notice")
	public void notice() {}
}
