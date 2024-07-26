package cl.stringmachine.megamedia.api.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.stringmachine.megamedia.api.security.repository.UserRepository;
import cl.stringmachine.megamedia.api.security.repository.model.UserEntity;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not Found"));

		return UserDetailsImpl.build(userEntity);
	}

}
