package com.selimsql.lesson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.selimsql.lesson.dao.UserDao;
import com.selimsql.lesson.domain.User;
import com.selimsql.lesson.dto.UserDTO;
import com.selimsql.lesson.util.Util;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Throwable.class)
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> queryUserList(UserDTO userDTO) {
		List<User> list = userDao.queryUserList(userDTO);
		return list;
	}

	@Override
	public User findById(Integer id) {
		User user = userDao.findById(id);
		return user;
	}

	@Override
	public User findByCode(String code) {
		User user = userDao.findByCode(code);
		return user;
	}

	@Override
	public User findByEmail(String email) {
		User user = userDao.findByEmail(email);
		return user;
	}

	@Override
	@Transactional(readOnly=false)
	public boolean newUserSave(User user, BindingResult result) {
		final boolean addUser = true;
		validateNewData(user, addUser, result);

		if (result.hasErrors())
			return false;

		insertOrUpdateRow(user);
		return true;
	}

	private void validateNewData(User user, boolean add, Errors errors) {
		String code = (user==null ? null : user.getCode());
		if (Util.isEmpty(code)) {
			errors.rejectValue("code", "required.Code");
		}
		else {
			User userBefore = findByCode(code);
			if (userBefore!=null) {
				if (add) {
					errors.rejectValue("code", "Row.codeX.already.exist.Please.enter.different.code", new Object[]{code}, "Error");
				}
				else
				//if (update)
				if (Util.isNotEqual(user.getId(), userBefore.getId()))
				{
					errors.rejectValue("code", "message.generic", new Object[]{"UserCode:" + code + " has already been defined!"}, "Error");
				}
			}

			String codeSmoothed = Util.codeFromStr(code);
			if (Util.isNotEqual(code, codeSmoothed)) {
				errors.rejectValue("code", "message.generic", new Object[]{"Code is invalid! Suggestion code:" + codeSmoothed}, "Error");
			}
		}


		//------------------------------
		String email = (user==null ? null : user.getEmail());
		if (Util.isEmpty(email)) {
			errors.rejectValue("email", "required.Email");
		}
		else {
			User userBefore = findByEmail(email);
			if (userBefore!=null) {
				if (add) {
					errors.rejectValue("email", "Row.emailX.already.exist.Please.enter.different.email", new Object[]{email}, "Error");
				}
				else
				//if (update)
				if (Util.isNotEqual(user.getId(), userBefore.getId()))
				{
					errors.rejectValue("email", "message.generic", new Object[]{"UserEmail:" + email + " has already been defined!"}, "Error");
				}
			}

			if (Util.isEMailValid(email)==false) {
				errors.rejectValue("email", "invalid.Email");
			}
		}

		ValidationUtils.rejectIfEmpty(errors, "name", "required.Name");
		ValidationUtils.rejectIfEmpty(errors, "surname", "required.Surname");
		ValidationUtils.rejectIfEmpty(errors, "password", "required.Password");
		ValidationUtils.rejectIfEmpty(errors, "status", "required.Status");
	}//validate_NewData

	@Override
	@Transactional(readOnly=false)
	public void insertOrUpdateRow(User row) {
		if (row.getId()==null) {
			Integer idSeq = userDao.getNextIdFromSequence();
			row.setId(idSeq);
		}

		userDao.insertOrUpdateRow(row);
	}


	@Override
	@Transactional(readOnly=false)
	public boolean updateUserSave(User user, BindingResult result) {
		final boolean addUser = false; //update!
		validateNewData(user, addUser, result);

		if (result.hasErrors())
			return false;

		updateRow(user);
		return true;
	}

	@Override
	@Transactional(readOnly=false)
	public void updateRow(User row) {
		userDao.updateRow(row);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteRow(User row) {
		userDao.deleteRow(row);
	}
}
