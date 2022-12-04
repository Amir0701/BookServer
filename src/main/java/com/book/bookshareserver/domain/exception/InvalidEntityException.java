package com.book.bookshareserver.domain.exception;

import java.util.List;

public class InvalidEntityException extends RuntimeException{
    private final List<String> messages;

    public InvalidEntityException(List<String> messages){
        this.messages = messages;
    }

    public List<String> getMessages(){
        return messages;
    }
}
