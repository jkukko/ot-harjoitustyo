package inventoryManagement.ui;

import inventoryManagement.dao.ArrayListTilausDao;
import inventoryManagement.dao.ArrayListTuoteDao;
import inventoryManagement.domain.VarastoService;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TextUi {
    
    private Scanner scanner;
    private VarastoService varastoService;
    private Map<String, String> commands;
    
    
    public TextUi(Scanner scanner) {
        this.scanner = scanner; 
        this.varastoService = new VarastoService(new ArrayListTilausDao(), new ArrayListTuoteDao());
        this.commands = new TreeMap<>();
        
        commands.put("1", "1 Add Product");
        commands.put("2", "2 Add Order");
        commands.put("3", "3 Take from inventory");
        commands.put("4", "4 Print current Inventory");
        commands.put("5", "5 Print Orher history");
        commands.put("6", "6 Upload history data");
        commands.put("x", "x Exit");
    }
    
    public void start() {
        System.out.println("This is inventory management software");
        while (true) {
            printCommands();
            System.out.print("Command: ");
            String command = scanner.nextLine();
            
            if (command.equals("x")) {
                break;
            } else if (command.equals("2")) {
                addOrderToInventory();
            } else if (command.equals("3")){
                takeFromInventory();
            } else if (command.equals("4")) {
                this.varastoService.tulostaKuvulaVarasto();
            } else if (command.equals("5")) {
                printOrderHistorybyProduct();
            } else if (command.equals("6")) {
                this.varastoService.lataaHistoria();
            }
        }
    }

    private void printCommands() {
        for (Map.Entry<String, String> entry : commands.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(value);
        }
    }

    private void addOrderToInventory() {
        System.out.print("Tuote: ");
        String tuote = scanner.nextLine();
        System.out.print("Määrä: ");
        int luku =  Integer.parseInt(scanner.nextLine());
        this.varastoService.kirjaaTilaus(tuote, luku);
    }

    private void takeFromInventory() {
        System.out.print("Tuote: ");
        String tuote = scanner.nextLine();
        System.out.print("Määrä: ");
        int luku =  Integer.parseInt(scanner.nextLine());
        this.varastoService.otaVarastosta(tuote, luku);
    }

    private void printOrderHistorybyProduct() {
        System.out.print("Tuote: ");
        String tuote = scanner.nextLine();
        this.varastoService.tulostaTuotteenKirjanpito(tuote);
    }
    
    
}
