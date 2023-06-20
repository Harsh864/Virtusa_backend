package com.study.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.Dto.LoginDto;
import com.study.Dto.UserDto;
import com.study.Dto.UserDto;
import com.study.Entity.User;
import com.study.Entity.User;
import com.study.Repo.UserRepo;
import com.study.Service.UserService;
import com.study.response.LoginResponse;

@Service
public class UserImpl implements UserService{
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepo userRepo;

	@Override
	public String addUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userRepo.save(user);
		return user.getUserName();
	}
	
	

	@Override
	public LoginResponse loginUser(LoginDto loginDto) {
		User user = userRepo.findByEmail(loginDto.getEmail());
		if(user == null) return new LoginResponse("No email exists",false);
		String currPass = loginDto.getPassword();
		String encodePass = user.getPassword();
		Boolean isMatched = passwordEncoder.matches(currPass, encodePass);
		if(!isMatched) {
			return new LoginResponse("Password is incorrect", false);
		}else {
			return new LoginResponse("Successfully LogedIn",true);
		}
	}



	@Override
	public List<UserDto> findAllUser() {
		List<User> users = userRepo.findAll();
		List<UserDto> userDtos = users.stream().map((user)->modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return userDtos;
	}

}
