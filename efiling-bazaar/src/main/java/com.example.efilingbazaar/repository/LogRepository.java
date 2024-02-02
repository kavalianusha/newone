
package com.example.efilingbazaar.repository;

import java.security.Timestamp;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.efilingbazaar.entity.LogEntity;
import com.example.efilingbazaar.request.LogRequest;


@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long>{

	Optional<LogEntity> findByEmailId(String emailId);

	LogEntity findFirstByEmployeeIdAndSessionLastLogoutTime(String employeeId, Timestamp invalidTimestamp);

	Optional<LogEntity> findByEmailIdAndSessionLastLogoutTime(String emailId, Timestamp invalidTimestamp);
	@Query(value = "select * from log_entity l " +
			"where l.employee_id  = :employeeId and DATE_FORMAT(l.session_last_login_time,'%Y-%m-%d %H:%i:%s')= :lastLoginTime",

			nativeQuery = true)
	LogEntity findByEmployee(@Param("employeeId") String employeeId, @Param("lastLoginTime")String lastLoginTime);

	List<LogEntity> findByEmployeeId(@Param("employeeId") String employeeId);

	LogEntity save(LogRequest logEntry);

}