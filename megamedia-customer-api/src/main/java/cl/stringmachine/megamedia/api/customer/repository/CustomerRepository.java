package cl.stringmachine.megamedia.api.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import cl.stringmachine.megamedia.api.customer.repository.model.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

	@EntityGraph(attributePaths = { "billingData" })
	List<CustomerEntity> findAll();

	@EntityGraph(attributePaths = { "billingData" })
	Optional<CustomerEntity> findById(Long id);

}
