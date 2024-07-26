package cl.stringmachine.megamedia.api.customer.client.countryinfoapi.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.stringmachine.megamedia.api.customer.client.countryinfoapi.CountryInfoApiClient;
import cl.stringmachine.megamedia.api.customer.client.countryinfoapi.dto.CountryInfoApiDto;
import cl.stringmachine.megamedia.api.customer.client.countryinfoapi.dto.CountryInfoApiDto.Currency;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryInfoApiClientImpl implements CountryInfoApiClient {

	private final RestTemplate restTemplate;

	private static final String BASE_URL = "https://countryinfoapi.com/api/countries/name/{country}";

	@Override
	public String getCurrencyData(String country) {

		CountryInfoApiDto countryInfoApiDto = restTemplate.getForObject(BASE_URL, CountryInfoApiDto.class, country);

		return getFirstCurrencyName(countryInfoApiDto.getCurrencies());

	}

	public String getFirstCurrencyName(Map<String, Currency> currencies) {
		if (currencies != null && !currencies.isEmpty()) {
			String firstCurrencyCode = currencies.keySet().iterator().next();
			return currencies.get(firstCurrencyCode).getName();
		}
		return null;
	}

}
