package inventoryManagement.domain;

import inventoryManagement.dao.TilausDao;
import inventoryManagement.dao.TuoteDao;
import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class VarastoService {
    
    private TilausDao tilausDao;
    private TuoteDao tuoteDao;
    private HashMap<Tuote, Integer> kuluvaTilanne;
    
    public VarastoService(TilausDao tilausDao, TuoteDao tuoteDao) {
        this.tilausDao = tilausDao;
        this.tuoteDao = tuoteDao;
        this.kuluvaTilanne = new HashMap<>();
    }
    
    public Tilaus kirjaaTilaus(String name, int maara) {
        Date kirjausPvm = new Date();
        if (this.tuoteDao.findByName(name)==null) {
            this.tuoteDao.create(new Tuote(name));
        }
        
        Tuote tuote = this.tuoteDao.findByName(name);
        
        if (this.kuluvaTilanne.containsKey(tuote)) {
            int nykyinenMaara = this.kuluvaTilanne.get(tuote);
            this.kuluvaTilanne.put(tuote, nykyinenMaara + maara);
        } else {
            this.kuluvaTilanne.put(tuote, maara);
        }
        
        return tilausDao.create(new Tilaus(tuote, kirjausPvm, true, maara));
    }
    
    private Tilaus kirjaaTilausTietyllePaivalle(String name, int maara, Date date) {
        Date kirjausPvm = date;
        if (this.tuoteDao.findByName(name)==null) {
            this.tuoteDao.create(new Tuote(name));
        }
        
        Tuote tuote = this.tuoteDao.findByName(name);
        
        if (this.kuluvaTilanne.containsKey(tuote)) {
            int nykyinenMaara = this.kuluvaTilanne.get(tuote);
            this.kuluvaTilanne.put(tuote, nykyinenMaara + maara);
        } else {
            this.kuluvaTilanne.put(tuote, maara);
        }
        
        return tilausDao.create(new Tilaus(tuote, kirjausPvm, true, maara));
    }


    public int otaVarastosta(String name, int maara) {
        Date kirjausPvm = new Date();
        
        if (this.tuoteDao.findByName(name)==null) {
            return 0;
        }
        
        Tuote tuote = this.tuoteDao.findByName(name);
        int nykyinenMaara = this.kuluvaTilanne.get(tuote);
        if (nykyinenMaara<maara) {
            Tilaus ulosMenevaTilaus = new Tilaus(tuote, kirjausPvm, false, nykyinenMaara);
            tilausDao.create(ulosMenevaTilaus);
            this.kuluvaTilanne.put(tuote, 0);
            return nykyinenMaara;
        }
        Tilaus ulosMenevaTilaus = new Tilaus(tuote, kirjausPvm, false, maara);
        tilausDao.create(ulosMenevaTilaus);
        this.kuluvaTilanne.put(tuote, nykyinenMaara-maara);
        return maara;
           
    }
    
    public int otaVarastostaTietyllaPaivalla(String name, int maara, Date date) {
        Date kirjausPvm = date;
        
        if (this.tuoteDao.findByName(name)==null) {
            return 0;
        }
        
        Tuote tuote = this.tuoteDao.findByName(name);
        int nykyinenMaara = this.kuluvaTilanne.get(tuote);
        if (nykyinenMaara<maara) {
            Tilaus ulosMenevaTilaus = new Tilaus(tuote, kirjausPvm, false, nykyinenMaara);
            tilausDao.create(ulosMenevaTilaus);
            this.kuluvaTilanne.put(tuote, 0);
            return nykyinenMaara;
        }
        Tilaus ulosMenevaTilaus = new Tilaus(tuote, kirjausPvm, false, maara);
        tilausDao.create(ulosMenevaTilaus);
        this.kuluvaTilanne.put(tuote, nykyinenMaara-maara);
        return maara;
           
    }    
    
    public void tulostaKuvulaVarasto() {
        for (Map.Entry<Tuote, Integer> entry : kuluvaTilanne.entrySet()) {
            Tuote key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " " + value);
            
        }
    }
    
    public void tulostaTuotteenKirjanpito(String tuote) {
        List<Tilaus> tilaukset = this.tilausDao.findByTuoteName(tuote);
        for (int i = 0; i < tilaukset.size(); i++) {
            System.out.println(tilaukset.get(i));
        }
    }
    
    public void lataaHistoria() {
        
        try (Scanner tiedostonLukija = new Scanner(Paths.get("history.csv"))) {
            
            while (tiedostonLukija.hasNextLine()) {
                String rivi = tiedostonLukija.nextLine();
                String[] palat = rivi.split(",");
                Boolean b = Boolean.parseBoolean(palat[4]);
                int maara = Integer.parseInt(palat[5]);
                int y = Integer.parseInt(palat[1]) - 1900;
                int m = Integer.parseInt(palat[2]) - 1;
                int d = Integer.parseInt(palat[3]);
                Date date = new Date(y, m, d);
                if (b==true) {
                    kirjaaTilausTietyllePaivalle(palat[0], maara, date);
                } else {
                    otaVarastostaTietyllaPaivalla(palat[0], maara, date);
                }
                    
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    
    public int palautaTuotteidenLKM() {
        return this.tuoteDao.getAll().size();
    }
    
    
    
}
