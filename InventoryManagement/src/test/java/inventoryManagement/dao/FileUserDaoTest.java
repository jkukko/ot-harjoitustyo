/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryManagement.dao;

import inventoryManagement.domain.User;
import java.io.File;
import java.io.FileWriter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author kukkojoo
 */
public class FileUserDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File userFile;  
    UserDao dao;

    @Before
    public void setUp() throws Exception {
        this.userFile = this.testFolder.newFile("testfile_users.csv");  
        
        try (FileWriter file = new FileWriter(this.userFile.getAbsolutePath())) {
            file.write("test,test\n");
        }
        
        this.dao = new FileUserDao(this.userFile.getAbsolutePath());        
    }
    
    @Test
    public void userIsInFile() {
        User user = this.dao.findByUsername("test");
        assertEquals("test", user.getUsername());
        assertEquals("test", user.getPassword());
        
    }
    
    @Test
    public void findByUserName() {
        User user = new User("test1", "test1");
        this.dao.create(user);
        assertEquals(user.getUsername(), this.dao.findByUsername("test1").getUsername());
    }
    
    @Test
    public void createNewUser() {
        User user = new User("test1", "test1");
        this.dao.create(user);
        assertEquals(2, this.dao.getAll().size());
    }
    
    
    @Test
    public void loginWithCorrectInformation() {
        assertEquals(true, this.dao.login("test", "test"));
    }
    
    @Test
    public void cannotLoginwithWrongInformation() {
        assertEquals(false, this.dao.login("Mormo", "Moro"));
    }
    
    @Test
    public void checkThatUsernameIsAlready() {
        assertEquals(true, this.dao.checkUsername("test"));
    }
    
    @Test
    public void checkThereIsNotThatUserName() {
        assertEquals(false, this.dao.checkUsername("moro"));
    }
}
