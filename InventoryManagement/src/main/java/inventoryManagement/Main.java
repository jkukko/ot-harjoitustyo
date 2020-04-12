package inventoryManagement;


import inventoryManagement.ui.GraphicUi;
import inventoryManagement.ui.TextUi;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        
        
        /*System.out.println("Valitse Käyttöliittymä");
        System.out.println("1 tekstikäyttöliittymä");
        System.out.println("Muuten graafinenkäyttöliittymä");
        String valinta = scanner.nextLine();
        if (valinta.equals("1")) {
            TextUi ui = new TextUi(scanner);
            ui.start();            
        } else {
            GraphicUi ui = new GraphicUi();
            ui.main(args);
        }*/
        GraphicUi ui = new GraphicUi();
        ui.main(args);

        
    }
    
}
