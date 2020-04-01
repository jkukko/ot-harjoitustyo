package inventoryManagement.dao;

import inventoryManagement.domain.Tilaus;
import java.util.List;

public interface TilausDao {
    
    Tilaus create(Tilaus tilaus);
    
    List<Tilaus> findByTuoteName(String name);
    
    List<Tilaus> getAll();
    
}
