package cl.stringmachine.megamedia.api.customer.service;

import java.util.List;

import cl.stringmachine.megamedia.api.customer.service.dto.CustomerRequestDto;
import cl.stringmachine.megamedia.api.customer.service.dto.CustomerResponseDto;

public interface CustomerService {

	List<CustomerResponseDto> getAllCustomers();

	CustomerResponseDto getCustomerById(Long id);

	CustomerResponseDto saveCustomer(CustomerRequestDto requestDto);

	CustomerResponseDto updateCustomer(Long id, CustomerRequestDto requestDto);

	void deleteCustomerById(Long id);

}
