package com.mongo.example.mongodemo.services.userservice;

import java.util.List;
import com.mongo.example.mongodemo.models.apimodel.User;

public interface UserServiceInterface {

	public List<User> getAllUsers();

	public User findUserById(int userId);

	public User findUserByEmail(String email);

	public User validateUser(User cred);

	public User addnewUser(User user);

	public User getUserProfileById(int id);
	
	public User editProfile(int id, User user);

}
