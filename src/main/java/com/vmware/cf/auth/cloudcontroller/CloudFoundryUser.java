package com.vmware.cf.auth.cloudcontroller;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CloudFoundryUser implements UserDetails {

	private static final long serialVersionUID = 7620278496146866756L;

	private String username;

	private String authToken;

	private Collection<? extends GrantedAuthority> authorities;

	public CloudFoundryUser(String username, String authToken, Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.authToken = authToken;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return null;
	}

	public String getAuthToken() {
		return authToken;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * Returns {@code true} if the supplied object is a {@code CloudFoundryUser} instance
	 * with the same {@code username} value.
	 * <p>
	 * In other words, the objects are equal if they have the same username, representing the
	 * same principal.
	 */
	@Override
	public boolean equals(Object rhs) {
		if (rhs instanceof CloudFoundryUser) {
			return username.equals(((CloudFoundryUser) rhs).username);
		}
		return false;
	}

	/**
	 * Returns the hashcode of the {@code username}.
	 */
	@Override
	public int hashCode() {
		return username.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(": ");
		sb.append("Username: ").append(this.username).append("; ");
		sb.append("AuthToken: [PROTECTED]; ");

		if (!authorities.isEmpty()) {
			sb.append("Granted Authorities: ");

			boolean first = true;
			for (GrantedAuthority auth : authorities) {
				if (!first) {
					sb.append(",");
				}
				first = false;

				sb.append(auth);
			}
		} else {
			sb.append("Not granted any authorities");
		}

		return sb.toString();
	}

}
