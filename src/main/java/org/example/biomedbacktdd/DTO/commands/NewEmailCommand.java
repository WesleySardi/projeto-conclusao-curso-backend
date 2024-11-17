package org.example.biomedbacktdd.DTO.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@JsonPropertyOrder({"emailCode", "sendDate", "returnDate", "emailUser", "cpfDep"})
public class NewEmailCommand extends RepresentationModel<NewEmailCommand> implements Serializable {

    @Serial
    private static final long serialVersionUID = 5633682452677692515L; // Atualize para um novo valor

    @JsonProperty("emailCode")
    @Mapping("emailCode")
    private int key;

    @JsonProperty("sendDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp sendDate;

    @JsonProperty("returnDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp returnDate;

    @JsonProperty("emailUser")
    private String emailUser;

    @JsonProperty("cpfDep")
    private String cpfDep;

    // Construtores padrão e completo
    public NewEmailCommand() {
    }

    public NewEmailCommand(int key, Timestamp sendDate, Timestamp returnDate, String emailUser, String cpfDep) {
        this.key = key;
        this.sendDate = sendDate;
        this.returnDate = returnDate;
        this.emailUser = emailUser;
        this.cpfDep = cpfDep;
    }

    // Getters e Setters
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getCpfDep() {
        return cpfDep;
    }

    public void setCpfDep(String cpfDep) {
        this.cpfDep = cpfDep;
    }

    // Métodos equals e hashCode atualizados para incluir emailUser
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewEmailCommand vo)) return false;
        if (!super.equals(o)) return false;
        return getKey() == vo.getKey() && Objects.equals(getSendDate(), vo.getSendDate()) && Objects.equals(getReturnDate(), vo.getReturnDate()) && Objects.equals(getEmailUser(), vo.getEmailUser()) && Objects.equals(getCpfDep(), vo.getCpfDep());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey(), getSendDate(), getReturnDate(), getEmailUser(), getCpfDep());
    }
}