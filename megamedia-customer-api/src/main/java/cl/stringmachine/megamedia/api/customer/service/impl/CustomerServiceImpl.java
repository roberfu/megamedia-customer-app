package cl.stringmachine.megamedia.api.customer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.stringmachine.megamedia.api.customer.client.countryinfoapi.CountryInfoApiClient;
import cl.stringmachine.megamedia.api.customer.repository.CustomerRepository;
import cl.stringmachine.megamedia.api.customer.repository.model.BillingDataEntity;
import cl.stringmachine.megamedia.api.customer.repository.model.CustomerEntity;
import cl.stringmachine.megamedia.api.customer.service.CustomerService;
import cl.stringmachine.megamedia.api.customer.service.dto.CustomerRequestDto;
import cl.stringmachine.megamedia.api.customer.service.dto.CustomerResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	private final CountryInfoApiClient countryInfoApiClient;

	private static final String CUSTOMER_NOT_FOUND = "Customer Not Found";

	@Override
	public List<CustomerResponseDto> getAllCustomers() {
		return customerRepository.findAll().stream().map(this::convertFromCustomerEntityToResponseDto).toList();
	}

	@Override
	public CustomerResponseDto getCustomerById(Long id) {
		return convertFromCustomerEntityToResponseDto(
				customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CUSTOMER_NOT_FOUND)));
	}

	@Override
	public CustomerResponseDto saveCustomer(CustomerRequestDto requestDto) {
		CustomerEntity customerEntity = new CustomerEntity();
		return convertFromCustomerEntityToResponseDto(
				customerRepository.save(convertFromRequestDtoToEntity(requestDto, customerEntity)));

	}

	@Override
	@Transactional
	public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto requestDto) {
		CustomerEntity customerEntity = customerRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(CUSTOMER_NOT_FOUND));
		return convertFromCustomerEntityToResponseDto(
				customerRepository.save(convertFromRequestDtoToEntity(requestDto, customerEntity)));
	}

	@Override
	public void deleteCustomerById(Long id) {
		CustomerEntity customerEntity = customerRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(CUSTOMER_NOT_FOUND));
		customerRepository.delete(customerEntity);

	}

	private CustomerResponseDto convertFromCustomerEntityToResponseDto(CustomerEntity customerEntity) {
		return CustomerResponseDto.builder().id(customerEntity.getId()).firstName(customerEntity.getFirstName())
				.lastName(customerEntity.getLastName()).email(customerEntity.getEmail())
				.billingDataCountry(customerEntity.getBillingData().getCountry())
				.billingDataAccount(customerEntity.getBillingData().getAccount())
				.billingDataCurrency(customerEntity.getBillingData().getCurrency()).build();
	}

	private CustomerEntity convertFromRequestDtoToEntity(CustomerRequestDto requestDto, CustomerEntity customerEntity) {
		customerEntity.setFirstName(requestDto.getFirstName());
		customerEntity.setLastName(requestDto.getLastName());
		customerEntity.setEmail(requestDto.getEmail());

		if (requestDto.getBillingDataCountry() != null) {
			BillingDataEntity billingDataEntity = customerEntity.getBillingData();
			if (billingDataEntity == null) {
				billingDataEntity = new BillingDataEntity();
				billingDataEntity.setCustomer(customerEntity);
				billingDataEntity.setAccount(requestDto.getBillingDataAccount());
				customerEntity.setBillingData(billingDataEntity);
			}

			String currency = countryInfoApiClient.getCurrencyData(requestDto.getBillingDataCountry());
			billingDataEntity.setCurrency(currency);
			billingDataEntity.setCountry(requestDto.getBillingDataCountry());
		} else {
			customerEntity.setBillingData(null);
		}
		return customerEntity;
	}

}
