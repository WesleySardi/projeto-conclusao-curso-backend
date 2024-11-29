package org.example.biomedbacktdd.entities.sms;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "sms_handler")
public class SmsHandler implements Serializable {
    @Id
    @Column(name = "sms_code")
    private int smsCode;

    @Column(name = "send_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp sendDate;

    @Column(name = "return_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp returnDate;

    @Column(name = "phone_user")
    private String phoneUser;

    @Column(name = "cpf_dep")
    private String cpfDep;

    public SmsHandler() { // EMPTY CONSTRUCTOR
    }

    public int getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(int smsCode) {
        this.smsCode = smsCode;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate( Timestamp  sendDate) {
        this.sendDate = sendDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
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
        if (!(o instanceof SmsHandler that)) return false;
        return getSmsCode() == that.getSmsCode() && Objects.equals(getSendDate(), that.getSendDate()) && Objects.equals(getReturnDate(), that.getReturnDate()) && Objects.equals(getPhoneUser(), that.getPhoneUser()) && Objects.equals(getCpfDep(), that.getCpfDep());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSmsCode(), getSendDate(), getReturnDate(), getPhoneUser(), getCpfDep());
    }
}
