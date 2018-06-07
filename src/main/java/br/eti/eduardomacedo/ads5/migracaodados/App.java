package br.eti.eduardomacedo.ads5.migracaodados;

import br.eti.eduardomacedo.ads5.migracaodados.util.DBFactory;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

//        args = new String[] {"-i", "exemplo"};

        criaTabelas();

        Argumentos args_exec = new Argumentos();
        CmdLineParser parser = new CmdLineParser(args_exec);

        try {
            parser.parseArgument(args);
            args_exec.run();
        } catch (CmdLineException e) {
            parser.printUsage(System.out);
        }
    }

    private static void criaTabelas() {
        Connection cnx = DBFactory.getConnection();

        try (Statement stmt = cnx.createStatement()) {
            ArrayList<String> tabelas = new ArrayList<>();

            ResultSet rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'");

            while (rs.next()) {
                String t = rs.getString("table_name");

                tabelas.add(t);
            }

            if (!tabelas.contains("agente_arrecadador")) {
                String ddl = "CREATE TABLE agente_arrecadador (" +
                        "id SMALLINT NOT NULL," +
                        "nome VARCHAR(50)," +
                        "PRIMARY KEY (id)" +
                        ")";
                stmt.execute(ddl);

                String dml = "INSERT INTO agente_arrecadador (id, nome) " +
                        "VALUES " +
                        "(1, 'Banco do Brasil')," +
                        "(33, 'Banco Santander')," +
                        "(70, 'Banco Regional de Brasília')," +
                        "(104, 'Caixa Econômica Federal')," +
                        "(237, 'Banco Bradesco')," +
                        "(341, 'Itaú Unibanco')," +
                        "(389, 'Banco Mercantil do Brasil')," +
                        "(399, 'HSBC Bank Brasil')," +
                        "(422, 'Banco Safra')," +
                        "(634, 'Banco Triângulo')," +
                        "(748, 'Banco Cooperativo Sicredi')," +
                        "(756, 'Bancoob')";
                stmt.execute(dml);
            }
            if (!tabelas.contains("lote")) {
                String ddl = "CREATE TABLE lote (" +
                        "id SERIAL NOT NULL," +
                        "id_agt_arr SMALLINT NOT NULL," +
                        "sequencial INTEGER NOT NULL," +
                        "checksum VARCHAR(32) NOT NULL," +
                        "PRIMARY KEY (id)," +
                        "FOREIGN KEY (id_agt_arr) REFERENCES agente_arrecadador (id)" +
                        ")";
                stmt.execute(ddl);
            }
            if (!tabelas.contains("registro")) {
                String ddl = "CREATE TABLE registro (" +
                        "id_lote INTEGER NOT NULL," +
                        "sequencia INTEGER NOT NULL," +
                        "tipo CHAR(1) NOT NULL," +
                        "registro VARCHAR(160) NOT NULL," +
                        "cd_remessa SMALLINT," +
                        "cd_convenio VARCHAR(20)," +
                        "no_empresa VARCHAR(20)," +
                        "id_agt_arr SMALLINT," +
                        "no_agt_arr VARCHAR(20)," +
                        "dt_ger_arq DATE," +
                        "nsa INTEGER," +
                        "ident_conta_credito VARCHAR(20)," +
                        "dt_pagamento DATE," +
                        "dt_credito DATE," +
                        "cod_barras VARCHAR(44)," +
                        "vl_recebido DOUBLE PRECISION," +
                        "vl_tarifa DOUBLE PRECISION," +
                        "nsr INTEGER," +
                        "tot_registros INTEGER," +
                        "vl_total DOUBLE PRECISION," +
                        "checksum VARCHAR(32) NOT NULL," +
                        "PRIMARY KEY (id_lote, sequencia)," +
                        "FOREIGN KEY (id_lote) REFERENCES lote(id)" +
                        ")";
                stmt.execute(ddl);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
