package cl.stringmachine.megamedia.api.customer.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDto {

	private Long id;

	@JsonProperty(value = "first_name")
	private String firstName;

	@JsonProperty(value = "last_name")
	private String lastName;

	private String email;

	@JsonProperty(value = "billing_data_country")
	private String billingDataCountry;

	@JsonProperty(value = "billing_data_account")
	private String billingDataAccount;

	@JsonProperty(value = "billing_data_currency")
	private String billingDataCurrency;

}
