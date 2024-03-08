package com.drkdsk.apiauthentication.exceptions.errors;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthError {

    private String message;

    public AuthError(String message)
    {
        this.message = message;
    }

}
