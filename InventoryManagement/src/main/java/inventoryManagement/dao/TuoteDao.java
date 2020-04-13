package inventoryManagement.dao;

import inventoryManagement.domain.Tuote;
import java.util.List;

public interface TuoteDao {
    
    Tuote create(Tuote tuote);
    
    Tuote findByName(String name);
    
    List<Tuote> getAll();
    
    Tuote changeSafetyLimit(String name, int amount);
    
}
