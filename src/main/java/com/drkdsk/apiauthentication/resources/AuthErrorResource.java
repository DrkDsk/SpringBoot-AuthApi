package com.drkdsk.apiauthentication.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthErrorResource {
    String message;
    boolean success;
}
