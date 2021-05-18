package com.selimsql.lesson.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.selimsql.lesson.domain.User;
import com.selimsql.lesson.dto.UserDTO;
import com.selimsql.lesson.util.Util;

@Repository("userDao")
public class UserDaoImp implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private Criteria createEntityCriteria(){
		return getSession().createCriteria(User.class);
	}

	public User getByKey(Integer key) {
		return (User) getSession().get(User.class, key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryUserList(UserDTO userDTO) {
		Criteria criteria = createEntityCriteria();

		String code = userDTO.getCode();
		if (StringUtils.hasLength(code))
			criteria.add(Restrictions.ilike("code", code + "%"));

		String name = userDTO.getName();
		if (StringUtils.hasLength(name))
			criteria.add(Restrictions.ilike("name", name + "%"));

		String surname = userDTO.getSurname();
		if (StringUtils.hasLength(surname))
			criteria.add(Restrictions.ilike("surname", surname + "%"));

		criteria.addOrder(Order.asc("name"));

		List<User> list = criteria.list();

		return list;
	}

	@Override
	public User findById(Integer id) {
		User user = getByKey(id);
		return user;
	}

	@Override
	public User findByCode(String code) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("code", code));
		User user = (User)criteria.uniqueResult();
		return user;
	}

	@Override
	public User findByEmail(String email) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("email", email));
		User user = (User)criteria.uniqueResult();
		return user;
	}

	@Override
	public Integer getNextIdFromSequence() {
		String sql = "Select SequenceNextValue(SequenceName)"
					+ "	From _DB_Sequence Where SequenceName = 'SEQ_USERPK'";

		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		Object objNextId = sqlQuery.uniqueResult();
		if (objNextId==null)
			return null;

		return Integer.valueOf(Util.getInt(objNextId));
	}

	@Override
	public void insertOrUpdateRow(User row) {
		getSession().save(row);
	}

	@Override
	public void updateRow(User row) {
		getSession().merge(row);
	}

	@Override
	public void deleteRow(User row) {
		getSession().delete(row);
	}
}
