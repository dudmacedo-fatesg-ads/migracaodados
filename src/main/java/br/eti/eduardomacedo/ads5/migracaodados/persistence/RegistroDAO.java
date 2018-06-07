package br.eti.eduardomacedo.ads5.migracaodados.persistence;

import br.eti.eduardomacedo.ads5.migracaodados.model.Registro;
import br.eti.eduardomacedo.ads5.migracaodados.util.DBFactory;

import java.sql.*;
import java.util.List;

public class RegistroDAO {

    private Connection cnx;

    public RegistroDAO() {
        cnx = DBFactory.getConnection();
    }

    public void insert(List<Registro> registros) {
        String sql = "INSERT INTO registro " +
                "(id_lote, sequencia, tipo, registro, cd_remessa, cd_convenio, no_empresa, id_agt_arr, no_agt_arr, " +
                "dt_ger_arq, nsa, ident_conta_credito, dt_pagamento, dt_credito, cod_barras, vl_recebido, vl_tarifa, " +
                "nsr, tot_registros, vl_total, checksum) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {

            for (Registro r : registros) {
                pstmt.setInt(1, r.getLote().getId());
                pstmt.setInt(2, r.getSequencia());
                pstmt.setString(3, r.getTipo());
                pstmt.setString(4, r.getRegistro());
                if (r.getCodigoRemessa() == null) {
                    pstmt.setNull(5, Types.INTEGER);
                } else {
                    pstmt.setInt(5, r.getCodigoRemessa());
                }
                if (r.getCodigoConvenio() == null) {
                    pstmt.setNull(6, Types.VARCHAR);
                } else {
                    pstmt.setString(6, r.getCodigoConvenio());
                }
                if (r.getNomeEmpresa() == null) {
                    pstmt.setNull(7, Types.VARCHAR);
                } else {
                    pstmt.setString(7, r.getNomeEmpresa());
                }
                if (r.getCodigoAgenteArrecadador() == null) {
                    pstmt.setNull(8, Types.INTEGER);
                } else {
                    pstmt.setInt(8, r.getCodigoAgenteArrecadador());
                }
                if (r.getNomeAgenteArrecadador() == null) {
                    pstmt.setNull(9, Types.VARCHAR);
                } else {
                    pstmt.setString(9, r.getNomeAgenteArrecadador());
                }
                if (r.getDataGeracao() == null) {
                    pstmt.setNull(10, Types.DATE);
                } else {
                    pstmt.setDate(10, new java.sql.Date(r.getDataGeracao().getTime()));
                }
                if (r.getNsa() == null) {
                    pstmt.setNull(11, Types.INTEGER);
                } else {
                    pstmt.setInt(11, r.getNsa());
                }
                if (r.getIdentificacaoContaCredito() == null) {
                    pstmt.setNull(12, Types.VARCHAR);
                } else {
                    pstmt.setString(12, r.getIdentificacaoContaCredito());
                }
                if (r.getDataPagamento() == null) {
                    pstmt.setNull(13, Types.DATE);
                } else {
                    pstmt.setDate(13, new java.sql.Date(r.getDataPagamento().getTime()));
                }
                if (r.getDataCredito() == null) {
                    pstmt.setNull(14, Types.DATE);
                } else {
                    pstmt.setDate(14, new java.sql.Date(r.getDataCredito().getTime()));
                }
                if (r.getCodigoBarras() == null) {
                    pstmt.setNull(15, Types.VARCHAR);
                } else {
                    pstmt.setString(15, r.getCodigoBarras());
                }
                if (r.getValorRecebido() == null) {
                    pstmt.setNull(16, Types.DOUBLE);
                } else {
                    pstmt.setDouble(16, r.getValorRecebido());
                }
                if (r.getValorTarifa() == null) {
                    pstmt.setNull(17, Types.DOUBLE);
                } else {
                    pstmt.setDouble(17, r.getValorTarifa());
                }
                if (r.getNsr() == null) {
                    pstmt.setNull(18, Types.INTEGER);
                } else {
                    pstmt.setInt(18, r.getNsr());
                }
                if (r.getTotalRegistros() == null) {
                    pstmt.setNull(19, Types.INTEGER);
                } else {
                    pstmt.setInt(19, r.getTotalRegistros());
                }
                if (r.getValorTotal() == null) {
                    pstmt.setNull(20, Types.DOUBLE);
                } else {
                    pstmt.setDouble(20, r.getValorTotal());
                }
                pstmt.setString(21, r.getChecksum());

                pstmt.addBatch();
            }

            pstmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void insert(Registro registro) {
//        throw new UnsupportedOperationException("Ainda não implementado");
//    }

}
