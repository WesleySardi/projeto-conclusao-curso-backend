package org.example.biomedbacktdd.dto.viewmodels;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@JsonPropertyOrder({"smsCode", "sendDate", "returnDate", "cpfDep"})
public class NewSmsViewModel extends RepresentationModel<NewSmsViewModel> implements Serializable {

    @Serial
    private static final long serialVersionUID = -5292945784469418597L;

    @JsonProperty("smsCode")
    @Mapping("smsCode")
    private int key;

    @JsonProperty("sendDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp sendDate;

    @JsonProperty("returnDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp returnDate;

    @JsonProperty("phoneUser")
    private String phoneUser;

    @JsonProperty("cpfDep")
    private String cpfDep;


    public NewSmsViewModel() { // EMPTY CONSTRUCTOR
    }

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
        if (!(o instanceof NewSmsViewModel vo)) return false;
        if (!super.equals(o)) return false;
        return getKey() == vo.getKey() && Objects.equals(getSendDate(), vo.getSendDate()) && Objects.equals(getReturnDate(), vo.getReturnDate()) && Objects.equals(getPhoneUser(), vo.getPhoneUser()) && Objects.equals(getCpfDep(), vo.getCpfDep());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey(), getSendDate(), getReturnDate(), getPhoneUser(), getCpfDep());
    }
}
