package com.vmware.cf.auth.cloudcontroller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.net.MalformedURLException;

import com.vmware.cf.auth.cloudcontroller.CloudFoundryUser;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CloudControllerAuthenticationProviderTest {

	private TestableCloudControllerAuthenticationProvider authProvider;

	@Mock
	private CloudFoundryClient client;

	@Before
	public void setup() throws Exception {
		initMocks(this);
		authProvider = new TestableCloudControllerAuthenticationProvider(client);
		authProvider.afterPropertiesSet();
	}

	@Test
	public void authenticate() {
		Authentication preAuth = new UsernamePasswordAuthenticationToken("user", "pass");
		when(client.login()).thenReturn("token");

		Authentication postAuth = authProvider.authenticate(preAuth);

		assertTrue(postAuth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
		assertEquals("user", postAuth.getName());
		assertEquals("user", authProvider.getLastUsername());
		assertEquals("pass", authProvider.getLastPassword());
		assertEquals("token", ((CloudFoundryUser) postAuth.getPrincipal()).getAuthToken());
	}

	@Test(expected = BadCredentialsException.class)
	public void authenticate_badCredentials() {
		Authentication preAuth = new UsernamePasswordAuthenticationToken("user", "pass");
		when(client.login()).thenReturn(null);

		authProvider.authenticate(preAuth);
	}

	@Test(expected = BadCredentialsException.class)
	public void authenticate_remoteIOE() {
		Authentication preAuth = new UsernamePasswordAuthenticationToken("user", "pass");
		when(client.login()).thenThrow(new RuntimeException("Remote exception"));

		authProvider.authenticate(preAuth);
	}

	class TestableCloudControllerAuthenticationProvider extends CloudControllerAuthenticationProvider {

		private CloudFoundryClient client;
		private String lastUsername;
		private String lastPassword;

		public TestableCloudControllerAuthenticationProvider(CloudFoundryClient client) {
			super("https://api.cloudfoundry.com");
			this.client = client;
		}

		@Override
		protected CloudFoundryClient buildCloudFoundryClient(String username, String password) throws MalformedURLException {
			lastUsername = username;
			lastPassword = password;
			return client;
		}

		public String getLastUsername() {
			return lastUsername;
		}

		public String getLastPassword() {
			return lastPassword;
		}
	}
}
