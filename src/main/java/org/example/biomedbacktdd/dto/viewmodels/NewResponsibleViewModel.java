package org.example.biomedbacktdd.dto.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"cpfRes", "nomeRes", "idadeRes", "contato1Res", "contato2Res", "contato3Res", "planoAssinado", "emailRes", "rgRes", "cepRes", "logradouro", "numero", "complemento", "bairro", "cidade", "estado"})
public class NewResponsibleViewModel extends RepresentationModel<NewResponsibleViewModel> implements Serializable {

    @Serial
    private static final long serialVersionUID = -1907032921662951285L;

    @Mapping("cpfRes")
    @JsonProperty("cpfRes")
    private String key;
    private String nomeRes;
    private Integer idadeRes;
    private String contato1Res;
    private String contato2Res;
    private String contato3Res;
    private Integer planoAssinado;
    private String emailRes;
    private String rgRes;
    private String cepRes;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    public NewResponsibleViewModel() {
    }

    public NewResponsibleViewModel(String key, String nomeRes, Integer idadeRes, String contato1Res, String contato2Res, String contato3Res, Integer planoAssinado, String emailRes, String rgRes, String cepRes, String logradouro, Integer numero, String complemento, String bairro, String cidade, String estado) {
        this.key = key;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
        if (!super.equals(o)) return false;
        NewResponsibleViewModel that = (NewResponsibleViewModel) o;
        return Objects.equals(getKey(), that.getKey()) && Objects.equals(getNomeRes(), that.getNomeRes()) && Objects.equals(getIdadeRes(), that.getIdadeRes()) && Objects.equals(getContato1Res(), that.getContato1Res()) && Objects.equals(getContato2Res(), that.getContato2Res()) && Objects.equals(getContato3Res(), that.getContato3Res()) && Objects.equals(getPlanoAssinado(), that.getPlanoAssinado()) && Objects.equals(getEmailRes(), that.getEmailRes()) && Objects.equals(getRgRes(), that.getRgRes()) && Objects.equals(getCepRes(), that.getCepRes()) && Objects.equals(getLogradouro(), that.getLogradouro()) && Objects.equals(getNumero(), that.getNumero()) && Objects.equals(getComplemento(), that.getComplemento()) && Objects.equals(getBairro(), that.getBairro()) && Objects.equals(getCidade(), that.getCidade()) && Objects.equals(getEstado(), that.getEstado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey(), getNomeRes(), getIdadeRes(), getContato1Res(), getContato2Res(), getContato3Res(), getPlanoAssinado(), getEmailRes(), getRgRes(), getCepRes(), getLogradouro(), getNumero(), getComplemento(), getBairro(), getCidade(), getEstado());
    }
}