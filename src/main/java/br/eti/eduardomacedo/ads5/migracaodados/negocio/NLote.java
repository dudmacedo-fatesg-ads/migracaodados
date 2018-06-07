package br.eti.eduardomacedo.ads5.migracaodados.negocio;

import br.eti.eduardomacedo.ads5.migracaodados.model.AgenteArrecadador;
import br.eti.eduardomacedo.ads5.migracaodados.model.Lote;
import br.eti.eduardomacedo.ads5.migracaodados.model.Registro;
import br.eti.eduardomacedo.ads5.migracaodados.util.Checksum;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;

public class NLote {

    public static Lote getLote(File arquivo) {
        Lote lote = null;

        try (FileReader fr = new FileReader(arquivo)) {
            try (BufferedReader br = new BufferedReader(fr)) {

                int sequencia = 1;
                while (br.ready()) {
                    String registro = br.readLine();

                    if (NRegistro.isHeader(registro)) {
                        if (lote != null) {
                            return null;
                        } else {
                            lote = new Lote();

                            Registro header = NRegistro.create(registro);
                            header.setLote(lote);
                            header.setSequencia(sequencia++);
                            lote.setHeader(header);
                        }
                    } else if (lote != null) {
                        if (NRegistro.isTrailler(registro)) {
                            Registro trailler = NRegistro.create(registro);
                            trailler.setLote(lote);
                            trailler.setSequencia(sequencia++);

                            lote.setTrailler(trailler);

                            completaLote(lote);

                            return lote;
                        } else if (registro.length() >= 150) {
                            if (lote.getRegistros() == null) {
                                ArrayList<Registro> registros = new ArrayList<>();
                                lote.setRegistros(registros);
                            }

                            Registro r = NRegistro.create(registro);
                            r.setLote(lote);
                            r.setSequencia(sequencia++);
                            lote.getRegistros().add(r);
                        } else {
                            return null;
                        }
                    }
                }

            }
        } catch (
                IOException e)

        {
            e.printStackTrace();
        }

        return lote;
    }

    private static void completaLote(Lote lote) {
        AgenteArrecadador agt = new AgenteArrecadador();
        agt.setId(lote.getHeader().getCodigoAgenteArrecadador());
        lote.setAgenteArrecadador(agt);
        lote.setSequencial(lote.getHeader().getNsa());
        lote.setChecksum(Checksum.md5(lote));

    }

}
