package com.gamestore.gamestoreinvoicing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
/* added this so app can register with the Eureka Service Registry on startup
& allows this app to contact the Eureka Service Registry to lookup the catalog Service location and connection information.
* */
//@EnableDiscoveryClient-- dan said not needed
@EnableFeignClients
public class GamestoreInvoicingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamestoreInvoicingApplication.class, args);
	}

}
