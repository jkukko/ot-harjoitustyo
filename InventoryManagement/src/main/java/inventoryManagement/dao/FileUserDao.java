/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryManagement.dao;

import inventoryManagement.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author kukkojoo
 */
public class FileUserDao implements UserDao {
    private List<User> users;
    private String file;
    
    public FileUserDao(String file) {
        this.users = new ArrayList<>();
        this.file = file;
        try {
            Scanner scanner = new Scanner(new File(this.file));
            while (scanner.hasNextLine()) {
                String [] parts = scanner.nextLine().split(",");
                User user = new User(parts[0], parts[1]);
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Create a new user aka save it
     * @param user 
     * @return user
     */

    @Override
    public User create(User user) {
        this.users.add(user);
        save();
        return user;
    }
    
    /**
     * Find user by specific user's name
     * @param username
     * @return user
     */

    @Override
    public User findByUsername(String username) {
        for (int i = 0; i < this.users.size(); i++) {
            
            if (this.users.get(i).getUsername().equals(username)) {
                return this.users.get(i);
            }
        }
        return null;
    }
    
    /**
     * Return all users
     * @return List of users
     */

    @Override
    public List<User> getAll() {
        return this.users;
    }
    
    /**
     * Login with username and password
     * @param username user's username
     * @param password user's password
     * @return Boolean
     */

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
    
    /**
     * Check if this username already exists
     * @param username user's username
     * @return Boolean
     */

    @Override
    public Boolean checkUsername(String username) {
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private void save() {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getUsername() + "," + user.getPassword() + "\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
}
