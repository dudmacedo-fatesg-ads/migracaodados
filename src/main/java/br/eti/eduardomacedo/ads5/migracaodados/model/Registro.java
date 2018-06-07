package br.eti.eduardomacedo.ads5.migracaodados.model;

import java.util.Date;

public class Registro {

    private Lote lote;
    private Integer sequencia;

    private String tipo;
    private String registro;

    // Atributos do Header (A)
    private Integer codigoRemessa;
    private String codigoConvenio;
    private String nomeEmpresa;
    private Integer codigoAgenteArrecadador;
    private String nomeAgenteArrecadador;
    private Date dataGeracao;
    private Integer nsa;

    // Atributos do Pagamento (G)
    private String identificacaoContaCredito;
    private Date dataPagamento;
    private Date dataCredito;
    private String codigoBarras;
    private Double valorRecebido;
    private Double valorTarifa;
    private Integer nsr;

    // Atributos do Trailler
    private Integer totalRegistros;
    private Double valorTotal;

    private String checksum;

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public Integer getCodigoRemessa() {
        return codigoRemessa;
    }

    public void setCodigoRemessa(Integer codigoRemessa) {
        this.codigoRemessa = codigoRemessa;
    }

    public String getCodigoConvenio() {
        return codigoConvenio;
    }

    public void setCodigoConvenio(String codigoConvenio) {
        this.codigoConvenio = codigoConvenio;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public Integer getCodigoAgenteArrecadador() {
        return codigoAgenteArrecadador;
    }

    public void setCodigoAgenteArrecadador(Integer codigoAgenteArrecadador) {
        this.codigoAgenteArrecadador = codigoAgenteArrecadador;
    }

    public String getNomeAgenteArrecadador() {
        return nomeAgenteArrecadador;
    }

    public void setNomeAgenteArrecadador(String nomeAgenteArrecadador) {
        this.nomeAgenteArrecadador = nomeAgenteArrecadador;
    }

    public Date getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public Integer getNsa() {
        return nsa;
    }

    public void setNsa(Integer nsa) {
        this.nsa = nsa;
    }

    public String getIdentificacaoContaCredito() {
        return identificacaoContaCredito;
    }

    public void setIdentificacaoContaCredito(String identificacaoContaCredito) {
        this.identificacaoContaCredito = identificacaoContaCredito;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataCredito() {
        return dataCredito;
    }

    public void setDataCredito(Date dataCredito) {
        this.dataCredito = dataCredito;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Double getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(Double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public Double getValorTarifa() {
        return valorTarifa;
    }

    public void setValorTarifa(Double valorTarifa) {
        this.valorTarifa = valorTarifa;
    }

    public Integer getNsr() {
        return nsr;
    }

    public void setNsr(Integer nsr) {
        this.nsr = nsr;
    }

    public Integer getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(Integer totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}
