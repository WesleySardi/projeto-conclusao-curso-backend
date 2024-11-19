package org.example.biomedbacktdd.dto.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@JsonPropertyOrder({"id", "titulo", "mensagem", "cpfResponsavel", "dataEnvio", "lida"})
public class NotificationStorageViewModel extends RepresentationModel<NotificationStorageViewModel> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long id;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("cpfResponsavel")
    private String cpfResponsavel;

    @JsonProperty("dataEnvio")
    private ZonedDateTime dataEnvio;

    @JsonProperty("lida")
    private Boolean lida;

    // Construtor padrão
    public NotificationStorageViewModel() {}

    // Construtor parametrizado
    public NotificationStorageViewModel(Long id, String titulo, String mensagem, String cpfResponsavel, ZonedDateTime dataEnvio, Boolean lida) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.cpfResponsavel = cpfResponsavel;
        this.dataEnvio = dataEnvio;
        this.lida = lida;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

    public ZonedDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(ZonedDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Boolean getLida() {
        return lida;
    }

    public void setLida(Boolean lida) {
        this.lida = lida;
    }

    // Métodos utilitários
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationStorageViewModel that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTitulo(), that.getTitulo()) &&
                Objects.equals(getMensagem(), that.getMensagem()) &&
                Objects.equals(getCpfResponsavel(), that.getCpfResponsavel()) &&
                Objects.equals(getDataEnvio(), that.getDataEnvio()) &&
                Objects.equals(getLida(), that.getLida());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getTitulo(), getMensagem(), getCpfResponsavel(), getDataEnvio(), getLida());
    }

    @Override
    public String toString() {
        return "NotificationStorageViewModel{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", cpfResponsavel='" + cpfResponsavel + '\'' +
                ", dataEnvio=" + dataEnvio +
                ", lida=" + lida +
                '}';
    }
}
