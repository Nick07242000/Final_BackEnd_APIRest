package com.example.test.controller;

import com.example.test.exceptions.BadRequestException;
import com.example.test.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CRUDController<T> {
    ResponseEntity<List<T>> getAll();
    ResponseEntity<T> getById(Long id) throws BadRequestException,ResourceNotFoundException;
    ResponseEntity<T> save(T t) throws BadRequestException,ResourceNotFoundException;
    ResponseEntity<String> update(T t) throws BadRequestException,ResourceNotFoundException;
    ResponseEntity<String> delete(Long id) throws BadRequestException,ResourceNotFoundException;
}
