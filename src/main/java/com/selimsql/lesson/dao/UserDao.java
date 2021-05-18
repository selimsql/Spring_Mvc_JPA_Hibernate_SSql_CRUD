package com.selimsql.lesson.dao;

import java.util.List;

import com.selimsql.lesson.domain.User;
import com.selimsql.lesson.dto.UserDTO;

public interface UserDao {
	List<User> queryUserList(UserDTO userDTO);

	User findById(Integer id);

	User findByCode(String code);

	User findByEmail(String email);

	Integer getNextIdFromSequence();

	void insertOrUpdateRow(User row);

	void updateRow(User row);

	void deleteRow(User row);
}
