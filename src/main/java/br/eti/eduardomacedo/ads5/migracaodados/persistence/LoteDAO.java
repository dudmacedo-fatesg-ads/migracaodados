package br.eti.eduardomacedo.ads5.migracaodados.persistence;

import br.eti.eduardomacedo.ads5.migracaodados.model.Lote;
import br.eti.eduardomacedo.ads5.migracaodados.model.Registro;
import br.eti.eduardomacedo.ads5.migracaodados.util.DBFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoteDAO {

    private Connection cnx;

    public LoteDAO() {
        cnx = DBFactory.getConnection();
    }

    public void insert(Lote lote) {
        // Primeiro verifica se o lote já está cadastrado
        {
            String sql = "SELECT id, checksum FROM lote " +
                    "WHERE id_agt_arr = ? AND sequencial = ? AND checksum = ?";

            try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
                pstmt.setInt(1, lote.getAgenteArrecadador().getId());
                pstmt.setInt(2, lote.getSequencial());
                pstmt.setString(3, lote.getChecksum());

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Lote já cadastrado.");
                    return;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Depois insere o lote
        {
            String sql = "INSERT INTO lote (id_agt_arr, sequencial, checksum) " +
                    "VALUES (?, ?, ?) " +
                    "RETURNING id";

            try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
                pstmt.setInt(1, lote.getAgenteArrecadador().getId());
                pstmt.setInt(2, lote.getSequencial());
                pstmt.setString(3, lote.getChecksum());

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id");
                    lote.setId(id);

                    ArrayList<Registro> registros = new ArrayList<>();
                    registros.add(lote.getHeader());
                    registros.addAll(lote.getRegistros());
                    registros.add(lote.getTrailler());

                    RegistroDAO regdao = new RegistroDAO();
                    regdao.insert(registros);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
