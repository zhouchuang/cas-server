package user.zc.cas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import user.zc.cas.entity.User;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService extends AbstractService {

    @Transactional
    public void insertUser(User user) {
      /*  String sql = "insert into user(id, username,password,name) values(?,?,?,?)";
        jdbcTemplate.update(sql, user.getId(), user.getUsername(),user.getPassword(),user.getName());
    */
        super.insert(user);
    }

    @Transactional
    public void updateUser(User user) {
        String sql = "update user set name = ? where id = ?";

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement arg0) throws SQLException {
                arg0.setString(1, user.getName());
                arg0.setLong(2, user.getId());
            }
        };
        getJdbcTemplate().update(sql, pss);
    }

    public List<User> selectUser(User user) {
        String sql = "select id,name,username,password from user where 1=1 ";

        RowMapper<User> rowMapper = new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet arg0, int arg1) throws SQLException {
                User user = new User();
                if(arg0.wasNull()) {
                    return user;
                }
                user.setId(arg0.getLong(1));
                user.setName(arg0.getString(2));
                user.setUsername(arg0.getString(3));
                user.setPassword(arg0.getString(4));
                return user;
            }
        };
        Object[] args = null;
        if(user != null) {
            if(user.getId() != null) {
                sql += "and id = ?";
                args = new Object[1];
                args[0] = user.getId();
            }
        }
        return getJdbcTemplate().query(sql, args, rowMapper);
    }

    @Transactional
    public void deleteUser(Integer id) {
        String sql = "delete from user where id = ?";

        getJdbcTemplate().update(sql, String.valueOf(id));
    }
}
