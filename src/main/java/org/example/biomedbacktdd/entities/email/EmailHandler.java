package org.example.biomedbacktdd.entities.email;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "email_handler")
public class EmailHandler implements Serializable {
    @Id
    @Column(name = "email_code")
    private int emailCode;

    @Column(name = "send_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp sendDate;

    @Column(name = "return_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp returnDate;

    @Column(name = "email_user")
    private String emailUser;

    @Column(name = "cpf_dep")
    private String cpfDep;

    public EmailHandler() { // EMPTY CONSTRUCTOR
    }

    public int getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(int emailCode) {
        this.emailCode = emailCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailHandler that)) return false;
        return getEmailCode() == that.getEmailCode() && Objects.equals(getSendDate(), that.getSendDate()) && Objects.equals(getReturnDate(), that.getReturnDate()) && Objects.equals(getEmailUser(), that.getEmailUser()) && Objects.equals(getCpfDep(), that.getCpfDep());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmailCode(), getSendDate(), getReturnDate(), getEmailUser(), getCpfDep());
    }
}