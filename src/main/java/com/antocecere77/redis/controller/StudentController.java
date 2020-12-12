package com.antocecere77.redis.controller;

import com.antocecere77.redis.entity.Student;
import com.antocecere77.redis.queue.MessagePublisher;
import com.antocecere77.redis.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;

    private final MessagePublisher redisPublisher;

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @GetMapping("/student/name/{name}")
    public ResponseEntity<?> findStudentByName(@PathVariable String name) {
        Student student =  studentRepository.findByName(name);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/student/id/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable String id) {
        Student student =  studentRepository.findById(id).get();
        return ResponseEntity.ok(student);
    }

    @PutMapping("student/{id}/{name}")
    public ResponseEntity<?> updateNameById(@PathVariable String id, @PathVariable String name) {
        Student student = studentRepository.findById(id).get();

        student.setName(name);
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @DeleteMapping("/student/id/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable String id) {
        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @DeleteMapping("student")
    private ResponseEntity<?> deleteAll() {
        studentRepository.findAll().forEach(student -> studentRepository.delete(student));
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/publish")
    public ResponseEntity<?> publish() {
        redisPublisher.publish("Ciao!!!");
        return ResponseEntity.noContent().build();
    }
}
