package com.bets.soccer.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RecordNotFoundException extends RuntimeException
{
    private String message;

    public RecordNotFoundException(String message)
    {
        super(message);
        this.message = message;
    }
}
