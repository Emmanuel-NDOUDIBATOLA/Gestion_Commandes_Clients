package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Commande;
import utils.DBConnection;

public class CommandeDAO {

    public void insert(Commande c) {
        String sql = "INSERT INTO commande(id_client, date_commande, montant_total) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getIdClient());
            ps.setDate(2, new java.sql.Date(c.getDateCommande().getTime()));
            ps.setDouble(3, c.getTotal());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Commande c) {
        String sql = "UPDATE commande SET id_client=?, date_commande=?, montant_total=? WHERE id_commande=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getIdClient());
            ps.setDate(2, new java.sql.Date(c.getDateCommande().getTime()));
            ps.setDouble(3, c.getTotal());
            ps.setInt(4, c.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

        // Supprimer les lignes de commande associées
        String sqlLignes = "DELETE FROM ligne_commande WHERE id_commande=?";

        // Supprimer la commande
        String sqlCommande = "DELETE FROM commande WHERE id_commande=?";

        try (Connection conn = DBConnection.getConnection()) {

            // Supprimer les lignes
            PreparedStatement ps1 = conn.prepareStatement(sqlLignes);
            ps1.setInt(1, id);
            ps1.executeUpdate();

            // Supprimer la commande
            PreparedStatement ps2 = conn.prepareStatement(sqlCommande);
            ps2.setInt(1, id);
            ps2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Commande getById(int id) {
        String sql = "SELECT * FROM commande WHERE id_commande=?";
        Commande c = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Commande(
                    rs.getInt("id_commande"),
                    rs.getInt("id_client"),
                    rs.getDate("date_commande"),
                    rs.getDouble("montant_total")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public List<Commande> getAll() {
        List<Commande> list = new ArrayList<>();
        String sql = "SELECT * FROM commande";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Commande(
                    rs.getInt("id_commande"),
                    rs.getInt("id_client"),
                    rs.getDate("date_commande"),
                    rs.getDouble("montant_total")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public int countCommandes() {
        String sql = "SELECT COUNT(*) FROM commande";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int insertAndReturnId(Commande c) {
        String sql = "INSERT INTO commande(id_client, date_commande, montant_total) VALUES (?, ?, ?)";
        int id = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, c.getIdClient());
            ps.setDate(2, new java.sql.Date(c.getDateCommande().getTime()));
            ps.setDouble(3, 0); // montant initial
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public double totalVentes() {
        String sql = "SELECT SUM(montant_total) FROM commande";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getDouble(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void updateMontantTotal(int idCommande) {
        String sql = "UPDATE commande SET montant_total = (SELECT SUM(quantite * prix_unitaire) FROM ligne_commande WHERE id_commande = ?) WHERE id_commande = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCommande);
            ps.setInt(2, idCommande);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
