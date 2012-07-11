package com.vmware.cf.auth.cloudcontroller;

import java.net.MalformedURLException;

import org.cloudfoundry.client.lib.CloudFoundryClient;

import com.vmware.cf.auth.cloudcontroller.CloudFoundryUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class CloudControllerAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private static final String ROLE_USER = "ROLE_USER";

	private final Logger log = LoggerFactory.getLogger(CloudControllerAuthenticationProvider.class);
	private final String cloudControllerUrl;

	public CloudControllerAuthenticationProvider(String cloudControllerUrl) {
		super();
		this.cloudControllerUrl = cloudControllerUrl;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// no op
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		String authToken = null;
		try {
			CloudFoundryClient client = buildCloudFoundryClient(username, (String) authentication.getCredentials());
			authToken = client.login();
		}
		catch (Exception e) {
			log.warn("Bad authentication for {}: " + e.getMessage(), username);
		}

		if (authToken == null) {
			throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
		log.info("Successful authentication for {}", username);
		return new CloudFoundryUser(username, authToken, AuthorityUtils.createAuthorityList(ROLE_USER));
	}

	protected CloudFoundryClient buildCloudFoundryClient(String username, String password) throws MalformedURLException {
		return new CloudFoundryClient(username, password, cloudControllerUrl);
	}
}