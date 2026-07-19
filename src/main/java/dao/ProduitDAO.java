package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Produit;
import utils.DBConnection;

public class ProduitDAO {

    public void insert(Produit p) {
        String sql = "INSERT INTO produit(nom_produit, prix, quantite_stock) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNom());
            ps.setDouble(2, p.getPrix());
            ps.setInt(3, p.getStock());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Produit p) {
        String sql = "UPDATE produit SET nom_produit=?, prix=?, quantite_stock=? WHERE id_produit=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNom());
            ps.setDouble(2, p.getPrix());
            ps.setInt(3, p.getStock());
            ps.setInt(4, p.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM produit WHERE id_produit=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Produit getById(int id) {
        String sql = "SELECT * FROM produit WHERE id_produit=?";
        Produit p = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Produit(
                    rs.getInt("id_produit"),
                    rs.getString("nom_produit"),
                    rs.getDouble("prix"),
                    rs.getInt("quantite_stock")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<Produit> getAll() {
        List<Produit> list = new ArrayList<>();
        String sql = "SELECT * FROM produit";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Produit(
                    rs.getInt("id_produit"),
                    rs.getString("nom_produit"),
                    rs.getDouble("prix"),
                    rs.getInt("quantite_stock")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public int countProduits() {
        String sql = "SELECT COUNT(*) FROM produit";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public List<Produit> search(String keyword) {
        List<Produit> list = new ArrayList<>();
        String sql = "SELECT * FROM produit WHERE nom_produit LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Produit(
                    rs.getInt("id_produit"),
                    rs.getString("nom_produit"),
                    rs.getDouble("prix"),
                    rs.getInt("quantite_stock")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


}
