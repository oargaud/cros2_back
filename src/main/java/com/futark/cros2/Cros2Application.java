package com.futark.cros2;

import com.futark.cros2.entity.Bd;
import com.futark.cros2.entity.Role;
import com.futark.cros2.repository.BdRepo;
import com.futark.cros2.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
public class Cros2Application {


	@Autowired
	UtilisateurService utilisateurService;

	@Autowired
	BdRepo bdRepo;

	public static void main(String[] args) {
		SpringApplication.run(Cros2Application.class, args);
	}



	@Bean
	public CommandLineRunner run() {
		return (args) -> {


//			for(int i =0; i<250;i++){
//				Bd bd  = new Bd();
//				bd.setTitre("titre_"+i);
//				bd.setTome("tome_"+(250-i));
//				bdRepo.save(bd);
//			}



		};
	}

}
