package com.cache.cacheservice.repositories;

import com.cache.cacheservice.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to persist Employee table
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
