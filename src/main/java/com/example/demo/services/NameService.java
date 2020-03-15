package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.NameEntity;
import com.example.demo.repositories.NameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NameService {
    @Autowired
    private NameRepository repo;

    public List<NameEntity> getAll() {
        return repo.findAll();
    }
}