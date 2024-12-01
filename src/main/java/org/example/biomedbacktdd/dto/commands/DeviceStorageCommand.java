package org.example.biomedbacktdd.dto.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"tokenDispositivo", "cpfResponsavel"})
public class DeviceStorageCommand implements Serializable {

    @Serial
    private static final long serialVersionUID = 8115865406889814562L;

    @Size(max = 255, message = "O token do dispositivo deve ter no máximo 255 caracteres.")
    @NotBlank(message = "Token do dispositivo não pode ser vazio.")
    @JsonProperty("tokenDispositivo")
    private String tokenDispositivo;

    @NotBlank(message = "CPF do responsável não pode ser vazio.")
    @JsonProperty("cpfResponsavel")
    private String cpfResponsavel;

    public DeviceStorageCommand() { // Empty Constructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceStorageCommand that)) return false;
        return Objects.equals(tokenDispositivo, that.tokenDispositivo) &&
                Objects.equals(cpfResponsavel, that.cpfResponsavel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenDispositivo, cpfResponsavel);
    }

    @Override
    public String toString() {
        return "DeviceStorageCommand{" +
                "tokenDispositivo='" + tokenDispositivo + '\'' +
                ", cpfResponsavel='" + cpfResponsavel + '\'' +
                '}';
    }
}
