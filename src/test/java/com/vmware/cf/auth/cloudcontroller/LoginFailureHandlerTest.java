package com.vmware.cf.auth.cloudcontroller;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.AuthenticationException;

public class LoginFailureHandlerTest {

	private LoginFailureHandler handler;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private AuthenticationException exception;

	@Before
	public void setup() {
		initMocks(this);

		handler = new LoginFailureHandler();
	}

	@Test
	public void authFailure() throws IOException, ServletException {
		PrintWriter writer = new PrintWriter("src/test/resources/testFile.txt");

		when(response.getWriter()).thenReturn(writer);

		handler.onAuthenticationFailure(request, response, exception);
		assertTrue(FileUtils.readFileToString(new File("src/test/resources/testFile.txt"), "UTF-8").contains("false"));
	}
}