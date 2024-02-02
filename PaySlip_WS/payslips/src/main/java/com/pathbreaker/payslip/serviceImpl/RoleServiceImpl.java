package com.pathbreaker.payslip.serviceImpl;

import com.pathbreaker.payslip.entity.Role;
import com.pathbreaker.payslip.exception.Exceptions;
import com.pathbreaker.payslip.exception.NotFoundException;
import com.pathbreaker.payslip.mappers.RolesMapper;
import com.pathbreaker.payslip.repository.RoleRepository;
import com.pathbreaker.payslip.request.RolesRequest;
import com.pathbreaker.payslip.response.ResultResponse;
import com.pathbreaker.payslip.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleServiceImpl(RolesMapper rolesMapper,
                            RoleRepository roleRepository){
        this.rolesMapper  = rolesMapper;
        this.roleRepository =roleRepository;
    }
    private final RolesMapper rolesMapper;
    private final RoleRepository roleRepository;
    @Override
    public ResponseEntity<?> createRole(RolesRequest rolesRequest) {

        try {
            Role roleEntity = rolesMapper.entityToRequest(rolesRequest);
            roleRepository.save(roleEntity);

            ResultResponse result = new ResultResponse();
            log.info("Role added successfully...");
            result.setResult("Role added successfully...");
            return ResponseEntity.ok(result);
        }
        catch (Exceptions ex){
            log.warn("An error occured while adding Role");
            throw new NotFoundException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while adding Role");
        }

    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roleEntities = roleRepository.findAll();
        return roleEntities;
    }

    @Override
    public Optional<Role> getById(int id) {
        try {
            Optional<Role> roleEntity= roleRepository.findById(id);
            if (roleEntity.isPresent()){
                return roleEntity;
            }
            else {
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The Role with " + id + " not found");
            }
        } catch (Exceptions ex) {
            // Handle other exceptions
            log.error("An error occurred while retrieving Role by ID: " + id, ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR,"An error occurred while retrieving Role by ID: " + id);
        }
    }

    @Override
    public ResponseEntity<?> updateById(int id, RolesRequest rolesRequest) {
        try {
            Optional<Role> roleEntity = roleRepository.findById(id);

            if (roleEntity.isPresent()) {
                Role entity = roleEntity.get();
                // Update the department title
                entity.setRole(rolesRequest.getRole());
                // Save the updated entity
                roleRepository.save(entity);

                ResultResponse result = new ResultResponse();
                log.info("Role Updated successfully...");
                result.setResult("Role Updated successfully...");
                return ResponseEntity.ok(result);
            } else {
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The Role with " + id + " not found");
            }
        }catch (Exceptions ex) {
            // Handle other exceptions
            log.error("An error occurred while updating Role by ID: " + id, ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR,"An error occurred while updating Role by ID: " + id);
        }

    }
    @Override
    public ResponseEntity<?> deleteById(int id) {
        try {
            Optional<Role> roleEntity = roleRepository.findById(id);
            if (roleEntity.isPresent()) {
                Role entity = roleEntity.get();
                roleRepository.delete(entity);

                ResultResponse result = new ResultResponse();
                log.info("Role deleted successfully...");
                result.setResult("Role deleted successfully...");
                return ResponseEntity.ok(result);
            } else {
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The Role with " + id + " not found");
            }
        } catch (Exceptions ex) {
            // Handle other exceptions
            log.error("An error occurred while Deleting Role by ID: " + id, ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while deleting Role by ID: " + id);
        }
    }
}
