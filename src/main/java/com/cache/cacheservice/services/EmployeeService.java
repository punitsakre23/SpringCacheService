package com.cache.cacheservice.services;

import com.cache.cacheservice.entities.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    Employee getEmployeeById(Long id);

    void deleteEmployeeById(Long id);

    String viewCacheContents(String cache);
}
