package org.example.biomedbacktdd.dto.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * ViewModel para encapsular os dados de um dispositivo armazenado no banco de dados.
 */
@JsonPropertyOrder({"id", "tokenDispositivo", "cpfResponsavel"})
public class DeviceStorageViewModel extends RepresentationModel<DeviceStorageViewModel> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long id;

    @JsonProperty("tokenDispositivo")
    private String tokenDispositivo;

    @JsonProperty("cpfResponsavel")
    private String cpfResponsavel;

    // Construtor padrão
    public DeviceStorageViewModel() {}

    // Construtor parametrizado
    public DeviceStorageViewModel(Long id, String tokenDispositivo, String cpfResponsavel) {
        this.id = id;
        this.tokenDispositivo = tokenDispositivo;
        this.cpfResponsavel = cpfResponsavel;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTokenDispositivo() {
        return tokenDispositivo;
    }

    public void setTokenDispositivo(String tokenDispositivo) {
        this.tokenDispositivo = tokenDispositivo;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

    // Métodos utilitários
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceStorageViewModel that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTokenDispositivo(), that.getTokenDispositivo()) &&
                Objects.equals(getCpfResponsavel(), that.getCpfResponsavel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getTokenDispositivo(), getCpfResponsavel());
    }

    @Override
    public String toString() {
        return "DeviceStorageViewModel{" +
                "id=" + id +
                ", tokenDispositivo='" + tokenDispositivo + '\'' +
                ", cpfResponsavel='" + cpfResponsavel + '\'' +
                '}';
    }
}

