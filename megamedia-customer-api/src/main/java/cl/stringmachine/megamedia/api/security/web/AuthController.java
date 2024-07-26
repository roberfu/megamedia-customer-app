package cl.stringmachine.megamedia.api.security.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.stringmachine.megamedia.api.security.service.SecurityService;
import cl.stringmachine.megamedia.api.security.service.dto.RegisterUserRequestDto;
import cl.stringmachine.megamedia.api.security.service.dto.TokenRequestDto;
import cl.stringmachine.megamedia.api.security.service.dto.TokenResponseDto;
import cl.stringmachine.megamedia.api.security.service.dto.UserResponseDto;
import io.jsonwebtoken.io.IOException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final SecurityService securityService;

	@PostMapping("/token")
	@Operation(summary = "Get a token")
	public ResponseEntity<TokenResponseDto> authenticateUser(@Valid @RequestBody TokenRequestDto tokenRequestDto) {
		return new ResponseEntity<>(securityService.getToken(tokenRequestDto), HttpStatus.OK);
	}

	@PostMapping("/register")
	@Operation(summary = "Register user")
	public ResponseEntity<UserResponseDto> registerUser(
			@Valid @RequestBody RegisterUserRequestDto registerUserRequestDto) throws IOException {
		return new ResponseEntity<>(securityService.registerUser(registerUserRequestDto), HttpStatus.CREATED);
	}

}
