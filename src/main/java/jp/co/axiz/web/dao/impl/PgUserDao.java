package jp.co.axiz.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axiz.web.dao.UserDao;
import jp.co.axiz.web.entity.Admin;
import jp.co.axiz.web.entity.User;

@Transactional
@Repository
public class PgUserDao implements UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_SELECT_ID_AND_PASS = "SELECT * FROM admin WHERE admin_id = ? AND password = ?";

	public List<User> findAll() {
		return jdbcTemplate.query("SELECT * FROM user_info", new BeanPropertyRowMapper<User>(User.class));
	}

	public Admin findByIdAndPass(String id, String pass) {

		List<Admin> adminList = jdbcTemplate.query(SQL_SELECT_ID_AND_PASS,
				new BeanPropertyRowMapper<Admin>(Admin.class), id, pass);

		if (adminList.isEmpty()) {
			return null;
		}
		return adminList.get(0);
	}

	public int insert(User user) {

		String sql = "INSERT INTO user_info VALUES( ? , ? , ? , ? )";
		int insertUserId = 0;

		List<User> userList = jdbcTemplate.query("SELECT * FROM user_info ORDER BY user_id DESC LIMIT 1",
				new BeanPropertyRowMapper<User>(User.class));
		jdbcTemplate.update(sql, (userList.get(0).getUserId() + 1), user.getUserName(), user.getTelephone(),
				user.getPassword());
		insertUserId = userList.get(0).getUserId()+1;
		return insertUserId;
	}

	public List<User> select(User user) {

		String sql = "SELECT * FROM user_info ";

		List<User> userList = new ArrayList<User>();

		boolean idNotNull = false;
		boolean nameNotNull = false;
		boolean telNotNull = false;


		String name = user.getUserName();
		String tel = user.getTelephone();

		if (user.getUserId() != null && !user.getUserId().equals("")) {
			// Idがnullではないなら条件に追加
			sql += " WHERE user_id = ?";

			idNotNull = true;
		}

		if (name != null && !"".equals(name)) {
			// nameがnullではないなら条件に追加
			if (sql.substring(sql.length() - 1).equals("?")) {
				// 既に他の条件が追加されているなら繋げる
				sql += " AND user_name = ?";
			} else {
				// 他の条件が無いなら通常の追加
				sql += " WHERE user_name = ?";
			}

			nameNotNull = true;
		}

		if (tel != null && !"".equals(tel)) {
			// priceがnullではないなら条件に追加
			if (sql.substring(sql.length() - 1).equals("?")) {
				// 既に他の条件が追加されているなら繋げる
				sql += " AND telephone = ?";
			} else {
				// 他の条件が無いなら通常の追加
				sql += " WHERE telephone = ?";
			}

			telNotNull = true;
		}

		if (idNotNull) {
			if (nameNotNull) {
				if (telNotNull) {
					userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), user.getUserId(),
							user.getUserName(), user.getTelephone());
				} else {
					userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), user.getUserId(),
							user.getUserName());
				}
			} else {
				if(telNotNull){
				userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), user.getUserId(),user.getTelephone());
				}else{
					userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), user.getUserId());
				}
			}
		} else if (nameNotNull) {
			if (telNotNull) {
				userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), user.getUserName(),
						user.getTelephone());
			} else {
				userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), user.getUserName());
			}
		} else if (telNotNull) {
			userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), user.getTelephone());
		} else {
			userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
		}
		return userList;

	}

	public int update(User user) {

		String sql = "UPDATE user_info SET user_name = ? , telephone = ? , password = ? WHERE user_id = ?";
		int count = 0;

		count = jdbcTemplate.update(sql, user.getUserName(), user.getTelephone(), user.getPassword(), user.getUserId());

		return count;
	}

	public int delete(User user) {

		String sql = "DELETE FROM user_info WHERE user_id = ?";
		int count = 0;

		count = jdbcTemplate.update(sql, user.getUserId());

		return count;
	}

}
