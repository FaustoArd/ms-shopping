package com.lord.securityservice.service;




import com.lord.securityservice.model.User;

public interface UserService  {

	public User findById(Long id);

	public User save(User user);

	

}
