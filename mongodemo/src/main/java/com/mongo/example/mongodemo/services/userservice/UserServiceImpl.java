package com.mongo.example.mongodemo.services.userservice;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mongo.example.mongodemo.exception.BusinessException;
import com.mongo.example.mongodemo.models.apimodel.User;
import com.mongo.example.mongodemo.repository.UserDao;

@Service
public class UserServiceImpl implements UserServiceInterface {

	public static final String EXCHANGE = "javatechie_exchange";
	public static final String ROUTING_KEY = "javatechie_routingKey";

	@Autowired
	private UserDao userDao;

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Override
	public User addnewUser(User user) {
		if (user.getFirst_name().isEmpty() || user.getFirst_name().length() == 0) {
			throw new BusinessException("601", "Please send proper name");
		}
		try {
			User user1 = userDao.save(user);
			return user1;
		} catch (IllegalArgumentException e) {
			throw new BusinessException("602", "Given user object is blank" + e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("603",
					"Something went wrong in service layer while saving new user" + e.getMessage());
		}
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list = null;
		try {
			list = userDao.findAll();
			rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, list);
		} catch (Exception e) {
			throw new BusinessException("604",
					"Something went wrong in service layer while fetching all users" + e.getMessage());
		}
		if (list.isEmpty())
			throw new BusinessException("605", "list is empty");
		return list;
	}

	@Override
	public User findUserById(int userId) {
		try {
			System.out.println("try");
			User user = userDao.findById(userId).get();
			System.out.println(user);
			return user;
		} catch (IllegalArgumentException e) {
			throw new BusinessException("606", "Given user id is null. " + e.getMessage());
		} catch (Exception e1) {
			throw new BusinessException("607", "Given empolyee id does not exist in database, " + e1.getMessage());
		}
	}

	@Override
	public User findUserByEmail(String email) {
		User user = null;
		try {
			System.out.println("try =>findUserByEmail");
			user = userDao.findUserByEmail(email);
			System.out.println(user);
		} catch (Exception e1) {
			throw new BusinessException("608", "Given empolyee email does not exist in database, " + e1.getMessage());
		}
		if (user == null)
			throw new BusinessException("609", "Given user  is null");
		return user;
	}

	@Override
	public User validateUser(User cred) {
		User user;
		try {
			user = userDao.findUserByEmail(cred.getEmail());
		} catch (Exception e) {
			throw new BusinessException("611",
					"Something went wrong in service layer while saving new user" + e.getMessage());
		}
		if (user != null && user.getPassword().equals(cred.getPassword())) {
			User result = user;
			return result;
		} else {
			throw new BusinessException("612", "Please send proper details");
		}
	}

	@Override
	public User editProfile(int id, User user) {
		User user1;
		try {
			user1 = userDao.findById(id).get();
			user1.setAddress(user.getAddress());
			user1.setLast_name(user.getLast_name());
			System.out.println(user.getLast_name());
			user1.setImg(user.getImg());
		} catch (IllegalArgumentException e) {
			throw new BusinessException("615", "Given user id is null. " + e.getMessage());
		} catch (Exception e1) {
			throw new BusinessException("616", "something wrong in service layer, " + e1.getMessage());
		}
		return userDao.save(user1);
	}

	@Override
	public User getUserProfileById(int user_id) {
		try {
			return userDao.getUserProfileById(user_id);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("613", "Given user id is null. " + e.getMessage());
		} catch (Exception e1) {
			throw new BusinessException("614", "Given empolyee id does not exist in database, " + e1.getMessage());
		}
	}

}
