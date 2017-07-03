package jp.co.axiz.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axiz.web.dao.UserDao;
import jp.co.axiz.web.entity.Admin;
import jp.co.axiz.web.entity.User;
import jp.co.axiz.web.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public Admin findByIdAndPass(String id, String pass) {
		return userDao.findByIdAndPass(id, pass);

	}

	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public List<User> select(User user) {
		return userDao.select(user);
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public int delete(User user) {
		return userDao.delete(user);
	}
}
