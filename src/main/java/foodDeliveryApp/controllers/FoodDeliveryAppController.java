package foodDeliveryApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import foodDeliveryApp.models.AuthenticationRequest;
import foodDeliveryApp.models.AuthenticationResponse;
import foodDeliveryApp.models.UserDto;
import foodDeliveryApp.services.CustomUserDetailsService;
import foodDeliveryApp.util.JwtUtil;


@RestController
@CrossOrigin
public class FoodDeliveryAppController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService userDetailsService; 
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String welcome() {
		return "Welcome";
	}
	
	@RequestMapping(value = "/authenticate",method=RequestMethod.POST)

	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try{
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
				authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e){
			throw new Exception("Incorrect Username or Password");
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	
		final String jwt = jwtTokenUtil.generateToken(userDetails);
				
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

}
