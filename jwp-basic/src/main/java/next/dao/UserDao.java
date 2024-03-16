package next.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import next.model.User;

public class UserDao {
    public void insert(User user){

        JdbcTemplate jdbcTemplate = new JdbcTemplate();


        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,user.getUserId(), user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public void update(User user)  {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();


        String sql = "UPDATE USERS SET password = ?, name= ?, email = ? WHERE userId = ?";
        jdbcTemplate.update(sql,user.getUserId(),user.getPassword(), user.getName(), user.getEmail());
    }


    public User findByUserId(String userId)throws SQLException{
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,userId);
            }
        };

        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
        };
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";
        return jdbcTemplate.queryForObject(sql,pss,rowMapper);
    }
/*
    @SuppressWarnings("unchecked")
    public List<User> findAll() throws SQLException{
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            void setValues(PreparedStatement pstmt) throws SQLException {
            }


            @Override
            Object mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
        };
        String sql = "SELECT userId, password, name, email FROM USERS";
        return (List<User>)jdbcTemplate.query(sql);

    }

 */
}






