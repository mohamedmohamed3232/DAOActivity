package DAO;

import com.mohamed.model.Customer;

public interface CustomerDAOI {
    enum CustSQL {
        GET_Customer_BY_ID("Select * FROM Customer"),
        ADD_Customer("INSERT INTO Customer (Id, email, fName, lName) " + "Values ?,?,?,?"),
        REMOVE_Customer("DELETE FROM Customer WHERE id = ?");

        private final String query;

        private CustSQL(String query_string) {
            this.query = query_string;
        }
        public String getQuery() {
            return query;
        }

    }
    Customer getCustomer(int id);
    Customer addCustomer(Customer customer);
    Customer deleteCustomer(int id);

}
