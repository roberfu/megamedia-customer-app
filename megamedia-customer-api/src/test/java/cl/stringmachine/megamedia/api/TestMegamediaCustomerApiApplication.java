package cl.stringmachine.megamedia.api;

import org.springframework.boot.SpringApplication;

public class TestMegamediaCustomerApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(MegamediaCustomerApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
