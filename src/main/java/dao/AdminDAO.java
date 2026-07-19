package dao;

import java.sql.*;
import models.Admin;
import utils.DBConnection;

public class AdminDAO {

    public Admin login(String username, String password) {
        String sql = "SELECT * FROM admin WHERE login=? AND password=?";
        Admin admin = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                admin = new Admin(
                    rs.getInt("id_admin"),
                    rs.getString("login"),
                    rs.getString("password")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
}

