package com.pathbreaker.authentication.service.serviceimpl;

import com.pathbreaker.authentication.service.exception.AdminException;
import com.pathbreaker.authentication.service.exception.InvalidInputException;
import com.pathbreaker.authentication.service.mappers.TokenDetailsMapper;
import com.pathbreaker.authentication.service.repository.TokenDetailsRepository;
import com.pathbreaker.authentication.service.entity.TokenDetails;
import com.pathbreaker.authentication.service.request.TokenDetailsPayload;
import com.pathbreaker.authentication.service.request.TokenDetailsUpdateRequest;
import com.pathbreaker.authentication.service.response.TokenDetailsResponse;
import com.pathbreaker.authentication.service.service.TokenDetailsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TokenDetailsServiceImpl implements TokenDetailsService {
    @Autowired
    public TokenDetailsServiceImpl(TokenDetailsRepository repository,
                                   TokenDetailsMapper tokenDetailsMapper) {
        this.repository = repository;
        this.tokenDetailsMapper = tokenDetailsMapper;
    }

    private final TokenDetailsRepository repository;

    private final TokenDetailsMapper tokenDetailsMapper;


    @Override  //create operation
    public TokenDetailsPayload saveTokenDetails(TokenDetailsPayload request) {
        try {
            validateRequest(request);
            TokenDetails tokenDetails = tokenDetailsMapper.requestToEntity(request);
            tokenDetails.setCreatedDate(LocalDate.now());
            tokenDetails.setLastUpdatedDate(LocalDate.now());
            repository.save(tokenDetails);
           TokenDetailsPayload response = tokenDetailsMapper.entityToRequest(tokenDetails);

           return response;
        }
        catch (InvalidInputException e) {
            throw e;
        }catch (Exception e) {
            throw new AdminException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    // Read operations
    @Override
    public List<TokenDetailsResponse> getAllTokenDetails() {
        List<TokenDetails> allTokenDetails = repository.findAll();
        return tokenDetailsMapper.entitiesToResponses(allTokenDetails);
    }

    //read only one operation
    @Override
    public TokenDetailsResponse getTokenDetailsById(Long id) {
        Optional<TokenDetails> tokenDetailsOptional = repository.findById(id);

        if (tokenDetailsOptional.isPresent()) {
            TokenDetails tokenDetails = tokenDetailsOptional.get();
            return tokenDetailsMapper.entityToResponse(tokenDetails);
        } else {
            // Handle the case where the token with the given ID is not found
            throw new EntityNotFoundException("Token with ID " + id + " not found");
        }
    }
    @Override
    public TokenDetailsResponse getTokenByClientId(String clientId) {
        TokenDetails tokenDetails = repository.findByClientId(clientId);
        if (StringUtils.hasLength(clientId)){
            return tokenDetailsMapper.entityToResponse(tokenDetails);
        }
        else {
            // Handle the case where the token with the given ID is not found
            throw new EntityNotFoundException("Token with clientId " + clientId + " not found");
        }
    }

    // Update operation
    @Override
    public TokenDetailsUpdateRequest updateTokenDetails(Long id, TokenDetailsUpdateRequest tokenDetailsUpdateRequest) {
        Optional<TokenDetails> existingTokenDetailsOptional = repository.findById(id);

        try {
            if (existingTokenDetailsOptional.isPresent()) {
                TokenDetails existingTokenDetails = existingTokenDetailsOptional.get();
                validateUpdateRequest(tokenDetailsUpdateRequest);
                TokenDetails tokenDetails = tokenDetailsMapper.requestToUpdateEntity(existingTokenDetails,tokenDetailsUpdateRequest);
                tokenDetails.setLastUpdatedDate(LocalDate.now());
                repository.save(tokenDetails);
                TokenDetailsUpdateRequest response = tokenDetailsMapper.entityToUpdateRequest(tokenDetails);

            return response;
        }
        return tokenDetailsUpdateRequest;
    }
         catch(InvalidInputException e){
            throw e;
        }catch(Exception e){
            throw new AdminException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    // Delete operation
    @Override
    public void deleteTokenDetailsById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void validateRequest(TokenDetailsPayload request) {
        // Check if clientId, accessExpirationInMin, and refreshExpirationInMin are present
       if (!StringUtils.hasLength(request.getClientId())){
           throw new InvalidInputException(HttpStatus.BAD_REQUEST,"clientId is required");
       }
        if (Objects.isNull(request.getAccessExpirationInMin())){
            throw new InvalidInputException(HttpStatus.BAD_REQUEST,"Access code is required");
        }
        if (Objects.isNull(request.getRefreshExpirationInMin())){
            throw new InvalidInputException(HttpStatus.BAD_REQUEST,"Refresh code is required");
        }
    }

    @Override
    public void validateUpdateRequest(TokenDetailsUpdateRequest request) {
        // Check if clientId, accessExpirationInMin, and refreshExpirationInMin are present
        if (!StringUtils.hasLength(request.getClientId())){
            throw new InvalidInputException(HttpStatus.BAD_REQUEST,"clientId is required");
        }
        if (Objects.isNull(request.getAccessExpirationInMin())){
            throw new InvalidInputException(HttpStatus.BAD_REQUEST,"Access code is required");
        }
        if (Objects.isNull(request.getRefreshExpirationInMin())){
            throw new InvalidInputException(HttpStatus.BAD_REQUEST,"Refresh code is required");
        }
    }


}
