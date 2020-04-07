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
    
    @Override
    public void init() {
        this.varastoService = new VarastoService(new ArrayListTilausDao(), new ArrayListTuoteDao());
        this.userDao = new ArrayListUserDao();
        
        this.userDao.create(new User(0, "Test", "Test"));
    }
    

    public void login(Stage loginStage) throws Exception {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text sceneTitle = new Text("Welcome");
        grid.add(sceneTitle, 0, 0);
        
        // Username title
        Label username = new Label("User name");
        grid.add(username, 0, 1);

        // Username textfield
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);
        
        // Password title
        Label pw = new Label("Password");
        grid.add(pw, 0, 2);
        
        // Password textfield
        PasswordField pwTextField = new PasswordField();
        grid.add(pwTextField, 1, 2);
        
        // Login Button
        Button loginButton = new Button("login");
        grid.add(loginButton, 1, 3);
        
        // Create new user Button
        Button createButton = new Button("Create new user");
        grid.add(createButton, 1, 4);

        Label loginMessage = new Label();

        loginButton.setOnAction(e->{
            String usenameValue = userTextField.getText();
            String pwValue = pwTextField.getText();
            System.out.println(usenameValue + ", " + pwValue);
            if (this.userDao.userCheck(usenameValue, pwValue)==true) {
                System.out.println("Login succeed");
            } else {
                System.out.println("Login failed");
            }
        });
        
        Scene scene = new Scene(grid);
        loginStage.setScene(scene);
        loginStage.setTitle("Login window");
        loginStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        login(primaryStage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
