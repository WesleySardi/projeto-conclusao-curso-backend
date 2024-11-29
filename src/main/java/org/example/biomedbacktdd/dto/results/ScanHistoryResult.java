package org.example.biomedbacktdd.dto.results;

import java.time.OffsetDateTime;

public class ScanHistoryResult {

    private Long scanId;
    private String scanName;
    private String scanEmail;
    private String scanPhone;
    private String depCpf;
    private OffsetDateTime scanDateTime;
    private Double latitude;
    private Double longitude;

    // Getters and Setters
    public Long getScanId() {
        return scanId;
    }

    public void setScanId(Long scanId) {
        this.scanId = scanId;
    }

    public String getScanName() {
        return scanName;
    }

    public void setScanName(String scanName) {
        this.scanName = scanName;
    }

    public String getScanEmail() {
        return scanEmail;
    }

    public void setScanEmail(String scanEmail) {
        this.scanEmail = scanEmail;
    }

    public String getScanPhone() {
        return scanPhone;
    }

    public void setScanPhone(String scanPhone) {
        this.scanPhone = scanPhone;
    }

    public String getDepCpf() {
        return depCpf;
    }

    public void setDepCpf(String depCpf) {
        this.depCpf = depCpf;
    }

    public OffsetDateTime getScanDateTime() {
        return scanDateTime;
    }

    public void setScanDateTime(OffsetDateTime scanDateTime) {
        this.scanDateTime = scanDateTime;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
