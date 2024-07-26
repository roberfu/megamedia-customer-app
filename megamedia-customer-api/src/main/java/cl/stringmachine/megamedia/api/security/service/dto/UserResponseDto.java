package cl.stringmachine.megamedia.api.security.service.dto;

import java.util.List;

import cl.stringmachine.megamedia.api.security.repository.model.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

	private String username;

	private List<ERole> roles;

}
