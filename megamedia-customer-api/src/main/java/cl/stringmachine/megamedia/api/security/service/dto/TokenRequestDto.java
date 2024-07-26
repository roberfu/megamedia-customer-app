package cl.stringmachine.megamedia.api.security.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenRequestDto {

	@NotBlank(message = "Username is Required")
	private String username;

	@NotBlank(message = "Password is Required")
	private String password;

}
