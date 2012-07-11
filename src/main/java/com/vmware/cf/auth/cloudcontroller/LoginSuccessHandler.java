package com.vmware.cf.auth.cloudcontroller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler
{
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException {

		Writer out = response.getWriter();
		out.write("\"" + ((CloudFoundryUser) auth.getPrincipal()).getUsername() + "\"");
		out.close();
	}
}