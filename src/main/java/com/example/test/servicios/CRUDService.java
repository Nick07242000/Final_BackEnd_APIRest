package com.example.test.servicios;

import com.example.test.exceptions.BadRequestException;
import com.example.test.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CRUDService<T> {
    List<T> getAll();
    T getById(Long id) throws BadRequestException,ResourceNotFoundException;
    T save(T t) throws BadRequestException,ResourceNotFoundException;
    String delete(Long id) throws BadRequestException,ResourceNotFoundException;
    String update(T t) throws BadRequestException,ResourceNotFoundException;
}
