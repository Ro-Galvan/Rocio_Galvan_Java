package com.gamestore.gamestorecatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/*added this so app can register with the Eureka Service Registry on startup
& allows this app to contact the Eureka Service Registry to lookup the invoice Service location and connection information.
*/
@SpringBootApplication
@EnableDiscoveryClient
public class GamestoreCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamestoreCatalogApplication.class, args);
	}

}
