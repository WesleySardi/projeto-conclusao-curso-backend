package org.example.biomedbacktdd.entities.devicestorage;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.biomedbacktdd.entities.responsible.Responsible;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "dispositivos")
public class DeviceStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('dispositivos_id_dispositivo_seq'")
    @Column(name = "id_dispositivo", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "token_dispositivo", nullable = false)
    private String tokenDispositivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cpf_responsavel", referencedColumnName = "cpf_res")
    private Responsible responsavel;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "data_cadastro")
    private Date dataCadastro;

    public DeviceStorage(){ // Empty Constructor
    }

    @PrePersist
    protected void onCreate() {
        if (this.dataCadastro == null) {
            this.dataCadastro = new Date();
        }
    }
}