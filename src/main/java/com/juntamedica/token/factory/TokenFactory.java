package com.juntamedica.token.factory;

import com.juntamedica.token.Token;
import com.juntamedica.token.payload.TokenCreationResponse;
import org.springframework.stereotype.Component;

@Component
public class TokenFactory {

    public Token build(String token) {
        return Token.builder()
                .id(null)
                .active(true)
                .tokenValue(token)
                .expirationDate(null)
                .build();
    }

    public TokenCreationResponse buildResponse(Token token) {
        return TokenCreationResponse.builder()
                .token(token.getTokenValue())
                .build();
    }

    public TokenCreationResponse buildResponse(String token) {
        return TokenCreationResponse.builder()
                .token(token)
                .build();
    }
}
