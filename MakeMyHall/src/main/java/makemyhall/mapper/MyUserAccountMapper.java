package makemyhall.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import makemyhall.model.MyUserAccount;
import org.springframework.jdbc.core.RowMapper;
 
public class MyUserAccountMapper implements RowMapper<MyUserAccount> {
 
    //@Override
    public MyUserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        String email = rs.getString("email");
        String userName= rs.getString("user_name");
        int phonenumber = rs.getInt("phonenumber");
        String password = rs.getString("password");
 
        return new MyUserAccount(email,userName, phonenumber, password);
    }
 
}
