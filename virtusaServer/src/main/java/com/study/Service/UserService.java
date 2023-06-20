package com.study.Service;

import java.util.List;

import com.study.Dto.LoginDto;
import com.study.Dto.UserDto;
import com.study.Dto.UserDto;
import com.study.response.LoginResponse;

public interface UserService {
	
	String addUser(UserDto userDto);
	
	LoginResponse loginUser(LoginDto loginDto);
	
	List<UserDto> findAllUser();

}
