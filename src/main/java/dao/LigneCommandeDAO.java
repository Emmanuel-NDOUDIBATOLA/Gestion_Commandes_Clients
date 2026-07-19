package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.LigneCommande;
import utils.DBConnection;

public class LigneCommandeDAO {

    public void insert(LigneCommande lc) {
        String sql = "INSERT INTO ligne_commande(id_commande, id_produit, quantite, prix_unitaire) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, lc.getIdCommande());
            ps.setInt(2, lc.getIdProduit());
            ps.setInt(3, lc.getQuantite());
            ps.setDouble(4, lc.getPrixUnitaire());
            ps.executeUpdate();
         // Mise à jour du montant total de la commande
            CommandeDAO commandeDAO = new CommandeDAO();
            commandeDAO.updateMontantTotal(lc.getIdCommande());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(LigneCommande lc) {
        String sql = "UPDATE ligne_commande SET id_commande=?, id_produit=?, quantite=?, prix_unitaire=? WHERE id_ligne=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, lc.getIdCommande());
            ps.setInt(2, lc.getIdProduit());
            ps.setInt(3, lc.getQuantite());
            ps.setDouble(4, lc.getPrixUnitaire());
            ps.setInt(5, lc.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM ligne_commande WHERE id_ligne=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LigneCommande getById(int id) {
        String sql = "SELECT * FROM ligne_commande WHERE id_ligne=?";
        LigneCommande lc = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                lc = new LigneCommande(
                    rs.getInt("id_ligne"),
                    rs.getInt("id_commande"),
                    rs.getInt("id_produit"),
                    rs.getInt("quantite"),
                    rs.getDouble("prix_unitaire")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lc;
    }

    public List<LigneCommande> getAll() {
        List<LigneCommande> list = new ArrayList<>();
        String sql = "SELECT * FROM ligne_commande";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new LigneCommande(
                    rs.getInt("id_ligne"),
                    rs.getInt("id_commande"),
                    rs.getInt("id_produit"),
                    rs.getInt("quantite"),
                    rs.getDouble("prix_unitaire")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<LigneCommande> getByCommande(int idCommande) {
        List<LigneCommande> list = new ArrayList<>();
        String sql = "SELECT * FROM ligne_commande WHERE id_commande=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCommande);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new LigneCommande(
                    rs.getInt("id_ligne"),
                    rs.getInt("id_commande"),
                    rs.getInt("id_produit"),
                    rs.getInt("quantite"),
                    rs.getDouble("prix_unitaire")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
