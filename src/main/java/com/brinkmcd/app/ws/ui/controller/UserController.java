package com.brinkmcd.app.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brinkmcd.app.ws.service.UserService;
import com.brinkmcd.app.ws.shared.dto.UserDto;
import com.brinkmcd.app.ws.ui.model.request.UserDetailsRequestModel;
import com.brinkmcd.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUser() {
		return "get user was called";
	}
	
	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
		// instantiate a UserRest obj to send as http response (UI layer)
		UserRest returnValue = new UserRest();
		
		// instantiate a UserDto for DB insert transaction (data layer)
		UserDto userDto = new UserDto();
		
		// copy data from the request object (userDetails) to the Dto
		// (UI -> data layer)
		BeanUtils.copyProperties(userDetails, userDto);
		
		// instantiate a UserDto for result of transaction (data layer)
		UserDto createdUser = userService.createUser(userDto);
		
		// copy data from Dto object to response object (data layer -> UI)
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
	}
	
	@PutMapping
	public String updateUser() {
		return "update user was called";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "delete user was called";
	}
}
