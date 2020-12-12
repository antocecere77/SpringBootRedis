package com.antocecere77.redis.repository;

import com.antocecere77.redis.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

    Student findByName(String name);
}
