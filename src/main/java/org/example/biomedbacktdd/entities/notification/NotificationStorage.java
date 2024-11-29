package org.example.biomedbacktdd.entities.notification;

import jakarta.persistence.*;
import org.example.biomedbacktdd.entities.dependent.Dependent;
import org.example.biomedbacktdd.entities.responsible.Responsible;

import java.time.ZonedDateTime;

@Entity
@Table(name = "notificacoes")
public class NotificationStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacao")
    private int idNotificacao;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "mensagem", nullable = false)
    private String mensagem;

    @Column(name = "data_envio", nullable = false)
    private ZonedDateTime dataEnvio;

    @Column(name = "lida", nullable = false)
    private Boolean lida;

    @ManyToOne
    @JoinColumn(name = "cpf_responsavel", referencedColumnName = "cpf_res")
    private Responsible responsavel;

    @JoinColumn(name = "cpf_dependente", referencedColumnName = "cpf_dep")
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

    public void setIdNotificacao(int id) {
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

    public Responsible getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsible responsavel) {
        this.responsavel = responsavel;
    }
}

