package com.pathbreaker.authentication.service.service;

import com.pathbreaker.authentication.service.request.TokenDetailsPayload;
import com.pathbreaker.authentication.service.request.TokenDetailsUpdateRequest;
import com.pathbreaker.authentication.service.response.TokenDetailsResponse;

import java.util.List;

public interface TokenDetailsService {

    public TokenDetailsPayload saveTokenDetails(TokenDetailsPayload request);

    public List<TokenDetailsResponse> getAllTokenDetails();

    public TokenDetailsResponse getTokenDetailsById(Long id);

    public TokenDetailsUpdateRequest updateTokenDetails(Long id, TokenDetailsUpdateRequest tokenDetailsPayload);

    public void deleteTokenDetailsById(Long id);

    public void validateRequest(TokenDetailsPayload request);
    public void validateUpdateRequest(TokenDetailsUpdateRequest request);

    public TokenDetailsResponse getTokenByClientId(String clientId);
}
