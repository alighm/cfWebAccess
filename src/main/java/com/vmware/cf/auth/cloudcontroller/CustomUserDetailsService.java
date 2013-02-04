package com.vmware.cf.auth.cloudcontroller;

import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Logger;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;

@SuppressWarnings("rawtypes")
public class CustomUserDetailsService implements AuthenticationUserDetailsService {

	private static final List DEFAULT_AUTHORITIES = AuthorityUtils.createAuthorityList("ROLE_USER");
	private final String cloudControllerUrl;
	protected static Logger logger = Logger.getLogger("service");

	public CustomUserDetailsService(String cloudControllerUrl) {
		super();
		this.cloudControllerUrl = cloudControllerUrl;
	}

	@Override
	public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
		String id = ((OpenIDAuthenticationToken) token).getIdentityUrl();
		String email = null;
		String firstName = null;
		String lastName = null;
		String fullName = null;

		List<OpenIDAttribute> attributes = ((OpenIDAuthenticationToken) token).getAttributes();

		for (OpenIDAttribute attribute : attributes) {
			if (attribute.getName().equals("email")) {
				email = attribute.getValues().get(0);
			}
			if (attribute.getName().equals("firstName")) {
				firstName = attribute.getValues().get(0);
			}
			if (attribute.getName().equals("lastName")) {
				lastName = attribute.getValues().get(0);
			}
			if (attribute.getName().equals("fullname")) {
				fullName = attribute.getValues().get(0);
			}
		}
		if (fullName == null) {
			StringBuilder fullNameBldr = new StringBuilder();
			if (firstName != null) {
				fullNameBldr.append(firstName);
			}
			if (lastName != null) {
				fullNameBldr.append(" ").append(lastName);
			}
			fullName = fullNameBldr.toString();
		}

		CustomUserDetails user = new CustomUserDetails(id,fullName,email, DEFAULT_AUTHORITIES);
		logger.info("Set username " + fullName + " email " + email);
		return user;
	}

	protected CloudFoundryClient buildCloudFoundryClient(String username, String password) throws MalformedURLException {
		return new CloudFoundryClient(username, password, cloudControllerUrl);
	}
}