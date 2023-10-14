package com.juntamedica.token.service;

import com.juntamedica.token.Token;
import com.juntamedica.token.TokenRepository;
import com.juntamedica.token.factory.TokenFactory;
import com.juntamedica.token.payload.TokenCreationRequest;
import com.juntamedica.token.payload.TokenCreationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TokenFactory tokenFactory;

    @Override
    public TokenCreationResponse save(TokenCreationRequest tokenCreationRequest, String tokenValue) {
        Token token = tokenRepository.save(tokenFactory.build(tokenValue));

        return tokenFactory.buildResponse(token);
    }

    @Override
    public String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            return bearerToken;
        }
    }

    @Override
    public TokenCreationResponse saveTokenDuploFator(String tokenValue) {
        Token token = tokenRepository.save(tokenFactory.build(tokenValue));

        return tokenFactory.buildResponse(token);
    }
}
