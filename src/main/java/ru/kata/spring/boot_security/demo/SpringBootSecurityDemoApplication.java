package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.HashSet;

@SpringBootApplication
public class SpringBootSecurityDemoApplication implements CommandLineRunner {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public SpringBootSecurityDemoApplication(RoleRepository roleRepository,
											 UserRepository userRepository,
											 PasswordEncoder passwordEncoder) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Role admin = new Role(1L,"ROLE_ADMIN");
		Role user = new Role(2L,"ROLE_USER");
		roleRepository.save(admin);
		roleRepository.save(user);


		userRepository.save(new User(1L,"vefv",passwordEncoder.encode("admin"),
				"admin@mail.com",new HashSet<>() {{
			add(admin);
		}}));

		userRepository.save(new User(2L,"wert@mail.ru",passwordEncoder.encode("user"),
				"user",new HashSet<>() {{
			add(user);
		}}));


	}
}
