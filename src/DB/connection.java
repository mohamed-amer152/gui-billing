package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class connection {

    public String driver = "com.mysql.cj.jdbc.Driver";
    public String url = "jdbc:mysql://localhost:3306/billing";
    private String user = "root";
    private String password = "root";
    public PreparedStatement ps;
    public Connection conn;
    public ResultSet rs;

    public Connection getconection() {

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    public int login(String name, String password) {
        int result = 2;
        try {
            getconection();
            ps = conn.prepareStatement("select * from billing.employee where user=? and password=?");
            ps.setString(1, name);
            ps.setString(2, password);
            rs = ps.executeQuery();

            while (rs.next()) {
                result = 1;
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println(result);
        return result;
    }

    public int insert_into_client(String name, String email, String phone, String address, String gender) {
        int result = 0;

        try {

            getconection();
            ps = conn.prepareStatement("insert into billing.client (name, email, phone, address, gender) values (?,?,?,?,?) ;");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, gender);

            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public ResultSet search(String phone, int flage) {
// will use same method to search by id or all
        try {
            getconection();
            if (flage == 0) {
                ps = conn.prepareStatement("select * from billing.client where phone=?");
                ps.setString(1, phone);
                rs = ps.executeQuery();

            } else {
                ps = conn.prepareStatement("select * from billing.client ");
                rs = ps.executeQuery();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

// update the client 
    public void update_client(String contact, String name, String email, String phone, String address, String gender) {
        try {
            getconection();
            ps = conn.prepareStatement("update billing.client set name=? ,email=?,phone=?,address=?,gender=? where phone =?");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, gender);
            ps.setString(6, contact);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // delete client
    public void delete_client(String contact) {
        try {
            getconection();
            ps = conn.prepareStatement("delete from billing.client where phone = ?");
            ps.setString(1, contact);
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

// insert into product 
    public void insert_product(String name, String serial, String expiry, String quantity, String available) {

        try {
            getconection();
            ps = conn.prepareStatement("insert into billing.product (name, serial, expiry, quantity, available) values (?,?,?,?,?) ;");
            ps.setString(1, name);
            ps.setString(2, serial);
            ps.setString(3, expiry);
            ps.setString(4, quantity);
            ps.setString(5, available);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

// search into product 
    public ResultSet search_product(String serial, int flag) {

        try {
            getconection();
            if (flag == 0) {
                ps = conn.prepareStatement("select * from billing.product where serial=?");
                ps.setString(1, serial);
                rs = ps.executeQuery();

            } else {
                ps = conn.prepareStatement("select * from billing.product ");
                rs = ps.executeQuery();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;

    }

// update product 
    public void update_product(String ser, String name, String serial, String expiry, String quantity, String available) {
        try {
            getconection();
            ps = conn.prepareStatement("update billing.product set name=? ,serial=?,expiry=?,quantity=?,available=? where serial =?");
            ps.setString(1, name);
            ps.setString(2, serial);
            ps.setString(3, expiry);
            ps.setString(4, quantity);
            ps.setString(5, available);
            ps.setString(6, ser);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// delete product 
    public void delete_product(String serial) {
        try {
            getconection();
            ps = conn.prepareStatement("delete from billing.product where serial = ?");
            ps.setString(1, serial);
            ps.executeUpdate();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    // insert into report 
    public void insert_report(String client,String product,String quantity,int total,int paid,int remain)
    {
        try {
            getconection();
            ps = conn.prepareStatement("insert into billing.report (client, product, quantity, total, paid ,remain) values (?,?,?,?,?,?) ;");
            ps.setString(1, client);
            ps.setString(2, product);
            ps.setString(3, quantity);
            ps.setInt(4, total);
            ps.setInt(5, paid);
            ps.setInt(6, remain);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

}
