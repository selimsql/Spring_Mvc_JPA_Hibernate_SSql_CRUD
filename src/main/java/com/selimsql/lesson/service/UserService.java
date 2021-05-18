package com.selimsql.lesson.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.selimsql.lesson.domain.User;
import com.selimsql.lesson.dto.UserDTO;

public interface UserService {

	List<User> queryUserList(UserDTO userDTO);

	User findById(Integer id);

	User findByCode(String code);

	User findByEmail(String email);

	boolean newUserSave(User user, BindingResult result);

	void insertOrUpdateRow(User row);

	boolean updateUserSave(User user, BindingResult result);

	void updateRow(User row);

	void deleteRow(User row);
}