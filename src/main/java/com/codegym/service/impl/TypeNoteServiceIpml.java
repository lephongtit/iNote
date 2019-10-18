package com.codegym.service.impl;

import com.codegym.model.TypeNote;
import com.codegym.repository.TypeNoteRepository;
import com.codegym.service.TypeNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class TypeNoteServiceIpml implements TypeNoteService {
    @Autowired
    private TypeNoteRepository typeNoteRepository;
    @Override
    public void save(TypeNote typeNote) {
        typeNoteRepository.save(typeNote);
    }

    @Override
    public void delete(Integer id) {
        typeNoteRepository.delete(id);
    }

    @Override
    public Iterable<TypeNote> findAll() {
        return typeNoteRepository.findAll();
    }

    @Override
    public Page<TypeNote> findAll(Pageable pageable) {
        return typeNoteRepository.findAll(pageable);
    }
}
