package com.todo1.store;

public enum ErrorCode {

    INTERNAL_ERROR(100, "Error interno del servidor"),
    NOT_EXISTS(101,"No existe el producto"),
    EXISTS(102, "Ya existe el producto"),
    CART_NOT_EXISTS(103, "No se ha encontrado el carrito");

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
