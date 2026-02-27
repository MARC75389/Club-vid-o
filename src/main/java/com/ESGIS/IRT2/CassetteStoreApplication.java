package com.ESGIS.IRT2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan("com.ESGIS.IRT2.Model") // On pointe explicitement vers vos modèles
public class CassetteStoreApplication {

	public static void main(String[] args) {

		SpringApplication.run(CassetteStoreApplication.class, args);
	}

}
