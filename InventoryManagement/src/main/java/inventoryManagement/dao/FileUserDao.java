package inventoryManagement.dao;

import inventoryManagement.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUserDao implements UserDao {
    private List<User> users;
    private String file;
    
    public FileUserDao(String file) throws Exception {
        this.users = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                User u = new User(parts[0], parts[1]);
                users.add(u);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }   
    }
    
    private void save() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getUsername() + ";" + user.getPassword() + "\n");
            }
        } 
    }    

    @Override
    public User create(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return users.stream()
            .filter(u->u.getUsername()
            .equals(username))
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

    @Override
    public Boolean login(String username, String pw) {
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getUsername().equals(username) &&
                    this.users.get(i).getPassword().equals(pw)) {
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
