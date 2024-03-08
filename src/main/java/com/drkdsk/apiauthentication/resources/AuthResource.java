package com.drkdsk.apiauthentication.resources;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResource extends AuthErrorResource {
    String token;
}
