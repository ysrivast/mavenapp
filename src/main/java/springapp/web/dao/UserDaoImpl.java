package springapp.web.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import springapp.web.mapper.UserMapper;
import springapp.web.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	DataSource dataSource;
	@Autowired
	JdbcTemplate jdbcTemplate;
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	public boolean validateUser(User user) {
		logger.info("In UserDao : before executing query");
		String sql = "select * from product_users where username= '"
				+ user.getUserName() + "'and password= '" + user.getPassword()
				+ "' ";
		List<User> listOfUsers = new ArrayList<User>();
		listOfUsers = jdbcTemplate.query(sql, new UserMapper());
		logger.info("In UserDao : after executing query" + listOfUsers.size());
		return (listOfUsers.size() > 0 ? true : false);
	}

	public User getUserDetail(User user) {
		logger.info("In UserDao : before executing query");
		String sql = "select * from product_users where username= '"
				+ user.getUserName() + "'and password= '" + user.getPassword()
				+ "' ";
		List<User> resultUser = new ArrayList<User>();
		resultUser = jdbcTemplate.query(sql, new UserMapper());
		return resultUser.get(0);
	}

	public boolean registerUser(User user) {
		String sql= "insert into product_users values ('"+user.getUserName()+"','"+user.getPassword()+"','"+user.getGender()+"')";
		String sql1= "insert into user_roles values ('"+user.getUserName()+"', 'customer')";
		logger.info("In UserDao before executing query : "+sql);
		int count=jdbcTemplate.update(sql);
		logger.info("In UserDao after executing supporting  query number of row inserted is : "+jdbcTemplate.update(sql1));
		logger.info("In UserDao after executing query number of row inserted is : "+count);
		return count>0 ? true : false;
	}

}
