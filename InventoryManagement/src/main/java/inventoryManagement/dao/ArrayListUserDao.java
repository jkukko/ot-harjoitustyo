package inventoryManagement.dao;

import inventoryManagement.domain.User;
import java.util.List;

public class ArrayListUserDao implements UserDao{
    
    private List<User> users;

    @Override
    public User create(User user) {
        this.users.add(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        for (int i = 0; i < this.users.size(); i++) {
            
            if (this.users.get(i).getUsername()==username) {
                return this.users.get(i);
            }
            
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }
    
}
