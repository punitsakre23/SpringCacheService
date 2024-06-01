package com.cache.cacheservice.services.impl;

import com.cache.cacheservice.entities.Employee;
import com.cache.cacheservice.repositories.EmployeeRepository;
import com.cache.cacheservice.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CacheManager cacheManager;

    /**
     * Create an Employee
     *
     * @param employee entity
     * @return Employee
     */
    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Update Employee details
     *
     * @param employee entity
     * @return Employee
     */
    @Override
    @CachePut(value = "employee", key = "#employee.id")
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Get Employee by id
     *
     * @param id employee id
     * @return Employee
     */
    @Override
    @Cacheable(value = "employee", key = "#id")
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseGet(Employee::new);
    }

    /**
     * Delete Employee By id
     *
     * @param id employee id
     */
    @Override
    @CacheEvict(value = "employee", key = "#id")
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    /**
     * View Cache Contents
     * @param cacheName Cache
     */
    @Override
    public String viewCacheContents(String cacheName) {
        var cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            return "Cache Name: " + cacheName + "\n" + "Cache Contents: " + cache.getNativeCache();
        } else {
            return "Cache '" + cacheName + "' not found.";
        }
    }
}
