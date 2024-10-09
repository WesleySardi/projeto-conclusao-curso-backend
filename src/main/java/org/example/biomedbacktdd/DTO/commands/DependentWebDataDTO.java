package org.example.biomedbacktdd.DTO.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"cpfDep", "nomeDep", "idadeDep", "tipoSanguineo", "generoDep", "laudo"})
public class DependentWebDataDTO extends RepresentationModel<DependentWebDataDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = -8454951390140164547L;

    @JsonProperty("cpfDep")
    private String key;

    private String nomeDep;

    private Integer idadeDep;

    private String tipoSanguineo;

    private String generoDep;

    private String laudo;

    public DependentWebDataDTO() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNomeDep() {
        return nomeDep;
    }

    public void setNomeDep(String nomeDep) {
        this.nomeDep = nomeDep;
    }

    public Integer getIdadeDep() {
        return idadeDep;
    }

    public void setIdadeDep(Integer idadeDep) {
        this.idadeDep = idadeDep;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getGeneroDep() {
        return generoDep;
    }

    public void setGeneroDep(String generoDep) {
        this.generoDep = generoDep;
    }

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DependentWebDataDTO that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getKey(), that.getKey()) && Objects.equals(getNomeDep(), that.getNomeDep()) && Objects.equals(getIdadeDep(), that.getIdadeDep()) && Objects.equals(getTipoSanguineo(), that.getTipoSanguineo()) && Objects.equals(getGeneroDep(), that.getGeneroDep()) && Objects.equals(getLaudo(), that.getLaudo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey(), getNomeDep(), getIdadeDep(), getTipoSanguineo(), getGeneroDep(), getLaudo());
    }
}