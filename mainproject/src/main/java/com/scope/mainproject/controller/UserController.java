package com.scope.mainproject.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scope.mainproject.model.City;
import com.scope.mainproject.model.Contact;
import com.scope.mainproject.model.Country;
import com.scope.mainproject.model.State;
import com.scope.mainproject.model.User;
import com.scope.mainproject.repository.UserRepository;
import com.scope.mainproject.service.Cityservice;
import com.scope.mainproject.service.ContactService;
import com.scope.mainproject.service.Countryservice;
import com.scope.mainproject.service.Stateservice;
import com.scope.mainproject.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private Countryservice countryser;
	@Autowired
	private Stateservice stateser;
	@Autowired
	private Cityservice cityser;
	@Autowired
	private UserRepository urepo;
	@Autowired
	private ContactService contactService;
	@RequestMapping("/")
	public String home(){
		return "home";
	}
	
	
	@RequestMapping("/register")
	public String regnform (Model model)
	{
		List<Country> countrylist=countryser.countrylist();
		model.addAttribute("User",new User());
		model.addAttribute("country",countrylist);
		return "regnform";

	}
	
	@RequestMapping("/contact")
	public String link1(Model model){
		return"Contact";
	}
	@RequestMapping("/login")
	public String link2(Model model){
		return"login22";
	}
	@RequestMapping("/about")
	public String link3(Model model){
		return"about";
	}
	@RequestMapping("/home")
	public String link4(Model model){
		return"home";
	}

	@RequestMapping("/forgot")
	public String link6(Model model){
		model.addAttribute("data", new User());
		return"Otp";
	}
	@RequestMapping("/course")
	public String link7(Model model){
		model.addAttribute("cou", new User());
		return"Courses";
	}
	@RequestMapping("/veri")
	public String link8(Model model){
		model.addAttribute("data", new User());
		return"Verifyotp";
	}
	@RequestMapping("/dash")
	public String link9(Model model){
		
		return"dashboard";
	}
	
	@RequestMapping("/change")
	public String link11(Model model){
		model.addAttribute("chang",new User());
		return"change";
	}



	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("con",new Contact());
	    return "Contact";	
	}
	@PostMapping("/contact")
	public String contact_add(@ModelAttribute("con") Contact con) {
		contactService.add(con);
		return"home";
	}
	
	
	
	@RequestMapping("send")
	public String registration(@ModelAttribute("User") User user,HttpServletRequest request)throws UnsupportedEncodingException, MessagingException 
	{
		userService.insert(user);
		userService.register(user,getSiteURL(request));
		return "Otp";
	}

	@GetMapping("/states/{id}")
	public @ResponseBody Iterable<State> getStateByCountry(@PathVariable("id") Country countryid){
		return stateser.getStateBy(countryid);
	}
	@GetMapping("/cities/{id}")
	public @ResponseBody Iterable<City> getStateByState(@PathVariable("id") State stateid){
		return cityser.getCityBy(stateid);
	}
	private String getSiteURL(HttpServletRequest request) {
			String siteurl=request.getRequestURL().toString();
			return siteurl.replace(request.getServletPath(),"");
	}
	@RequestMapping("/verify")
	public String verify(@Param("code")String code,Model model) {
		if(userService.verify(code)) {
			model.addAttribute("otp",new User());
			return "Otp";
		}else {
			return"success";
		}
	}
	
	@RequestMapping("/send-otp")
	public String login(Model model) {
		model.addAttribute("otp",new User());
		return"Otp";
	}
	
	@PostMapping("/send-otp")
	public String sendOtp(Model model,HttpServletRequest request, @RequestParam("email")String email,User user) throws UnsupportedEncodingException, MessagingException {
	 User existingUser = urepo.findByEmail(email);
	if(existingUser != null) {
	String newOtp = generateRandomOtp();
	existingUser.setOtp(newOtp);

	urepo.save(existingUser);
	userService.sendEmail(email,newOtp);
	model.addAttribute("verify",new User());
	model.addAttribute("email",email);
	return "Verifyotp";
} else {
	return "Error1";
	}
}

	@PostMapping("/verify-otp")
	public String verifyOTP(@RequestParam("email") String email, @RequestParam("otp") String enteredOTP, Model model) {
	
	User user = urepo.findByEmail(email);
	System.out.println(user.toString());
	if(user != null && user.getOtp()!= null && user.getOtp().equals(enteredOTP))
	{
	user.setVerified(true);
	user.setOtp(null);
	urepo.save(user);
	model.addAttribute("email",email);
	model.addAttribute("pass",new User());
	return "Password";
} 
	else
{
	return "Error1";
}
	}
	private String generateRandomOtp() {
		String otp = String.valueOf(new Random().nextInt(900000)+100000);
		return otp;
	
}
	
	
	@PostMapping("/password")
	public String password(@RequestParam("email") String email,@RequestParam("password")String password ,Model model) {
		User  user=urepo.findByEmail(email);
		user.setPassword(password);
		urepo.save(user);
		model.addAttribute("email",email);
		model.addAttribute("register",new User());
		return "login22";
	}
	@PostMapping("/login")
	public String login(@RequestParam("email")String email,@RequestParam("password")String password,Model model,HttpSession session,HttpServletResponse response) {
		User user=urepo.findByEmail(email);
		session.setAttribute("name",email);
		if(user!=null && user.getPassword().equals(password) && user.isVerified()) {
			Cookie cookie = new Cookie("username", user.getFirstname());
		    cookie.setMaxAge(600);
		    cookie.setPath("/");
		    response.addCookie(cookie);
			model.addAttribute("register",new User());
			return "Dashboard";
		}
		else {
			return "Error3";
		}
		 
	}
	@RequestMapping("/dashboard")
	public String link5(Model model,HttpSession session){
		String username = (String) session.getAttribute("name");
		 if(username != null) {
			    User regmodel=urepo.findByEmail(username);
			    if(regmodel!=null) {
			    	return"Dashboard";
			    }
			    return "login22";
		 }
		 return "login22";
		
	}
	@GetMapping("/view")
	public String getData(HttpSession session,Model model,@ModelAttribute("user") User user) {
	    String username = (String) session.getAttribute("name");
	    if(username != null) {
	    User regmodel=urepo.findByEmail(username);
	    if(regmodel!=null) {
	    	String firstname=regmodel.getFirstname();
			String lastname=regmodel.getLastname();
			String mail=regmodel.getEmail();
			String gender=regmodel.getGender();
			String contact=regmodel.getPhonenumber();
			String dob=regmodel.getDateofbirth();
			String addr=regmodel.getAddress();
			String hobbie=regmodel.getHobbies();
			String skils = regmodel.getSkills();
			String country=(regmodel.getCountry()).getCountryname();
			String state=(regmodel.getState()).getStatename();
			String city=(regmodel.getCity()).getCityname();
			String course=regmodel.getCourse();
			model.addAttribute("firstname",firstname);
			model.addAttribute("lastname",lastname);
			model.addAttribute("email",mail);
			model.addAttribute("gender",gender);
			model.addAttribute("contact",contact);
			model.addAttribute("dateofbirth",dob);
			model.addAttribute("address",addr);
			model.addAttribute("hobbies",hobbie);
			model.addAttribute("skills",skils);
			model.addAttribute("country", country);
			model.addAttribute("state",state);
			model.addAttribute("city",city);
			model.addAttribute("courses",course);
	    	return "View";
	    }}else {
	    	return "login22";
	    }return "login22";
	    
	}
	@RequestMapping("/edit")
	public String link10(Model model,HttpSession session){
		String username = (String) session.getAttribute("name");
		User regmodel=urepo.findByEmail(username);
		model.addAttribute("edit",regmodel);
		return"edit";
	}
	@RequestMapping("/editprofile")
	public String edit(HttpSession session,Model model,@RequestParam( value="firstname",required=false)String firstname,
			@RequestParam(value="lastname",required=false)String lastname,@RequestParam(value = "number", required = false)String number,
			@RequestParam(value="dob",required=false)String dob,@RequestParam(value="address",required=false)String address
			) {
		String username = (String) session.getAttribute("name");
	    User regmodel=urepo.findByEmail(username);
	    if(regmodel!=null) {

	    	regmodel.setFirstname(firstname);
	    	regmodel.setLastname(lastname);
	    	regmodel.setPhonenumber(number);
	    	regmodel.setDateofbirth(dob);
	    	regmodel.setAddress(address);
	    	urepo.save(regmodel);
	    	return"Dashboard";
	    }
	    
	     else {
		return"Error1";
	}
	}
	@PostMapping("/course")
	public String course(HttpSession session,Model model,@RequestParam("course")String course) {
		String username = (String) session.getAttribute("name");
	    User regmodel=urepo.findByEmail(username);
	    if(regmodel!=null) {
	    	
	    	regmodel.setCourse(course);
	    	urepo.save(regmodel);
	    	
	    	return"Dashboard";
	    }
		else {
			return"Error1";
		}
		
	}
	@PostMapping("/change")
	public String change(HttpSession session,Model model,User user,@RequestParam("password")String password,@RequestParam("newpassword")String newpassword) {
		String username = (String) session.getAttribute("name");
	   User regmodel=urepo.findByEmail(username);
	    if(regmodel!=null && user.getPassword().equals(password))
	    {
	    	regmodel.setPassword(newpassword);
	    	model.addAttribute("chang",new User());
	    	urepo.save(regmodel);
	    	model.addAttribute("register",new User());
	    	return"redirect:/logout";
	    }
	    else {
		return"Error1";
	    }
	}
	@GetMapping("/logout")
	public String logout(Model model,HttpSession session,User user,HttpServletResponse response) {
		String sess = (String) session.getAttribute("name");
		if(sess!=null) {
			session.invalidate();
			Cookie cookie = new Cookie("username", user.getFirstname());
		    cookie.setMaxAge(0);
		    cookie.setPath("/dash");
		    response.addCookie(cookie);
		}
		model.addAttribute("register",new User());
		return"home";
	}
	




	

}

