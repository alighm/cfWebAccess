package com.vmware.cf.web;

import java.net.URI;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/market")
public class AppDirectController {

	private OAuthRestTemplate appDirectRestTemplate;

	@Autowired
	public void setAppDirectRestTemplate(OAuthRestTemplate appDirectRestTemplate) {
		this.appDirectRestTemplate = appDirectRestTemplate;
	}

	@RequestMapping("/create")
	public void create(@RequestParam("url") URI uri) {
		byte[] bytes = appDirectRestTemplate.getForObject(uri, byte[].class);
		System.out.println("HERE IS THE URL: " + uri.toString());
	}

	@RequestMapping("/change")
	public void change() {
		
	}

	@RequestMapping("/cancel")
	public void cancel() {
		
	}

	@RequestMapping("/notice")
	public void notice() {
		
	}
}
