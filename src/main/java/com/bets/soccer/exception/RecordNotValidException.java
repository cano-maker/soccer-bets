package com.bets.soccer.exception;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RecordNotValidException extends RuntimeException
{
    private String message;

    public RecordNotValidException(String message)
    {
        super(message);
        this.message = message;
    }
}
