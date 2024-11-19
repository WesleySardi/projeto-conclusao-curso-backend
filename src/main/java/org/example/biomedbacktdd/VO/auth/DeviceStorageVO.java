package org.example.biomedbacktdd.VO.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id", "tokenDispositivo", "dataCadastro"})
public class DeviceStorageVO extends RepresentationModel<DeviceStorageVO> implements Serializable {

    @Serial
    private static final long serialVersionUID = -2093471018037172557L;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("tokenDispositivo")
    private String tokenDispositivo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    @JsonProperty("dataCadastro")
    private Instant dataCadastro;

    public DeviceStorageVO() {
    }

    public DeviceStorageVO(Integer id, String tokenDispositivo, Instant dataCadastro) {
        this.id = id;
        this.tokenDispositivo = tokenDispositivo;
        this.dataCadastro = dataCadastro;
    }

    public static DeviceStorageVO of(Integer id, String tokenDispositivo, Instant dataCadastro) {
        return new DeviceStorageVO(id, tokenDispositivo, dataCadastro);
    }
}
