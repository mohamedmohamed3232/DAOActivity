package com.mohamed.services;

import DAO.AbstractDAO;
import DAO.CustomerDAOI;
import com.mohamed.model.Customer;

import java.sql.SQLException;

public class CustomerDAO extends AbstractDAO implements CustomerDAOI {
    private Customer cust;

    /**
     * This is a constructor that creates a new customer object
     * This is called Constructor Injector
     */
    public CustomerDAO() {
        this.cust = new Customer();
    }

    /**
     * This is setting up a method to get a customer from the customer table using the Id as a parameter
     * @param id
     * @return
     */
    @Override
    public Customer getCustomer(int id) {
      try {
          this.connectToMySQL();
          ps = conn.prepareStatement(CustSQL.GET_Customer_BY_ID.getQuery());
          ps.setInt(1,id);
          rs = ps.executeQuery();
          while(rs.next()) {
              cust.setId(rs.getInt(1));
              cust.setEmail(rs.getString(2));
              cust.setfName(rs.getString(3));
              cust.setlName(rs.getString(4));
              System.out.println("Customer Id: " + cust.getId() + "Customer email: " + cust.getEmail() +
                      "Customer First Name: " + cust.getfName() + "Customer Last Name: " + cust.getlName());
          }

      } catch (SQLException e) {
          e.printStackTrace();
      }finally {
      closeConnections();
      return cust;
      }

    }

    /**
     * This is a method that adds a customer to the database and then returns the Customer
     * @param customer
     * @return
     */
    @Override
    public Customer addCustomer(Customer customer) {
        try {
            this.connectToMySQL();
            ps = conn.prepareStatement(CustSQL.ADD_Customer.getQuery());
            ps.setInt(1, cust.getId());
            ps.setString(2,cust.getEmail());
            ps.setString(3,cust.getfName());
            ps.setString(4,cust.getlName());
            ps.executeUpdate();
            System.out.println(cust + "is added");


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections();
            return customer;
        }
    }

    @Override
    public Customer deleteCustomer(int id) {
        try {
            this.connectToMySQL();
            ps = conn.prepareStatement(CustSQL.REMOVE_Customer.getQuery());
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while(rs.next()) {
                cust.setId(rs.getInt(1));
                cust.setEmail(rs.getString(2));
                cust.setfName(rs.getString(3));
                cust.setlName(rs.getString(4));
                System.out.println("This customer has been deleted " + cust.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnections();
            return null;
        }
    }
}
