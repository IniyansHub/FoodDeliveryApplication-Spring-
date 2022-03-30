package foodDeliveryApp.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import foodDeliveryApp.models.User;
import foodDeliveryApp.repository.UserRepository;

@Controller
public class FoodDeliveryAppController {

	@Autowired
	UserRepository repo;

	@RequestMapping("/login")
	public String loginPage() {

		return "login.jsp";

	}
	
	

	@RequestMapping("/check")
	public ModelAndView authenticateUser(User user) {
		
		String email = user.getEmail();
		
		ModelAndView mv = new ModelAndView();
		
		if (repo.findByEmail(user.getEmail()) != null
				&& repo.findByEmail(email).getPassword().equals(user.getPassword())) {
			
			String username = repo.findByEmail(email).getUsername();
			
			mv.addObject("username",username);
			mv.setViewName("home.jsp");

			return mv;

		} else {
			
			String errorMessage = "Invalid username or password";
			mv.addObject("error",errorMessage);
			mv.setViewName("login.jsp");
			return mv;
		}

	}
	

	@RequestMapping("/signup")
	public String signupPage() {

		return "signup.jsp";
	}

	@RequestMapping("/add")
	public String addUser(User user) {

		if (repo.findByEmail(user.getEmail()) == null) {
			repo.save(user);
			return "login.jsp";
		} else {
			System.out.println("User exist already!");
			return "singup.jsp";
		}

	}

}
