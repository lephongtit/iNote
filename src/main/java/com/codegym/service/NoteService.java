package com.codegym.service;

import com.codegym.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoteService {
    void save (Note note);
    void delete(Integer id);
    List<Note> serchNote(String keyword);
    Page<Note> findAll(Pageable pageable);
    Note findById(Integer id);

//    void writeJSON();
//
//
//    void importJSON();

}
