package com.chunyi.repository;

import com.chunyi.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/*
 * Created by 黄春怡 on 2017/2/9.
 */
public interface EmployeeRepository extends CrudRepository<Employee,Integer>{

    @Query(value="select e from Employee e where e.phone=?1")
    public Employee findByPhone(String phone);



    @Query("select e from Employee e where e.id in ?1 ")
    public List<Employee> findById(List<Integer> ids);



}
