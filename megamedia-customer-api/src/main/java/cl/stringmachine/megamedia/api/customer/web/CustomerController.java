package cl.stringmachine.megamedia.api.customer.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.stringmachine.megamedia.api.customer.service.CustomerService;
import cl.stringmachine.megamedia.api.customer.service.dto.CustomerRequestDto;
import cl.stringmachine.megamedia.api.customer.service.dto.CustomerResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;

	@GetMapping()
	@Operation(summary = "Get a list of customers")
	ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
		return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get a customer by Id")
	ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long id) {
		return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
	}

	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Saves a customer")
	public ResponseEntity<CustomerResponseDto> saveCustomer(@RequestBody CustomerRequestDto requestDto) {
		return new ResponseEntity<>(customerService.saveCustomer(requestDto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Updates a customer")
	ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable Long id,
			@RequestBody CustomerRequestDto requestDto) {
		return new ResponseEntity<>(customerService.updateCustomer(id, requestDto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Deletes a customer by Id")
	ResponseEntity<CustomerResponseDto> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomerById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
