package org.example.biomedbacktdd.entities.scanhistory;

import jakarta.persistence.*;


import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "scanhistory")
public class ScanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scan_id")
    private Long scanId;

    @Column(name = "scan_name", nullable = false)
    private String scanName;

    @Column(name = "scan_email", nullable = false)
    private String scanEmail;

    @Column(name = "scan_phone", nullable = false)
    private String scanPhone;

    @Column(name = "dep_cpf")
    private String depCpf;

    @Column(name = "scan_date_time", nullable = false)
    private OffsetDateTime scanDateTime;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScanHistory that = (ScanHistory) o;
        return Objects.equals(scanId, that.scanId) && Objects.equals(scanName, that.scanName) && Objects.equals(scanEmail, that.scanEmail) && Objects.equals(scanPhone, that.scanPhone) && Objects.equals(depCpf, that.depCpf) && Objects.equals(scanDateTime, that.scanDateTime) && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scanId, scanName, scanEmail, scanPhone, depCpf, scanDateTime, latitude, longitude);
    }
}