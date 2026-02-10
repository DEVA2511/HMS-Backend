package com.hms.user.Repository;

import com.hms.user.DTO.MonthlyRoleCount;
import com.hms.user.DTO.Roles;
import com.hms.user.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    @Query("SELECT new com.hms.user.DTO.MonthlyRoleCount(CAST(FUNCTION('MONTHNAME', u.createdAt) AS string), COUNT(u)) " +
            "FROM User u " +
            "WHERE u.role = :role AND YEAR(u.createdAt) = YEAR(CURRENT_DATE) " +
            "GROUP BY FUNCTION('MONTH', u.createdAt), CAST(FUNCTION('MONTHNAME', u.createdAt) AS string) " +
            "ORDER BY FUNCTION('MONTH', u.createdAt)")
    List<MonthlyRoleCount> countRegistrationsByGroupedByMonth(Roles role);
}
