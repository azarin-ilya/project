package com.WoodCraftStudio.project.exception_hadling;

public enum ExceptionMessage {

    CLIENT_NOT_FOUND("Клиент не найден.", 404), ORDER_NOT_FOUND("Заказ не найден.",404)
    , MANAGER_NOT_FOUND("Менеджер не найден.",404);

    private String message;
    private Integer statusCode;

    ExceptionMessage(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
