package DAO;

import com.mohamed.model.Item;

import java.util.List;

public interface ItemDAOI {
    enum ItemSQL{
        GET_ALL_ITEMS("SELECT * FROM Item"),
        ADD_ITEM("INSERT INTO Item (id, itemName, price)" +
                "VALUES (?,?,?)"),
        REMOVE_ITEM("DELETE FROM Item WHERE id = ?");

        private String query;
        private ItemSQL(String query){
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
    }

    List<Item> getAllItems();
    Item addItem(Item item);
    Item removeItem(int id);
}
