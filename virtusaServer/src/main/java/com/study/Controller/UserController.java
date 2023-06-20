package com.study.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.Dto.LoginDto;
import com.study.Dto.UserDto;
import com.study.Service.UserService;
import com.study.response.LoginResponse;



@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true") //used to connect frontend with backend(cors)
@RequestMapping("/register")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/")
	public String registerUser(@RequestBody UserDto userDto) {
		String addUser = userService.addUser(userDto);
		return addUser;
	}
	
	
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto){
		LoginResponse loginUser = userService.loginUser(loginDto);
		return ResponseEntity.ok(loginUser);
	}
	
	@GetMapping("/alluser")
	private ResponseEntity<List<UserDto>> getAllUser(){
			List<UserDto> user = userService.findAllUser();
			return new ResponseEntity<List<UserDto>>(user,HttpStatus.OK);
	}
	
	

}
