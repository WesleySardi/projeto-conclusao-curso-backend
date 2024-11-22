package org.example.biomedbacktdd.VO.auth;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.ZonedDateTime;

@JsonPropertyOrder({"id", "titulo", "mensagem", "dataEnvio", "lida", "cpfResponsavel"})
public class NotificationStorageVO {
    private Long id;
    private String titulo;
    private String mensagem;
    private ZonedDateTime dataEnvio;
    private Boolean lida;
    private String cpfResponsavel;

    public NotificationStorageVO(Long id, String titulo, String mensagem, ZonedDateTime dataEnvio, Boolean lida, String cpfResponsavel) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
        this.lida = lida;
        this.cpfResponsavel = cpfResponsavel;
    }

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

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }
}
