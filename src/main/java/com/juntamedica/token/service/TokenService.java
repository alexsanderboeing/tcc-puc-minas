package com.juntamedica.token.service;

import com.juntamedica.token.payload.TokenCreationRequest;
import com.juntamedica.token.payload.TokenCreationResponse;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

@Service
public interface TokenService {

    TokenCreationResponse save(TokenCreationRequest tokenCreationRequest, String tokenValue);

    String extractJwtFromRequest(HttpServletRequest request);

    TokenCreationResponse saveTokenDuploFator(String tokenValue);
}
