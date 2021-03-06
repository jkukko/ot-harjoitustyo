package inventoryManagement.domain;

import inventoryManagement.dao.UserDao;
import java.util.ArrayList;
import java.util.List;

public class FakeUserDao implements UserDao {
    List<User> users;
    
    public FakeUserDao() {
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
            
            if (this.users.get(i).getUsername().equals(username)) {
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
    public Boolean login(String username, String password) {
        for (int i = 0; i < this.users.size(); i++) {
            
            if (this.users.get(i).getUsername().equals(username) &&
                    this.users.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean checkUsername(String username) {
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    
}
