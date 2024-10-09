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

    @Column(name = "endereco_id_res")
    private Integer enderecoIdRes;

    @Column(name = "rg_res")
    private String rgRes;

    @Column(name = "senha_res")
    private String senhaRes;

    public Responsible() {
    }

    public Responsible(String cpfRes, String nomeRes, Integer idadeRes, String contato1Res, String contato2Res, String contato3Res, Integer planoAssinado, String emailRes, Integer enderecoIdRes, String rgRes, String senhaRes) {

        this.cpfRes = cpfRes;
        this.nomeRes = nomeRes;
        this.idadeRes = idadeRes;
        this.contato1Res = contato1Res;
        this.contato2Res = contato2Res;
        this.contato3Res = contato3Res;
        this.planoAssinado = planoAssinado;
        this.emailRes = emailRes;
        this.enderecoIdRes = enderecoIdRes;
        this.rgRes = rgRes;
        this.senhaRes = senhaRes;
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

    public Integer getEnderecoIdRes() {
        return enderecoIdRes;
    }

    public void setEnderecoIdRes(Integer enderecoIdRes) {
        this.enderecoIdRes = enderecoIdRes;
    }

    public String getRgRes() {
        return rgRes;
    }

    public void setRgRes(String rgRes) {
        this.rgRes = rgRes;
    }

    public String getSenhaRes() {
        return senhaRes;
    }

    public void setSenhaRes(String senhaRes) {
        this.senhaRes = senhaRes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Responsible that = (Responsible) o;
        return Objects.equals(cpfRes, that.cpfRes) && Objects.equals(nomeRes, that.nomeRes) && Objects.equals(idadeRes, that.idadeRes) && Objects.equals(contato1Res, that.contato1Res) && Objects.equals(contato2Res, that.contato2Res) && Objects.equals(contato3Res, that.contato3Res) && Objects.equals(planoAssinado, that.planoAssinado) && Objects.equals(emailRes, that.emailRes) && Objects.equals(enderecoIdRes, that.enderecoIdRes) && Objects.equals(rgRes, that.rgRes) && Objects.equals(senhaRes, that.senhaRes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfRes, nomeRes, idadeRes, contato1Res, contato2Res, contato3Res, planoAssinado, emailRes, enderecoIdRes, rgRes, senhaRes);
    }
}