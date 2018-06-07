package br.eti.eduardomacedo.ads5.migracaodados.negocio;

import br.eti.eduardomacedo.ads5.migracaodados.model.Registro;
import br.eti.eduardomacedo.ads5.migracaodados.util.Checksum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NRegistro {

    public static Registro create(String registro) {
        if (registro != null && registro.length() >= 150) {
            Registro r = new Registro();

            r.setTipo(registro.substring(0, 1));
            r.setRegistro(registro);
            r.setChecksum(Checksum.md5(registro));

            if (isHeader(r.getRegistro())) {
                // Preenche atributos de header
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                try {
                    r.setCodigoRemessa(Integer.parseInt(registro.substring(1, 2)));
                } catch (NumberFormatException e) {
                    r.setCodigoRemessa(null);
                }
                r.setCodigoConvenio(registro.substring(2, 22));
                r.setNomeEmpresa(registro.substring(22, 42));
                try {
                    r.setCodigoAgenteArrecadador(Integer.parseInt(registro.substring(42, 45)));
                } catch (NumberFormatException e) {
                    r.setCodigoAgenteArrecadador(null);
                }
                r.setNomeAgenteArrecadador(registro.substring(45, 65));
                try {
                    r.setDataGeracao(dateFormat.parse(registro.substring(65, 73)));
                } catch (ParseException e) {
                    r.setDataGeracao(null);
                }
                try {
                    r.setNsa(Integer.parseInt(registro.substring(73, 79)));
                } catch (NumberFormatException e) {
                    r.setNsa(null);
                }
            } else if (isTrailler(r.getRegistro())) {
                // Preenche atributos de trailler
                try {
                    r.setTotalRegistros(Integer.parseInt(registro.substring(1, 7)));
                } catch (NumberFormatException e) {
                    r.setTotalRegistros(null);
                }

                try {
                    r.setValorTotal(Double.parseDouble(registro.substring(7, 24)) / 100.0);
                } catch (NumberFormatException e) {
                    r.setValorTotal(null);
                }
            } else {
                // Preenche atributos de registro de pagamento
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                r.setIdentificacaoContaCredito(registro.substring(1, 21));
                try {
                    r.setDataPagamento(dateFormat.parse(registro.substring(21, 29)));
                } catch (ParseException e) {
                    r.setDataPagamento(null);
                }
                try {
                    r.setDataCredito(dateFormat.parse(registro.substring(29, 37)));
                } catch (ParseException e) {
                    r.setDataPagamento(null);
                }
                r.setCodigoBarras(registro.substring(37, 81));
                try {
                    r.setValorRecebido(Double.parseDouble(registro.substring(81, 93)) / 100.0);
                } catch (NumberFormatException e) {
                    r.setValorRecebido(null);
                }
                try {
                    r.setValorTarifa(Double.parseDouble(registro.substring(93, 100)) / 100.0);
                } catch (NumberFormatException e) {
                    r.setValorTarifa(null);
                }
                try {
                    r.setNsr(Integer.parseInt(registro.substring(100, 108)));
                } catch (NumberFormatException e) {
                    r.setNsr(null);
                }
            }


            return r;
        }
        return null;
    }

    public static boolean isHeader(String registro) {
        return (registro.length() >= 150 && registro.charAt(0) == 'A');
    }

    public static boolean isTrailler(String registro) {
        return (registro.length() >= 150 && registro.charAt(0) == 'Z');
    }

}
