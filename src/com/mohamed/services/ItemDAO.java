package com.mohamed.services;

import DAO.AbstractDAO;
import DAO.ItemDAOI;
import com.mohamed.model.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO extends AbstractDAO implements ItemDAOI {
    private Item item;
    private List<Item> itemList;

    /**
     * This is setting up a empty constructor that sets up the Item object and the Item List
     * This is an example of a constructor injection
     */
    public ItemDAO() {
        this.item = item;
        this.itemList = new ArrayList<>();
    }

    @Override
    public List<Item> getAllItems() {
        try {
            connectToMySQL();
            ps = conn.prepareStatement(ItemSQL.GET_ALL_ITEMS.getQuery());
            rs = ps.executeQuery();
            itemList.clear();
            while(rs.next()) {
                item = new Item();
                item.setId(rs.getInt(1));
                item.setItemName(rs.getString(2));
                item.setPrice(rs.getDouble(3));
                itemList.add(item);

            }
            System.out.println("Thses are the items that are in the list: " + rs.getRow());
            closeConnections();
            return itemList;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Item addItem(Item item) {
      try {
          connectToMySQL();
          ps = conn.prepareStatement(ItemSQL.ADD_ITEM.getQuery());
          ps.setInt(1, item.getId());
          ps.setString(2, item.getItemName());
          ps.setDouble(3,item.getPrice());
          ps.executeUpdate();
          System.out.println("This " + item + " has been added");
      } catch(SQLException e) {
          e.printStackTrace();
          System.out.println("Could not add Item");
    
      } finally {
          this.closeConnections();
          return null;
      }
    }

    @Override
    public Item removeItem(int id) {
       try {
           connectToMySQL();
           ps = conn.prepareStatement(ItemSQL.REMOVE_ITEM.getQuery());
           ps.setInt(1,id);
           rs = ps.executeQuery();
           while (rs.next()) {
               item.setId(rs.getInt(1));
               item.setItemName(rs.getString(2));
               item.setPrice(rs.getDouble(3));
               System.out.println("This item: " + item.toString() + " has been removed");

           }
           return item;
       }catch(SQLException e) {
           e.printStackTrace();
       }finally {
           closeConnections();

       }
        return null;
    }
}
