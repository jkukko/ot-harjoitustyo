package inventoryManagement.domain;

import inventoryManagement.dao.TilausDao;
import inventoryManagement.dao.TuoteDao;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    
    
    
}
