package com.mongo.example.mongodemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mongo.example.mongodemo.models.apimodel.User;


@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	
	User findUserByEmail(String email);
		
	@Query(value="SELECT u.user_id,u.address,u.email,u.first_name,u.img,u.last_name FROM user u WHERE u.user_id =:user_id",nativeQuery = true)
	User getUserProfileById(@Param("user_id") int user_id);
	
//	@Query(value="SELECT * FROM user WHERE user_id =?", nativeQuery = true)
//	User getUserProfileById(@Param("user_id") int user_id);


	
//	@Transactional
//	@Modifying
//	@Query(value="UPDATE user SET address = :address WHERE user_id = :user_id", nativeQuery = true)
//	public User	editProfile(int id, User user);
}
