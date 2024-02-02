// Import necessary classes and annotations
package com.pathbreaker.hostinghub.mappers;

import com.pathbreaker.hostinghub.entity.HostingEntity;
import com.pathbreaker.hostinghub.entity.PasswordsEntity;
import com.pathbreaker.hostinghub.request.HostingRequest;
import com.pathbreaker.hostinghub.request.HostingUpdateRequest;
import com.pathbreaker.hostinghub.response.HostingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

// Define this interface as a MapStruct Mapper
@Mapper(componentModel = "spring")
public interface HostingMapper {

        @Mappings({
                @Mapping(target = "hostingId", source = "reqDto.hostingId"),
                @Mapping(target = "hostingProvider", source = "reqDto.hostingProvider"),
                @Mapping(target = "url1", source = "reqDto.url1"),
                @Mapping(target = "url2", source = "reqDto.url2"),
                @Mapping(target = "url3", source = "reqDto.url3"),
                @Mapping(target = "duration", source = "reqDto.duration"),
                @Mapping(target = "password", source = "reqDto.password"),
                @Mapping(target = "emailId", source = "reqDto.emailId"),
                @Mapping(target = "registrationPhoneNumber", source = "reqDto.registrationPhoneNumber"),
                @Mapping(target = "registrationDomain", source = "reqDto.registrationDomain"),
                @Mapping(target = "registrationDate", source = "reqDto.registrationDate"),
                @Mapping(target = "expiryDate", source = "reqDto.expiryDate"),
                @Mapping(target = "userName", source = "reqDto.userName"),
                @Mapping(target = "ns1", source = "reqDto.ns1"),
                @Mapping(target = "ns2", source = "reqDto.ns2"),
                @Mapping(target = "hostingDnsName", source = "reqDto.hostingDnsName")
        })
        HostingEntity entityToRequest(HostingRequest reqDto);

        @Mappings({
                @Mapping(target = "hostingProvider", source = "hostingEntity.hostingProvider"),
                @Mapping(target = "url1", source = "hostingEntity.url1"),
                @Mapping(target = "url2", source = "hostingEntity.url2"),
                @Mapping(target = "url3", source = "hostingEntity.url3"),
                @Mapping(target = "duration", source = "hostingEntity.duration"),
                @Mapping(target = "password", source = "hostingEntity.password"),
                @Mapping(target = "emailId", source = "hostingEntity.emailId"),
                @Mapping(target = "registrationPhoneNumber", source = "hostingEntity.registrationPhoneNumber"),
                @Mapping(target = "registrationDomain", source = "hostingEntity.registrationDomain"),
                @Mapping(target = "registrationDate", source = "hostingEntity.registrationDate"),
                @Mapping(target = "expiryDate", source = "hostingEntity.expiryDate"),
                @Mapping(target = "userName", source = "hostingEntity.userName"),
                @Mapping(target = "hostingDnsName", source = "hostingEntity.hostingDnsName"),
                @Mapping(target = "ns1", source = "hostingEntity.ns1"),
                @Mapping(target = "ns2", source = "hostingEntity.ns2"),
                @Mapping(target = "passwordsResponse", source = "hostingEntity.passwordsEntity")
        })
        HostingResponse requestToEntity(HostingEntity hostingEntity);

        /*  private String hostingProvider;
        private String url1;
        private String url2;
        private String url3;
        private String duration;
        private String password;
        private String emailId;
        private String registrationPhoneNumber;
        private String registrationDomain;
        private String registrationDate; // Holds the date of domain registration
        private String expiryDate;
        private String userName;
        private String hostingDnsName;
        private String ns1;
        private String ns2;
        private Long daysLeft;*/

        @Mappings({
                @Mapping(target = "hostingProvider", source = "host.hostingProvider"),
                @Mapping(target = "url1", source = "host.url1"),
                @Mapping(target = "url2", source = "host.url2"),
                @Mapping(target = "url3", source = "host.url3"),
                @Mapping(target = "duration", source = "host.duration"),
                @Mapping(target = "password", source = "host.password"),
                @Mapping(target = "emailId", source = "host.emailId"),
                @Mapping(target = "registrationPhoneNumber", source = "host.registrationPhoneNumber"),
                @Mapping(target = "registrationDomain", source = "host.registrationDomain"),
                @Mapping(target = "registrationDate", source = "host.registrationDate"),
                @Mapping(target = "expiryDate", source = "host.expiryDate"),
                @Mapping(target = "userName", source = "host.userName"),
                @Mapping(target = "hostingDnsName", source = "host.hostingDnsName"),
                @Mapping(target = "ns1", source = "host.ns1"),
                @Mapping(target = "ns2", source = "host.ns2"),
                @Mapping(target = "passwordsResponse", source = "host.passwordsEntity")
        })
        HostingResponse responseToEntity(HostingEntity host);

        @Mappings({
                @Mapping(target = "hostingProvider", source = "hostingUpdateRequest.hostingProvider"),
                @Mapping(target = "url1", source = "hostingUpdateRequest.url1"),
                @Mapping(target = "url2", source = "hostingUpdateRequest.url2"),
                @Mapping(target = "url3", source = "hostingUpdateRequest.url3"),
                @Mapping(target = "duration", source = "hostingUpdateRequest.duration"),
                @Mapping(target = "password", source = "hostingUpdateRequest.password"),
                @Mapping(target = "emailId", source = "hostingUpdateRequest.emailId"),
                @Mapping(target = "registrationPhoneNumber", source = "hostingUpdateRequest.registrationPhoneNumber"),
                @Mapping(target = "registrationDomain", source = "hostingUpdateRequest.registrationDomain"),
                @Mapping(target = "registrationDate", source = "hostingUpdateRequest.registrationDate"),
                @Mapping(target = "expiryDate", source = "hostingUpdateRequest.expiryDate"),
                @Mapping(target = "userName", source = "hostingUpdateRequest.userName"),
                @Mapping(target = "hostingDnsName", source = "hostingUpdateRequest.hostingDnsName"),
                @Mapping(target = "daysLeft", source = "hostingUpdateRequest.daysLeft"),
                @Mapping(target = "ns1", source = "hostingUpdateRequest.ns1"),
                @Mapping(target = "ns2", source = "hostingUpdateRequest.ns2"),
                @Mapping(target = "passwordsEntity", source = "hostingUpdateRequest.passwordsEntity")
        })
        HostingEntity updateEntityFromRequest(HostingEntity hostingEntity, HostingUpdateRequest hostingUpdateRequest);
        PasswordsEntity requestToUpdatePasswordEntity(HostingUpdateRequest hostingUpdateRequest);
        PasswordsEntity requestToPasswordEntity(HostingRequest request);
}
