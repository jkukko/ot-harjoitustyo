package inventoryManagement;

import inventoryManagement.domain.Tilaus;
import inventoryManagement.domain.Tuote;
import inventoryManagement.domain.Varasto;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        Varasto varasto = new Varasto();
        Tuote tuote = new Tuote(1, "Maito");
        Tilaus sisaanTulevaTilaus1 = new Tilaus(tuote, new Date(2020, 3, 26), true, 10);
        Tilaus sisaanTulevaTilaus2 = new Tilaus(tuote, new Date(2020, 3, 26), true, 10);
        Tilaus ulosTulevaTilaus1 = new Tilaus(tuote, new Date(2020, 3, 26), false, 5);
        Tilaus ulosTulevaTilaus2 = new Tilaus(tuote, new Date(2020, 3, 26), false, 3);
        varasto.kirjaaTilaus(sisaanTulevaTilaus1);
        varasto.kirjaaTilaus(sisaanTulevaTilaus2);
        varasto.kirjaaTilaus(ulosTulevaTilaus1);
        varasto.kirjaaTilaus(ulosTulevaTilaus2);
        int tuoteMaara = varasto.getTuotteenKuluvaMaara(tuote);
        System.out.println(tuoteMaara);
        varasto.tulostaNykyinenVarasto();
    }
    
}
