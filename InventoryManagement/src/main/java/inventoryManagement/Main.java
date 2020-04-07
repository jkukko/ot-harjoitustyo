package inventoryManagement;


import inventoryManagement.ui.GraphicUi;
import inventoryManagement.ui.TextUi;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);
        TextUi ui = new TextUi(scanner);
        ui.start();*/
        GraphicUi ui = new GraphicUi();
        ui.main(args);
    }
    
}
