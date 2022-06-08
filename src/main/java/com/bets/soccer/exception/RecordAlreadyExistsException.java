package com.bets.soccer.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RecordAlreadyExistsException extends RuntimeException
{
    private String message;

    public RecordAlreadyExistsException(String message)
    {
        super(message);
        this.message = message;
    }

}
