package jp.co.axiz.web.dao;

import java.util.List;

import jp.co.axiz.web.entity.Admin;
import jp.co.axiz.web.entity.User;

public interface UserDao {
	public Admin findByIdAndPass(String id, String pass);

	public List<User> findAll();

	public int insert(User user);

	public List<User> select(User user);

	public int update(User user);

	public int delete(User user);
}
