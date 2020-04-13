package inventoryManagement.dao;

import inventoryManagement.domain.Tuote;
import java.util.ArrayList;
import java.util.List;

public class ArrayListTuoteDao implements TuoteDao {
    
    private List<Tuote> tuotteet;
    
    public ArrayListTuoteDao() {
        this.tuotteet = new ArrayList<>();
    }
    
    @Override
    public Tuote create(Tuote tuote) {
        
        if (!this.tuotteet.contains(tuote)) {
            this.tuotteet.add(tuote);
        }
        
        
        return tuote;
    }

    @Override
    public Tuote findByName(String name) {
        for (int i = 0; i < this.tuotteet.size(); i++) {
            
            if (this.tuotteet.get(i).getNimi().equals(name)) {
                return this.tuotteet.get(i);
            }
            
        }
        return null;
    }

    @Override
    public List<Tuote> getAll() {
        return this.tuotteet;
    }

    @Override
    public Tuote changeSafetyLimit(String name, int amount) {
        Tuote t = this.findByName(name);
        t.setSafetyAmmount(amount);
        t.setDifference(t.getCurrentStock() - amount);
        return t;
    }

    @Override
    public Integer getSafetyStoct(String name) {
        Tuote t = this.findByName(name);
        return t.getSafetyAmmount();
    }

    @Override
    public Tuote changeCurrentStock(String name, int amount) {
        Tuote t = findByName(name);
        t.setCurrentStock(amount);
        t.setDifference(amount - t.getSafetyAmmount());
        return t;
    }
    
}
