package com.soc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soc.dto.LoginRequest;
import com.soc.dto.SignupRequest;
import com.soc.service.AuthService;
import com.soc.util.CustomException;
import com.soc.util.SuccessMessage;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<SuccessMessage<String>> login(@RequestBody LoginRequest loginRequest) throws CustomException
	{
		String result = authService.login(loginRequest);
		SuccessMessage<String> message = new SuccessMessage<String>();
		message.setData(result);
		return ResponseEntity.ok(message);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<SuccessMessage<String>> signup(@RequestBody SignupRequest signupRequest)
	{
		String result = authService.signup(signupRequest);
		SuccessMessage<String> message = new SuccessMessage<String>();
		message.setData(result);
		return ResponseEntity.ok(message);
	}
}
