package inventoryManagement.dao;

import inventoryManagement.domain.User;
import java.util.ArrayList;
import java.util.List;

public class ArrayListUserDao implements UserDao {
    
    private List<User> users;
    
    public ArrayListUserDao() {
        this.users = new ArrayList<>();
    }

    @Override
    public User create(User user) {
        this.users.add(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        for (int i = 0; i < this.users.size(); i++) {
            
            if (this.users.get(i).getUsername() == username) {
                return this.users.get(i);
            }
            
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

    @Override
    public Boolean userCheck(String username, String pw) {
        
        for (int i = 0; i < this.users.size(); i++) {
            
            if (this.users.get(i).getUsername().equals(username) &&
                    this.users.get(i).getPassword().equals(pw)) {
                return true;
            }
            
            
            
        }
        return false;
    }
    
}
