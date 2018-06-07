package br.eti.eduardomacedo.ads5.migracaodados.util;

import br.eti.eduardomacedo.ads5.migracaodados.model.Lote;
import br.eti.eduardomacedo.ads5.migracaodados.model.Registro;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Checksum {

    public static String md5(Lote lote) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");

            m.update(lote.getHeader().getRegistro().getBytes());
            m.update("\n".getBytes());

            for (Registro r : lote.getRegistros()) {
                m.update(r.getRegistro().getBytes());
                m.update("\n".getBytes());
            }

            m.update(lote.getTrailler().getRegistro().getBytes());

            String ret = new BigInteger(1, m.digest()).toString(16);
            while (ret.length() < 32) {
                ret = "0" + ret;
            }
            return ret;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String md5(String registro) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");

            m.update(registro.getBytes());

            String ret = new BigInteger(1, m.digest()).toString(16);
            while (ret.length() < 32) {
                ret = "0" + ret;
            }
            return ret;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

}
