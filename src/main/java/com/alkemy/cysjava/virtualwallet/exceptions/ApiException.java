package com.alkemy.cysjava.virtualwallet.exceptions;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
public class ApiException {
    private List<String> messages;

    public ApiException(List<String> messages){
        this.messages = messages;
    }
}
