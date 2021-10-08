package com.todo1.store;

public enum ErrorCode {


    INTERNAL_ERROR(100, "Error interno del servidor"),
    PRODUCT_NOT_EXISTS(101,"No existe el producto"),
    EXISTS(102, "Ya existe el producto"),
    CART_NOT_EXISTS(103, "No se ha encontrado el carrito"),
    CARD_NOT_EXISTS(104, "No existe la tarjeta"),
    USER_NOT_EXISTS(105, "No se ha encontrado el usuario");

    private final int value;
    private final String reasonPhrase;

    ErrorCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
