package com.WoodCraftStudio.project.exception_hadling;

public class NotFoundException extends RuntimeException{

   private ExceptionMessage exceptionMessage;

    public NotFoundException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }
}
