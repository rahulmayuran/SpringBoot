package com.stock.market.User.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CustomMessage {
    private int status;
    private String errorMessage;

}

