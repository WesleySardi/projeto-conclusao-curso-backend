package org.example.biomedbacktdd.dto.viewmodels;

import java.util.Objects;

public class StatusResponseViewModel<T> {
    private T responseContent;
    private String infoMessage;
    private String statusMessage;
    private int status;
    private Boolean isOk;

    public StatusResponseViewModel() {
    }

    public StatusResponseViewModel(T responseContent, String infoMessage, String statusMessage, int status, Boolean isOk) {
        this.responseContent = responseContent;
        this.infoMessage = infoMessage;
        this.statusMessage = statusMessage;
        this.status = status;
        this.isOk = isOk;
    }

    public T getContentResponse() {
        return responseContent;
    }

    public void setContentResponse(T responseContent) {
        this.responseContent = responseContent;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Boolean getIsOk() {
        return isOk;
    }

    public void setIsOk(Boolean isOk) {
        this.isOk = isOk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusResponseViewModel<?> that = (StatusResponseViewModel<?>) o;
        return status == that.status && Objects.equals(responseContent, that.responseContent) && Objects.equals(infoMessage, that.infoMessage) && Objects.equals(statusMessage, that.statusMessage) && Objects.equals(isOk, that.isOk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseContent, infoMessage, statusMessage, status, isOk);
    }
}
