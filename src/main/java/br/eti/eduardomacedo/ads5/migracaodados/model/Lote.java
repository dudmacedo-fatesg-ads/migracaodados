package br.eti.eduardomacedo.ads5.migracaodados.model;

import java.util.Date;
import java.util.List;

public class Lote {

    private Integer id;

    private Integer codigoRemessa;

    private Integer codigoConvenio;
    private AgenteArrecadador agenteArrecadador;

    private Date dataGeracao;

    private Integer sequencial;

    private Registro header;
    private List<Registro> registros;
    private Registro trailler;

    private String checksum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigoRemessa() {
        return codigoRemessa;
    }

    public void setCodigoRemessa(Integer codigoRemessa) {
        this.codigoRemessa = codigoRemessa;
    }

    public Integer getCodigoConvenio() {
        return codigoConvenio;
    }

    public void setCodigoConvenio(Integer codigoConvenio) {
        this.codigoConvenio = codigoConvenio;
    }

    public AgenteArrecadador getAgenteArrecadador() {
        return agenteArrecadador;
    }

    public void setAgenteArrecadador(AgenteArrecadador agenteArrecadador) {
        this.agenteArrecadador = agenteArrecadador;
    }

    public Date getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public Integer getSequencial() {
        return sequencial;
    }

    public void setSequencial(Integer sequencial) {
        this.sequencial = sequencial;
    }

    public Registro getHeader() {
        return header;
    }

    public void setHeader(Registro header) {
        this.header = header;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    public Registro getTrailler() {
        return trailler;
    }

    public void setTrailler(Registro trailler) {
        this.trailler = trailler;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}
