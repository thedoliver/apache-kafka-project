package com.denis.paymentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("idUser")
    private Long idUser;

    @JsonProperty("idProduct")
    private Long idProduct;

    @JsonProperty("cardNumber")
    private String cardNumber;
}
