package org.example.biomedbacktdd.dto.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@JsonPropertyOrder({"id_notificacao", "titulo", "mensagem", "cpfResponsavel", "dataEnvio", "lida", "cpfDependente"})
public class NotificationStorageResult extends RepresentationModel<NotificationStorageResult> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Mapping("id_notificacao")
    @JsonProperty("id_notificacao")
    private int idNotificacao;

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

    @JsonProperty("cpfDependente")
    private String cpfDependente;

    public String getCpfDependente() {
        return cpfDependente;
    }

    public void setCpfDependente(String cpfDependente) {
        this.cpfDependente = cpfDependente;
    }

    public int getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdNotificacao(int idNotificacao) {
        this.idNotificacao = idNotificacao;
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
        if (!(o instanceof NotificationStorageResult that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getIdNotificacao(), that.getIdNotificacao()) &&
                Objects.equals(getTitulo(), that.getTitulo()) &&
                Objects.equals(getMensagem(), that.getMensagem()) &&
                Objects.equals(getCpfResponsavel(), that.getCpfResponsavel()) &&
                Objects.equals(getDataEnvio(), that.getDataEnvio()) &&
                Objects.equals(getLida(), that.getLida()) &&
                Objects.equals(getCpfDependente(), that.getCpfDependente());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIdNotificacao(), getTitulo(), getMensagem(), getCpfResponsavel(), getDataEnvio(), getLida());
    }

    @Override
    public String toString() {
        return "NotificationStorageResult{" +
                "id=" + idNotificacao +
                ", titulo='" + titulo + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", cpfResponsavel='" + cpfResponsavel + '\'' +
                ", dataEnvio=" + dataEnvio +
                ", lida=" + lida +
                ", cpfDependente=" + cpfDependente +
                '}';
    }
}

