package cl.stringmachine.megamedia.api.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.stringmachine.megamedia.api.security.repository.model.ERole;
import cl.stringmachine.megamedia.api.security.repository.model.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	Optional<RoleEntity> findByName(ERole name);

}
