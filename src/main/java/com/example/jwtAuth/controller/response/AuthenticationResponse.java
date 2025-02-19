package com.example.jwtAuth.controller.response;

import lombok.*;
import org.hibernate.hql.internal.classic.Parser;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
    private String token;
    }

