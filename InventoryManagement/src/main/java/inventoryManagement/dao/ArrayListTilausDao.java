package inventoryManagement.dao;

import inventoryManagement.domain.Tilaus;
import java.util.ArrayList;
import java.util.List;

public class ArrayListTilausDao implements TilausDao {
    private List<Tilaus> tilaukset;
    
    public ArrayListTilausDao() {
        this.tilaukset = new ArrayList<>();
    }
    
    @Override
    public Tilaus create(Tilaus tilaus) {
        this.tilaukset.add(tilaus);
        return tilaus;
    }

    @Override
    public List<Tilaus> findByTuoteName(String name) {
        List<Tilaus> listaTuotteenTilauksista = new ArrayList<>();
        for (int i = 0; i < this.tilaukset.size(); i++) {
            
            if (this.tilaukset.get(i).getTuote().getNimi().equals(name)) {
                listaTuotteenTilauksista.add(this.tilaukset.get(i));
            }
        }
        return listaTuotteenTilauksista;
    }

    @Override
    public List<Tilaus> getAll() {
        return this.tilaukset;
    }
    
}
