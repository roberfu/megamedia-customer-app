package cl.stringmachine.megamedia.api.security.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cl.stringmachine.megamedia.api.security.repository.model.UserEntity;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(UserEntity userEntity) {
		List<GrantedAuthority> authorities = userEntity.getRoles().stream()
				.map(role -> (GrantedAuthority) new SimpleGrantedAuthority(role.getName().name())).toList();

		return new UserDetailsImpl(userEntity.getUsername(), userEntity.getPassword(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

}
