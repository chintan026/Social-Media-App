package com.soc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.soc.dto.LoginRequest;
import com.soc.dto.SignupRequest;
import com.soc.entity.User;
import com.soc.repository.UserRepository;
import com.soc.security.JwtService;
import com.soc.util.CustomException;

@Service
public class AuthService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	public String signup(SignupRequest signupRequest)
	{
		User user = new User();
		user.setEmail(signupRequest.getEmail());
		user.setName(signupRequest.getName());
		user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		user.setUsername(signupRequest.getUsername());
		userRepository.saveAndFlush(user);
		return "Profile Created Successfully";
		
	}
	
	public String login(LoginRequest loginRequest) throws CustomException
	{
		Optional<User> userOp = userRepository.findByUsername(loginRequest.getUsername());
		
		if(userOp.isPresent())
		{
			User user = userOp.get();
			if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
			{
				return jwtService.generateToken(user);
			}
			else
			{
				throw new CustomException("Invalid Credentials !!");
			}
		}
		else
		{
			throw new CustomException("User not Found !");
		}
	}
}	
