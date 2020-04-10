package inventoryManagement.ui;

import inventoryManagement.dao.ArrayListTilausDao;
import inventoryManagement.dao.ArrayListTuoteDao;
import inventoryManagement.dao.ArrayListUserDao;
import inventoryManagement.domain.User;
import inventoryManagement.domain.VarastoService;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GraphicUi extends Application {
    
    private VarastoService varastoService;
    private ArrayListUserDao userDao;
    
    private Scene newUserScene;
    private Scene loginScene;
    private Scene mainScene;
  
    @Override
    public void init() {
        this.varastoService = new VarastoService(new ArrayListTilausDao(), new ArrayListTuoteDao());
        this.userDao = new ArrayListUserDao();
        
        this.userDao.create(new User("Test", "Test"));
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // login scene
        
        GridPane loginGrid = new GridPane();
        loginGrid.setAlignment(Pos.CENTER);
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);
        loginGrid.setPadding(new Insets(25, 25, 25, 25));
        Text loginTitle = new Text("Login");
        loginGrid.add(loginTitle, 0, 0);
        
        // Username 
        Label usernameLabel = new Label("Username");
        TextField usernameInput = new TextField();
        loginGrid.add(usernameLabel, 0, 1);
        loginGrid.add(usernameInput, 1, 1);
        
        // Password
        Label passwordLabel = new Label("Password");
        PasswordField passwordInput = new PasswordField();
        loginGrid.add(passwordLabel, 0, 2);
        loginGrid.add(passwordInput, 1, 2);
        
        // Buttons
        Button loginButton = new Button("login");
        Button createButton = new Button("Create new user");
        loginGrid.add(loginButton, 1, 3);
        loginGrid.add(createButton, 1, 4);
        
        loginButton.setOnAction(e-> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            if (this.userDao.login(username, password) == true) {
                System.out.println("Login succeed");
                primaryStage.setScene(this.mainScene);
            } else {
                System.out.println("Login failed");
            }
        });
        
        createButton.setOnAction(e-> {
            primaryStage.setScene(this.newUserScene);
        });
        
        this.loginScene = new Scene(loginGrid, 300, 250);
        
        // Create new user scene
        GridPane newUserGrid = new GridPane();
        newUserGrid.setAlignment(Pos.CENTER);
        newUserGrid.setHgap(10);
        newUserGrid.setHgap(10);
        newUserGrid.setPadding(new Insets(25, 25, 25, 25));
        Text newUserTitle = new Text("Create new user");
        newUserGrid.add(newUserTitle, 0, 0);
        
        // Username
        Label newUserUsernameLabel = new Label("Username");
        TextField newUserUsernameInput = new TextField();
        newUserGrid.add(newUserUsernameLabel, 0, 1);
        newUserGrid.add(newUserUsernameInput, 1, 1);
        
        
        // Password
        Label newUserPasswordLabel = new Label("Password");
        PasswordField newUserPasswordInput = new PasswordField();
        newUserGrid.add(newUserPasswordLabel, 0, 2);
        newUserGrid.add(newUserPasswordInput, 1, 2);
        
        // Buttons
        Button saveNewUser = new Button("Save");
        newUserGrid.add(saveNewUser, 1, 3);
        
        saveNewUser.setOnAction(e-> {
            String username = newUserUsernameInput.getText();
            String password = newUserPasswordInput.getText();
            if (this.userDao.checkUsername(username) == false) {
                System.out.println("New user created");
                this.userDao.create(new User(username, password));
                primaryStage.setScene(this.loginScene);
            } else {
                System.out.println("This username is already in use");
            }
        });
        
        this.newUserScene = new Scene(newUserGrid, 300, 250);
        
        
        // Main scene
        MenuBar menuBar = new MenuBar();
        
        // setup primary stage
        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(this.loginScene);
        primaryStage.show();
        
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
