package com.root.controller;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.root.dto.LoginDto;
import com.root.dto.RegistroDto;
import com.root.entity.Rol;
import com.root.entity.User;
import com.root.repository.RolRepository;
import com.root.repository.UserRepository;
import com.root.security.JwtAuthResponseDto;
import com.root.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponseDto> authenticateUser(@RequestBody LoginDto loginDto) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new JwtAuthResponseDto(token));
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody RegistroDto registerDto) {
		if (userRepository.existsByUsername(registerDto.getUsername())) {
			return new ResponseEntity<>("User has already exist", HttpStatus.BAD_REQUEST);
		}
		if (userRepository.existsByEmail(registerDto.getEmail())) {
			return new ResponseEntity<>("Email has already registered", HttpStatus.BAD_REQUEST);
		}

		User user = new User();
		user.setName(registerDto.getName());
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

		Rol rols = rolRepository.findByName("ROLE_ADMIN").get();
		user.setRols(Collections.singleton(rols));

		userRepository.save(user);

		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
	}
}
