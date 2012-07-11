package com.vmware.cf.auth.cloudcontroller;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;

public class LoginSuccessHandlerTest {

	private LoginSuccessHandler handler;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private Authentication auth;

	@Mock
	private CloudFoundryUser activeUser;

	@Before
	public void setup() {
		initMocks(this);

		handler = new LoginSuccessHandler();
	}

	@Test
	public void authFailure() throws IOException {
		PrintWriter writer = new PrintWriter("src/test/resources/testFile.txt");

		when(response.getWriter()).thenReturn(writer);
		when(auth.getPrincipal()).thenReturn(activeUser);
		when(activeUser.getUsername()).thenReturn("test@test.com");

		handler.onAuthenticationSuccess(request, response, auth);
		assertTrue(FileUtils.readFileToString(new File("src/test/resources/testFile.txt"), "UTF-8").contains("test@test.com"));
	}
}