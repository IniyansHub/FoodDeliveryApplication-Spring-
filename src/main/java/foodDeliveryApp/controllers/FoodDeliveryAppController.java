package foodDeliveryApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import foodDeliveryApp.models.AuthenticationRequest;
import foodDeliveryApp.models.AuthenticationResponse;
import foodDeliveryApp.models.UserDto;
import foodDeliveryApp.services.CustomUserDetailsService;
import foodDeliveryApp.util.JwtUtil;


@Controller
public class FoodDeliveryAppController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService userDetailsService; 
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String welcome() {
		return "Welcome";
	}
	
	@RequestMapping(value = "/authenticate",method=RequestMethod.POST)
	// ResponseEntity is HTTP
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
		
		System.out.println(jwt);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	
	
	
	/*
	 * @Autowired UserRepository repo;
	 * 
	 * 
	 * @RequestMapping("/login") public String loginPage() {
	 * 
	 * return "login.jsp";
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @RequestMapping("/check") public ModelAndView authenticateUser(User user) {
	 * 
	 * String email = user.getEmail();
	 * 
	 * ModelAndView mv = new ModelAndView();
	 * 
	 * if (repo.findByEmail(email) != null &&
	 * repo.findByEmail(email).getPassword().equals(user.getPassword())) {
	 * 
	 * String username = repo.findByEmail(email).getUsername();
	 * 
	 * mv.addObject("username",username); mv.setViewName("home.jsp");
	 * 
	 * return mv;
	 * 
	 * } else {
	 * 
	 * String errorMessage = "Invalid username or password";
	 * mv.addObject("error",errorMessage); mv.setViewName("login.jsp"); return mv; }
	 * 
	 * }
	 * 
	 * 
	 * @RequestMapping("/signup") public String signupPage() {
	 * 
	 * return "signup.jsp"; }
	 * 
	 * @RequestMapping("/add") public String addUser(User user) {
	 * 
	 * if (repo.findByEmail(user.getEmail()) == null) { repo.save(user); return
	 * "login.jsp"; } else { System.out.println("User exist already!"); return
	 * "singup.jsp"; }
	 * 
	 * }
	 */

}
