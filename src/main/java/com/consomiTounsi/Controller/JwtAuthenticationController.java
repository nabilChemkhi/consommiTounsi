package com.consomiTounsi.Controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.consomiTounsi.Config.JwtTokenUtil;
import com.consomiTounsi.entities.JwtRequest;
import com.consomiTounsi.entities.JwtResponse;
import com.consomiTounsi.entities.UserDTO;
import com.consomiTounsi.entities.Users;
import com.consomiTounsi.services.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

/*	@RequestMapping(value = "/authenticate", method = RequestMethod.POST
			
			)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
System.out.println(authenticationRequest.getUsername());
System.out.println("a");
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	*/
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	
	public String createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
System.out.println(authenticationRequest.getUsername());
System.out.println("a");
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		return token;
		//return ResponseEntity.ok(new JwtResponse(token));
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST )
	//public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		public ResponseEntity<?> saveUser(@RequestBody Users user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			System.out.println("aa"+username+"aaa"+password);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}