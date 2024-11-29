package org.example.biomedbacktdd.dto.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@JsonPropertyOrder({ "id_notificacao", "titulo", "mensagem", "cpfResponsavel", "dataEnvio", "lida", "cpfDependente"})
public class NotificationStorageCommand implements Serializable {

    @Serial
    private static final long serialVersionUID = 314070064715448129L;

    @JsonProperty("id_notificacao")
    private int idNotificacao;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("mensagem")
    private String mensagem;

    @JsonProperty("cpfResponsavel")
    private String cpfResponsavel;

    @JsonProperty("dataEnvio")
    private ZonedDateTime dataEnvio = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

    @JsonProperty("lida")
    private Boolean lida;

    @JsonProperty("cpfDependente")
    private String cpfDependente;

    // Construtor padrão
    public NotificationStorageCommand() {
    }

    // Construtor com parâmetros
    public NotificationStorageCommand(int idNotificacao, String titulo, String mensagem, String cpfResponsavel, ZonedDateTime dataEnvio, Boolean lida, String cpfDependente) {
        this.idNotificacao = idNotificacao;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.cpfResponsavel = cpfResponsavel;
        this.dataEnvio = dataEnvio;
        this.lida = lida;
        this.cpfDependente = cpfDependente;
    }

    // Getters e Setters


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

    @NotBlank(message = "O título da notificação precisa estar presente.")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @NotBlank(message = "A notificação precisa ter uma mensagem.")
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @NotBlank(message = "O CPF do responsável deve ser informado.")
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
        if (!(o instanceof NotificationStorageCommand that)) return false;
        return Objects.equals(titulo, that.titulo) &&
                Objects.equals(mensagem, that.mensagem) &&
                Objects.equals(cpfResponsavel, that.cpfResponsavel) &&
                Objects.equals(dataEnvio, that.dataEnvio) &&
                Objects.equals(lida, that.lida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, mensagem, cpfResponsavel, dataEnvio, lida);
    }

    @Override
    public String toString() {
        return "NotificationStorageCommand{" +
                "titulo='" + titulo + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", cpfResponsavel='" + cpfResponsavel + '\'' +
                ", dataEnvio=" + dataEnvio +
                ", lida=" + lida +
                '}';
    }
}
