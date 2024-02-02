package com.example.efilingbazaar.mapper;

import com.example.efilingbazaar.entity.CustomerEntity;
import com.example.efilingbazaar.request.CustomerRequest;
import com.example.efilingbazaar.response.CustomerResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-25T19:58:26+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerRequest requestToEntity(CustomerEntity customerEntity) {
        if ( customerEntity == null ) {
            return null;
        }

        CustomerRequest customerRequest = new CustomerRequest();

        customerRequest.setCustomerId( customerEntity.getCustomerId() );
        customerRequest.setFirst_name( customerEntity.getFirst_name() );
        customerRequest.setLast_name( customerEntity.getLast_name() );
        customerRequest.setEmail( customerEntity.getEmail() );
        customerRequest.setMobile_no( customerEntity.getMobile_no() );
        customerRequest.setPan_no( customerEntity.getPan_no() );
        customerRequest.setPassword( customerEntity.getPassword() );
        customerRequest.setOtp( customerEntity.getOtp() );
        customerRequest.setOtpTime( customerEntity.getOtpTime() );
        customerRequest.setCreatedDate( customerEntity.getCreatedDate() );
        customerRequest.setLastLoginTime( customerEntity.getLastLoginTime() );
        customerRequest.setLastLogoutTime( customerEntity.getLastLogoutTime() );
        List<String> list = customerEntity.getCustomerFilePaths();
        if ( list != null ) {
            customerRequest.setCustomerFilePaths( new ArrayList<String>( list ) );
        }

        return customerRequest;
    }

    @Override
    public CustomerEntity entityToRequest(CustomerRequest request) {
        if ( request == null ) {
            return null;
        }

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setCustomerId( request.getCustomerId() );
        customerEntity.setFirst_name( request.getFirst_name() );
        customerEntity.setLast_name( request.getLast_name() );
        customerEntity.setEmail( request.getEmail() );
        customerEntity.setMobile_no( request.getMobile_no() );
        customerEntity.setPan_no( request.getPan_no() );
        customerEntity.setPassword( request.getPassword() );
        customerEntity.setOtp( request.getOtp() );
        customerEntity.setOtpTime( request.getOtpTime() );
        customerEntity.setCreatedDate( request.getCreatedDate() );
        customerEntity.setLastLoginTime( request.getLastLoginTime() );
        customerEntity.setLastLogoutTime( request.getLastLogoutTime() );
        List<String> list = request.getCustomerFilePaths();
        if ( list != null ) {
            customerEntity.setCustomerFilePaths( new ArrayList<String>( list ) );
        }

        return customerEntity;
    }

    @Override
    public List<CustomerResponse> responseToEntityList(List<CustomerEntity> customerEntity) {
        if ( customerEntity == null ) {
            return null;
        }

        List<CustomerResponse> list = new ArrayList<CustomerResponse>( customerEntity.size() );
        for ( CustomerEntity customerEntity1 : customerEntity ) {
            list.add( responseToEntity( customerEntity1 ) );
        }

        return list;
    }

    @Override
    public CustomerResponse responseToEntity(CustomerEntity customerEntity) {
        if ( customerEntity == null ) {
            return null;
        }

        CustomerResponse customerResponse = new CustomerResponse();

        customerResponse.setCustomerId( customerEntity.getCustomerId() );
        customerResponse.setFirst_name( customerEntity.getFirst_name() );
        customerResponse.setLast_name( customerEntity.getLast_name() );
        customerResponse.setEmail( customerEntity.getEmail() );
        customerResponse.setMobile_no( customerEntity.getMobile_no() );
        customerResponse.setPan_no( customerEntity.getPan_no() );
        customerResponse.setPassword( customerEntity.getPassword() );
        customerResponse.setOtp( customerEntity.getOtp() );
        customerResponse.setOtpTime( customerEntity.getOtpTime() );
        customerResponse.setCreatedDate( customerEntity.getCreatedDate() );
        List<String> list = customerEntity.getCustomerFilePaths();
        if ( list != null ) {
            customerResponse.setCustomerFilePaths( new ArrayList<String>( list ) );
        }
        customerResponse.setLastLogoutTime( customerEntity.getLastLogoutTime() );
        customerResponse.setLastLoginTime( customerEntity.getLastLoginTime() );

        return customerResponse;
    }

    @Override
    public CustomerEntity entityToResponse(CustomerResponse response) {
        if ( response == null ) {
            return null;
        }

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setCustomerId( response.getCustomerId() );
        customerEntity.setFirst_name( response.getFirst_name() );
        customerEntity.setLast_name( response.getLast_name() );
        customerEntity.setEmail( response.getEmail() );
        customerEntity.setMobile_no( response.getMobile_no() );
        customerEntity.setPan_no( response.getPan_no() );
        customerEntity.setPassword( response.getPassword() );
        customerEntity.setOtp( response.getOtp() );
        customerEntity.setOtpTime( response.getOtpTime() );
        customerEntity.setCreatedDate( response.getCreatedDate() );
        customerEntity.setLastLoginTime( response.getLastLoginTime() );
        customerEntity.setLastLogoutTime( response.getLastLogoutTime() );
        List<String> list = response.getCustomerFilePaths();
        if ( list != null ) {
            customerEntity.setCustomerFilePaths( new ArrayList<String>( list ) );
        }

        return customerEntity;
    }
}
