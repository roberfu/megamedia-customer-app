package cl.stringmachine.megamedia.api.customer.client.countryinfoapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
