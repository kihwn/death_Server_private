package next.dao;

import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

        public void update(String sql,Object... parameters) throws DataAccessException{
            try(Connection conn = ConnectionManager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                for (int i=0; i< parameters.length; i++){
                    pstmt.setObject(i + 1, parameters[i]);
                }
                pstmt.executeUpdate();

                } catch (SQLException e){
                    throw new DataAccessException(e);
                    }
                }




    public <T> List<T> query(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) throws DataAccessException{

        ResultSet rs = null;
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pss.setValues(pstmt);
            rs = pstmt.executeQuery();
            List<T> list = new ArrayList<T>();
            while (rs.next()){
                list.add(rowMapper.mapRow(rs));
            }
            return list;
        }catch (SQLException e){
            throw new DataAccessException(e);
        }finally {
            /*
            if (rs!=null){

                rs.close();
            }
            if (pstmt != null){
                pstmt.close();
            }
            if (con !=null){
                con.close();
            }

             */


        }

    }

    @SuppressWarnings("rawtypes")
    public <T> T queryForObject(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) throws SQLException{
        List<T> result = query(sql,pss,rowMapper);
        if (result.isEmpty()){
            return null;
        }
        return result.get(0);
    }
}

