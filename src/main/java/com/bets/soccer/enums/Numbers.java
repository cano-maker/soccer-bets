package com.bets.soccer.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Numbers
{
    ZERO(0),
    UNO(1);
    private int value;

    public int value() {
       return this.value;
    }

}
