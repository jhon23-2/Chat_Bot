package com.procecingData.procecingDataSpting;

import com.procecingData.procecingDataSpting.entity.PersonEntity;
import com.procecingData.procecingDataSpting.entity.RoleEntity;
import com.procecingData.procecingDataSpting.entity.RoleEnum;
import com.procecingData.procecingDataSpting.service.serviceInterface.PersonService;
import com.procecingData.procecingDataSpting.service.serviceInterface.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class ProcecingDataSptingApplication {

	@Autowired
	private RoleService roleService;

	@Autowired
	private PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(ProcecingDataSptingApplication.class, args);
	}
	@Bean
	CommandLineRunner init(){
		return args -> {
			RoleEntity roleUser = RoleEntity.builder()
					.id(2L)
					.role(RoleEnum.USER)
					.build();

			RoleEntity roleAdmin = RoleEntity.builder()
					.id(1L)
					.role(RoleEnum.ADMIN)
					.build();

			PersonEntity personAdmin = PersonEntity.builder()
					.name("Jhonatan")
					.username("jhoalmanza52@gmail.com")
					.password("$2a$10$7hxNksl5Sw13FF9l0D5xoug758rkU8jFudm6A.6Ef6/vNESmmSkJ.")
					.roles(Set.of(roleAdmin))
					.build();

			PersonEntity personAdmin2 = PersonEntity.builder()
					.name("Sultan")
					.username("Sultan@gmail.com")
					.password("$2a$10$S138kQljO3VF59wnBJwzNeI5HmubiH/UDrlvmnKpfq1hr1tH8BgLm")
					.roles(Set.of(roleAdmin))
					.build();

			PersonEntity personUser = PersonEntity.builder()
					.name("Sandy")
					.username("sandy@gmail.com")
					.password("$2a$10$7vJfgIfgMvUFuTnrwA25henbegSEUbhOcLancbEHEkYtcWMeHbmKu")
					.roles(Set.of(roleUser))
					.build();

			roleService.saveAllRole(Set.of(roleAdmin,roleUser));
			personService.saveUser(personAdmin);
			personService.saveUser(personAdmin2);
			personService.saveUser(personUser);

		};
	}
}
