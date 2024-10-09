package org.example.biomedbacktdd.entities.dependent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "dependente")
public class Dependent implements Serializable {

    @Serial
    private static final long serialVersionUID = 7899126663964097550L;

    @Id
    @Column(name = "cpf_dep")
    private String cpfDep;

    @Column(name = "nome_dep")
    private String nomeDep;

    @Column(name = "idade_dep")
    private Integer idadeDep;

    @Column(name = "tipo_sanguineo")
    private String tipoSanguineo;
    private String laudo;

    @Column(name = "genero_dep")
    private String generoDep;

    @Column(name = "rg_dep")
    private String rgDep;

    @Column(name = "cpf_res_dep")
    private String cpfResDep;

    @Column(name = "pip_tag_id_dep")
    private Integer piTagIdDep;

    @Column(name = "cpf_ter_dep")
    private String cpfTerDep;

    @Column(name = "id_cirurgia_dep")
    private Integer idCirurgiaDep;

    @Column(name = "id_scan_dep")
    private Integer idScanDep;

    public Dependent() {
    }

    public String getCpfDep() {
        return cpfDep;
    }

    public void setCpfDep(String cpfDep) {
        this.cpfDep = cpfDep;
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
        if (!(o instanceof Dependent dependent)) return false;
        return Objects.equals(getCpfDep(), dependent.getCpfDep()) && Objects.equals(getNomeDep(), dependent.getNomeDep()) && Objects.equals(getIdadeDep(), dependent.getIdadeDep()) && Objects.equals(getTipoSanguineo(), dependent.getTipoSanguineo()) && Objects.equals(getLaudo(), dependent.getLaudo()) && Objects.equals(getGeneroDep(), dependent.getGeneroDep()) && Objects.equals(getRgDep(), dependent.getRgDep()) && Objects.equals(getCpfResDep(), dependent.getCpfResDep()) && Objects.equals(getPiTagIdDep(), dependent.getPiTagIdDep()) && Objects.equals(getCpfTerDep(), dependent.getCpfTerDep()) && Objects.equals(getIdCirurgiaDep(), dependent.getIdCirurgiaDep()) && Objects.equals(getIdScanDep(), dependent.getIdScanDep());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCpfDep(), getNomeDep(), getIdadeDep(), getTipoSanguineo(), getLaudo(), getGeneroDep(), getRgDep(), getCpfResDep(), getPiTagIdDep(), getCpfTerDep(), getIdCirurgiaDep(), getIdScanDep());
    }
}