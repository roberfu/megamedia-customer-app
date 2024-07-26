package cl.stringmachine.megamedia.api.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.stringmachine.megamedia.api.security.repository.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String username);

	Boolean existsByUsername(String username);

}
