package com.mongo.example.mongodemo.controller;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.example.mongodemo.config.MessagingConfig;
import com.mongo.example.mongodemo.exception.BusinessException;
import com.mongo.example.mongodemo.exception.ControllerException;
import com.mongo.example.mongodemo.models.apimodel.User;
import com.mongo.example.mongodemo.services.userservice.UserServiceInterface;

@RestController
@RequestMapping("/user")
//@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	@Autowired
	private UserServiceInterface userServiceInterface;
	
	 @Autowired
	    private RabbitTemplate template;
	 
	public UserController(UserServiceInterface userServiceInterface) {
		this.userServiceInterface = userServiceInterface;
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers()
	{
		List<User> user = null;
		try {
		 user =userServiceInterface.getAllUsers();
	       System.out.println("try=> getall");
		 template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, user);

		return new ResponseEntity<List<User>>(user,HttpStatus.OK);
		}
		catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			ControllerException ce = new ControllerException("622","something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody User cred)
	{
		User user1;
		try {
			user1 =userServiceInterface.validateUser(cred);
		}
		catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			ControllerException ce = new ControllerException("621","something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		if(user1==null)
		{
			return new ResponseEntity<User>(user1, HttpStatus.LENGTH_REQUIRED);
		}
		return new ResponseEntity<String>("successfully signed in",HttpStatus.OK);
			}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user)
	{
		try {
		User result=userServiceInterface.addnewUser(user);
		return new ResponseEntity<User>(result,HttpStatus.CREATED);
		}
		catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			ControllerException ce = new ControllerException("623","something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	

	@GetMapping("/findbyid/{id}")
	public ResponseEntity<?> findByUserId(@PathVariable("id") int id)
	{
		try {
		User result=userServiceInterface.findUserById(id);
			return new ResponseEntity<User>(result,HttpStatus.OK);
		}
		catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			ControllerException ce = new ControllerException("624","something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/findbyemail/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable("email") String email)
	{
		try {
		User result=userServiceInterface.findUserByEmail(email);
			return new ResponseEntity<User>(result, HttpStatus.OK);
		}
		catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
		ControllerException ce = new ControllerException("625","Something went wrong in controller");
		return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
	}
	}
	
	@GetMapping("/getuserprofile/{user_id}")
	public ResponseEntity<?> getuserProfile(@PathVariable("user_id") int user_id)
	{
		try {
		User result=userServiceInterface.getUserProfileById(user_id);
			return new ResponseEntity<User>(result,HttpStatus.FOUND);
		}
		catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			ControllerException ce = new ControllerException("626","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PutMapping("/editprofile/{id}")
	public ResponseEntity<?> editProfile(@PathVariable("id") int id, @RequestBody User user) {
		try {
		User result = userServiceInterface.editProfile(id, user);
		return new ResponseEntity<User>(result,HttpStatus.OK);

		}
		catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			ControllerException ce = new ControllerException("627","something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
}
