package com.rungroop.web.service;

import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rungroop.web.dto.RegistrationDto;
import com.rungroop.web.models.Role;
import com.rungroop.web.models.UserEntity;
import com.rungroop.web.repository.RoleRepository;
import com.rungroop.web.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void saveUser(RegistrationDto registrationDto) {
		
		UserEntity user = new UserEntity();
		user.setUsername(registrationDto.getUsername());
		user.setEmail(registrationDto.getEmail());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		Role role = roleRepository.findByName("USER");
		user.setRoles(Arrays.asList(role));		
		userRepository.save(user);	}

	@Override
	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}








