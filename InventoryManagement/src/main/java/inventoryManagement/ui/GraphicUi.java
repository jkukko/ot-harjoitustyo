package inventoryManagement.ui;

import inventoryManagement.dao.ArrayListTilausDao;
import inventoryManagement.dao.ArrayListTuoteDao;
import inventoryManagement.dao.ArrayListUserDao;
import inventoryManagement.domain.User;
import inventoryManagement.domain.VarastoService;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
        this.varastoService.lataaHistoria();
    }
    
    private boolean isInt(TextField input) {
        try {
            int amount = Integer.parseInt(input.getText());
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // login scene
        
        GridPane loginLayout = new GridPane();
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setHgap(10);
        loginLayout.setVgap(10);
        loginLayout.setPadding(new Insets(25, 25, 25, 25));
        Text loginTitle = new Text("Login");
        loginLayout.add(loginTitle, 0, 0);
        
        // Username 
        Label usernameLabel = new Label("Username");
        TextField usernameInput = new TextField();
        loginLayout.add(usernameLabel, 0, 1);
        loginLayout.add(usernameInput, 1, 1);
        
        // Password
        Label passwordLabel = new Label("Password");
        PasswordField passwordInput = new PasswordField();
        loginLayout.add(passwordLabel, 0, 2);
        loginLayout.add(passwordInput, 1, 2);
        
        // Buttons
        Button loginButton = new Button("login");
        Button createButton = new Button("Create new user");
        loginLayout.add(loginButton, 1, 3);
        loginLayout.add(createButton, 1, 4);
        
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
        
        this.loginScene = new Scene(loginLayout, 300, 250);
        
        // Create new user scene
        GridPane createUserLayout = new GridPane();
        createUserLayout.setAlignment(Pos.CENTER);
        createUserLayout.setHgap(10);
        createUserLayout.setVgap(10);
        createUserLayout.setPadding(new Insets(25, 25, 25, 25));
        Text newUserTitle = new Text("Create new user");
        createUserLayout.add(newUserTitle, 0, 0);
        
        // Username
        Label newUserUsernameLabel = new Label("Username");
        TextField newUserUsernameInput = new TextField();
        createUserLayout.add(newUserUsernameLabel, 0, 1);
        createUserLayout.add(newUserUsernameInput, 1, 1);
        
        
        // Password
        Label newUserPasswordLabel = new Label("Password");
        PasswordField newUserPasswordInput = new PasswordField();
        createUserLayout.add(newUserPasswordLabel, 0, 2);
        createUserLayout.add(newUserPasswordInput, 1, 2);
        
        // Buttons
        Button saveNewUser = new Button("Save");
        createUserLayout.add(saveNewUser, 1, 3);

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
        
        this.newUserScene = new Scene(createUserLayout, 300, 250);
        
        // Create menu
        MenuBar menuBar = new MenuBar();
        Menu menuOptions = new Menu("_Options");
        MenuItem incomingOrderMenuItem = new MenuItem("Incoming Order");
        MenuItem allertMonitorMenuItem = new MenuItem("Allert monitor");
        MenuItem takeFromInventoryMenuItem = new MenuItem("Take from stock");
        MenuItem historyMenuItem = new MenuItem("History View");
        menuOptions.getItems().addAll(
                takeFromInventoryMenuItem, 
                incomingOrderMenuItem, 
                allertMonitorMenuItem,
                historyMenuItem);
        menuBar.getMenus().addAll(menuOptions);
             
        // Create layout
        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        ComboBox productList = new ComboBox(
            FXCollections.observableArrayList(this.varastoService.listaTuoteNimista()));
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label productLabel = new Label("Select Product");
        Label amountLabel = new Label("Amount");
                
        /// Incoming order elements
        TextField inOrderAmount = new TextField();
                
        Button inOrderButton = new Button("Save");
        
        inOrderButton.setOnAction(e -> {
            if (isInt(inOrderAmount) == true) {
                String selectedProduct = productList.getValue().toString();
                int amount = Integer.parseInt(inOrderAmount.getText());
                this.varastoService.kirjaaTilaus(selectedProduct, amount);
                productList.setItems(
                        FXCollections.observableArrayList(this.varastoService.listaTuoteNimista()));
                inOrderAmount.clear();
            } else {
                inOrderAmount.setStyle("-fx-text-fill: red;");                
            }
        });
        
        
        // Take From inventory elements
        TextField outOrderAmount = new TextField();
        
        Button outOrderButton = new Button("Save");

        outOrderButton.setOnAction(e -> {
            if (isInt(outOrderAmount) == true) {
                String selectedProduct = productList.getValue().toString();
                int amount = Integer.parseInt(outOrderAmount.getText());
                this.varastoService.otaVarastosta(selectedProduct, amount);
                productList.setItems(
                        FXCollections.observableArrayList(this.varastoService.listaTuoteNimista()));
                inOrderAmount.clear();
            } else {
                inOrderAmount.setStyle("-fx-text-fill: red;");
                
            }
        });
        
        // Kuluva Tilanne Varasto
        TableView table = new TableView();
        TableColumn productColumn = new TableColumn("Product");
        productColumn.setMinWidth(100);
        productColumn.setCellValueFactory(new PropertyValueFactory<>("nimi"));
        
        TableColumn amountColumn = new TableColumn("Current Stock");
        amountColumn.setMinWidth(100);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("currentStock"));    
                    
        table.getColumns().addAll(productColumn, amountColumn);
        
             
        // Handling menu events
        incomingOrderMenuItem.setOnAction(e -> {
            grid.getChildren().clear();
            grid.add(productLabel, 0, 0);
            grid.add(amountLabel, 1, 0);            
            grid.add(productList, 0, 1);
            productList.setEditable(true);            
            grid.add(inOrderAmount, 1, 1);
            grid.add(inOrderButton, 0, 2);
            layout.setCenter(grid);
        });
        
        takeFromInventoryMenuItem.setOnAction(e -> {
            grid.getChildren().clear();
            grid.add(productLabel, 0, 0);
            grid.add(amountLabel, 1, 0);            
            grid.add(productList, 0, 1);
            productList.setEditable(false);
            grid.add(outOrderAmount, 1, 1);
            grid.add(outOrderButton, 0, 2);
            layout.setCenter(grid);
        });
        
        allertMonitorMenuItem.setOnAction(e -> {
            table.getItems().clear();
            table.setItems(FXCollections.observableArrayList(this.varastoService.palautaTuotteet()));
            layout.setCenter(table);
        });
        this.mainScene = new Scene(layout, 300, 250);

        
        // setup primary stage
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(this.loginScene);
        primaryStage.show();
        
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
   
    
}
