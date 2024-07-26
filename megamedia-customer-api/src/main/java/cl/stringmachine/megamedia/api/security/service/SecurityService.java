package cl.stringmachine.megamedia.api.security.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.stringmachine.megamedia.api.security.repository.RoleRepository;
import cl.stringmachine.megamedia.api.security.repository.UserRepository;
import cl.stringmachine.megamedia.api.security.repository.model.ERole;
import cl.stringmachine.megamedia.api.security.repository.model.RoleEntity;
import cl.stringmachine.megamedia.api.security.repository.model.UserEntity;
import cl.stringmachine.megamedia.api.security.service.dto.RegisterUserRequestDto;
import cl.stringmachine.megamedia.api.security.service.dto.TokenRequestDto;
import cl.stringmachine.megamedia.api.security.service.dto.TokenResponseDto;
import cl.stringmachine.megamedia.api.security.service.dto.UserResponseDto;
import cl.stringmachine.megamedia.api.security.service.jwt.JwtUtils;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityService {

	private final AuthenticationManager authenticationManager;

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final PasswordEncoder encoder;

	private final JwtUtils jwtUtils;

	public TokenResponseDto getToken(TokenRequestDto request) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		return TokenResponseDto.builder().accessToken(jwt).build();

	}

	@Transactional
	public UserResponseDto registerUser(RegisterUserRequestDto request) throws IOException {

		Optional<UserEntity> optional = userRepository.findByUsername(request.getUsername());

		if (optional.isPresent())
			throw new IOException("User Exists");

		RoleEntity roleEntity = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found"));

		Set<RoleEntity> roles = new HashSet<>();
		roles.add(roleEntity);

		UserEntity userEntity = new UserEntity();
		userEntity.setPassword(encoder.encode(request.getPassword()));
		userEntity.setUsername(request.getUsername());
		userEntity.setRoles(roles);

		try {
			userEntity.setRoles(roles);
			userRepository.save(userEntity);
		} catch (Exception e) {
			throw new IOException("Database Error");
		}

		return UserResponseDto.builder().username(userEntity.getUsername()).build();
	}

}
