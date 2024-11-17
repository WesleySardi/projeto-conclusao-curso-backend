package org.example.biomedbacktdd.DTO.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"cpfRes", "nomeRes", "idadeRes", "contato1Res", "contato2Res", "contato3Res", "planoAssinado", "emailRes", "enderecoIdRes", "rgRes", "senhaRes"})
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
    private Integer enderecoIdRes;
    private String rgRes;
    private String senhaRes;

    public NewResponsibleViewModel() {
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
        if (!(o instanceof NewResponsibleViewModel that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getKey(), that.getKey()) && Objects.equals(getNomeRes(), that.getNomeRes()) && Objects.equals(getIdadeRes(), that.getIdadeRes()) && Objects.equals(getContato1Res(), that.getContato1Res()) && Objects.equals(getContato2Res(), that.getContato2Res()) && Objects.equals(getContato3Res(), that.getContato3Res()) && Objects.equals(getPlanoAssinado(), that.getPlanoAssinado()) && Objects.equals(getEmailRes(), that.getEmailRes()) && Objects.equals(getEnderecoIdRes(), that.getEnderecoIdRes()) && Objects.equals(getRgRes(), that.getRgRes()) && Objects.equals(getSenhaRes(), that.getSenhaRes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey(), getNomeRes(), getIdadeRes(), getContato1Res(), getContato2Res(), getContato3Res(), getPlanoAssinado(), getEmailRes(), getEnderecoIdRes(), getRgRes(), getSenhaRes());
    }
}