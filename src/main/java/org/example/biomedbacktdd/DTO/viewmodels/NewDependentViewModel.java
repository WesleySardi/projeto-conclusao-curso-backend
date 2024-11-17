package org.example.biomedbacktdd.DTO.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"cpfDep", "nomeDep", "idadeDep", "tipoSanguineo", "laudo", "generoDep", "rgDep", "cpfResDep", "piTagIdDep", "cpfTerDep", "idCirurgiaDep", "idScanDep"})
public class NewDependentViewModel extends RepresentationModel<NewDependentViewModel> implements Serializable {

    @Serial
    private static final long serialVersionUID = -2334790843533194773L;

    @Mapping("cpfDep")
    @JsonProperty("cpfDep")
    private String key;
    private String nomeDep;
    private Integer idadeDep;
    private String tipoSanguineo;
    private String laudo;
    private String generoDep;
    private String rgDep;
    private String cpfResDep;
    private Integer piTagIdDep;
    private String cpfTerDep;
    private Integer idCirurgiaDep;
    private Integer idScanDep;

    public NewDependentViewModel() {
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

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    public String getGeneroDep() {
        return generoDep;
    }

    public void setGeneroDep(String generDep) {
        this.generoDep = generDep;
    }

    public String getRgDep() {
        return rgDep;
    }

    public void setRgDep(String rgDep) {
        this.rgDep = rgDep;
    }

    public String getCpfResDep() {
        return cpfResDep;
    }

    public void setCpfResDep(String cpfResDep) {
        this.cpfResDep = cpfResDep;
    }

    public Integer getPiTagIdDep() {
        return piTagIdDep;
    }

    public void setPiTagIdDep(Integer piTagIdDep) {
        this.piTagIdDep = piTagIdDep;
    }

    public String getCpfTerDep() {
        return cpfTerDep;
    }

    public void setCpfTerDep(String cpfTerDep) {
        this.cpfTerDep = cpfTerDep;
    }

    public Integer getIdCirurgiaDep() {
        return idCirurgiaDep;
    }

    public void setIdCirurgiaDep(Integer idCirurgiaDep) {
        this.idCirurgiaDep = idCirurgiaDep;
    }

    public Integer getIdScanDep() {
        return idScanDep;
    }

    public void setIdScanDep(Integer idScanDep) {
        this.idScanDep = idScanDep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewDependentViewModel that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getKey(), that.getKey()) && Objects.equals(getNomeDep(), that.getNomeDep()) && Objects.equals(getIdadeDep(), that.getIdadeDep()) && Objects.equals(getTipoSanguineo(), that.getTipoSanguineo()) && Objects.equals(getLaudo(), that.getLaudo()) && Objects.equals(getGeneroDep(), that.getGeneroDep()) && Objects.equals(getRgDep(), that.getRgDep()) && Objects.equals(getCpfResDep(), that.getCpfResDep()) && Objects.equals(getPiTagIdDep(), that.getPiTagIdDep()) && Objects.equals(getCpfTerDep(), that.getCpfTerDep()) && Objects.equals(getIdCirurgiaDep(), that.getIdCirurgiaDep()) && Objects.equals(getIdScanDep(), that.getIdScanDep());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey(), getNomeDep(), getIdadeDep(), getTipoSanguineo(), getLaudo(), getGeneroDep(), getRgDep(), getCpfResDep(), getPiTagIdDep(), getCpfTerDep(), getIdCirurgiaDep(), getIdScanDep());
    }
}