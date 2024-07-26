package cl.stringmachine.megamedia.api.customer.client.countryinfoapi.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryInfoApiDto {

	private String name;

	private Map<String, Currency> currencies;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Currency {
		private String name;
		private String symbol;
	}

}
