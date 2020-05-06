package inventoryManagement.dao;

import inventoryManagement.domain.User;
import java.util.List;

public interface UserDao {

    User create(User user) ;

    User findByUsername(String username);

    List<User> getAll();
    
    Boolean login(String username, String pw);
    
    Boolean checkUsername(String username);


    
}
