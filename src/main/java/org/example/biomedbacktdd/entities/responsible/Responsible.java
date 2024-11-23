package org.example.biomedbacktdd.entities.responsible;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "responsavel")
public class Responsible implements Serializable {

    @Serial
    private static final long serialVersionUID = -5996657227761996072L;

    @Id
    @Column(name = "cpf_res")
    private String cpfRes;

    @Column(name = "nome_res")
    private String nomeRes;

    @Column(name = "idade_res")
    private Integer idadeRes;

    @Column(name = "contato1_res")
    private String contato1Res;

    @Column(name = "contato2_res")
    private String contato2Res;

    @Column(name = "contato3_res")
    private String contato3Res;

    @Column(name = "plano_assinado")
    private Integer planoAssinado;

    @Column(name = "email_res")
    private String emailRes;

    @Column(name = "rg_res")
    private String rgRes;

    @Column(name = "cep_res")
    private String cepRes;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    public Responsible() {
    }

    public Responsible(String cpfRes, String nomeRes, Integer idadeRes, String contato1Res, String contato2Res, String contato3Res, Integer planoAssinado, String emailRes, String rgRes, String cepRes, String logradouro, Integer numero, String complemento, String bairro, String cidade, String estado) {
        this.cpfRes = cpfRes;
        this.nomeRes = nomeRes;
        this.idadeRes = idadeRes;
        this.contato1Res = contato1Res;
        this.contato2Res = contato2Res;
        this.contato3Res = contato3Res;
        this.planoAssinado = planoAssinado;
        this.emailRes = emailRes;
        this.rgRes = rgRes;
        this.cepRes = cepRes;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCpfRes() {
        return cpfRes;
    }

    public void setCpfRes(String cpfRes) {
        this.cpfRes = cpfRes;
    }

    public String getNomeRes() {
        return nomeRes;
    }

    public void setNomeRes(String nomeRes) {
        this.nomeRes = nomeRes;
    }

    public Integer getIdadeRes() {
        return idadeRes;
    }

    public void setIdadeRes(Integer idadeRes) {
        this.idadeRes = idadeRes;
    }

    public String getContato1Res() {
        return contato1Res;
    }

    public void setContato1Res(String contato1Res) {
        this.contato1Res = contato1Res;
    }

    public String getContato2Res() {
        return contato2Res;
    }

    public void setContato2Res(String contato2Res) {
        this.contato2Res = contato2Res;
    }

    public String getContato3Res() {
        return contato3Res;
    }

    public void setContato3Res(String contato3Res) {
        this.contato3Res = contato3Res;
    }

    public Integer getPlanoAssinado() {
        return planoAssinado;
    }

    public void setPlanoAssinado(Integer planoAssinado) {
        this.planoAssinado = planoAssinado;
    }

    public String getEmailRes() {
        return emailRes;
    }

    public void setEmailRes(String emailRes) {
        this.emailRes = emailRes;
    }

    public String getRgRes() {
        return rgRes;
    }

    public void setRgRes(String rgRes) {
        this.rgRes = rgRes;
    }

    public String getCepRes() {
        return cepRes;
    }

    public void setCepRes(String cepRes) {
        this.cepRes = cepRes;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Responsible that = (Responsible) o;
        return Objects.equals(getCpfRes(), that.getCpfRes()) && Objects.equals(getNomeRes(), that.getNomeRes()) && Objects.equals(getIdadeRes(), that.getIdadeRes()) && Objects.equals(getContato1Res(), that.getContato1Res()) && Objects.equals(getContato2Res(), that.getContato2Res()) && Objects.equals(getContato3Res(), that.getContato3Res()) && Objects.equals(getPlanoAssinado(), that.getPlanoAssinado()) && Objects.equals(getEmailRes(), that.getEmailRes()) && Objects.equals(getRgRes(), that.getRgRes()) && Objects.equals(getCepRes(), that.getCepRes()) && Objects.equals(getLogradouro(), that.getLogradouro()) && Objects.equals(getNumero(), that.getNumero()) && Objects.equals(getComplemento(), that.getComplemento()) && Objects.equals(getBairro(), that.getBairro()) && Objects.equals(getCidade(), that.getCidade()) && Objects.equals(getEstado(), that.getEstado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCpfRes(), getNomeRes(), getIdadeRes(), getContato1Res(), getContato2Res(), getContato3Res(), getPlanoAssinado(), getEmailRes(), getRgRes(), getCepRes(), getLogradouro(), getNumero(), getComplemento(), getBairro(), getCidade(), getEstado());
    }
}