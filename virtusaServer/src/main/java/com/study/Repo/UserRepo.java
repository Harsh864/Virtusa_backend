package com.study.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.Entity.User;
import com.study.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);

}
