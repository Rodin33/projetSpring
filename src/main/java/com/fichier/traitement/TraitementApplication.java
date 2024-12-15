package com.fichier.traitement;

import com.fichier.traitement.entity.AppUser;
import com.fichier.traitement.entity.Role;
import com.fichier.traitement.repository.AppUserRepository;
import com.fichier.traitement.repository.OperationEntreeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class TraitementApplication  {
	public static void main(String[] args) {
		SpringApplication.run(TraitementApplication.class, args);
	}


}
