package com.brinkmcd.app.ws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.brinkmcd.app.ws.UserRepository;
import com.brinkmcd.app.ws.io.entity.UserEntity;
import com.brinkmcd.app.ws.service.UserService;
import com.brinkmcd.app.ws.shared.Utils;
import com.brinkmcd.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {

		// check for existing user
		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new RuntimeException("Record already exists");
		}

		// copy user data from Dto to Entity (DB model object)
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		// set required fields that were not given in the http request
		String publicUserId = utils.generateUserId(20);
		userEntity.setUserId(publicUserId);
		
		// encrypt password
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		// INSERT query
		UserEntity storedUserDetails = userRepository.save(userEntity);

		// copy data returned from query to Dtos
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);

		return returnValue;
	}

}
