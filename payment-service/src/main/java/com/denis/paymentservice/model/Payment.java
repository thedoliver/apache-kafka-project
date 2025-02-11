package com.denis.paymentservice.model;

import lombok.Getter;

import java.io.Serializable;

@Getter // Eliminar o Boilerplate metodos assessores
public class Payment implements Serializable {

    private Long id;
    private Long idUser;
    private Long idProduct;
    private String cardNumber;

}
