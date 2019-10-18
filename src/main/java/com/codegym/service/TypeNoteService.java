package com.codegym.service;

import com.codegym.model.TypeNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeNoteService {
    void save(TypeNote typeNote);
    void delete(Integer id);
    Iterable<TypeNote> findAll();
    Page<TypeNote> findAll(Pageable pageable);

}
