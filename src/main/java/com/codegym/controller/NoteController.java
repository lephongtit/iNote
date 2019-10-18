package com.codegym.controller;

import com.codegym.model.Note;
import com.codegym.model.TypeNote;
import com.codegym.service.NoteService;
import com.codegym.service.TypeNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private TypeNoteService typeNoteService;
    @ModelAttribute("typeNotes")
    public Iterable<TypeNote> typeNotes(){return typeNoteService.findAll();}

    @GetMapping("/note/create")
    public ModelAndView PageCreate(){
        ModelAndView modelAndView = new ModelAndView("note/create","note",new Note());
        return modelAndView;

    }
    @PostMapping(value = "/note/create/done",params = "add")
    public String saveNote(@ModelAttribute("note") Note note, RedirectAttributes redirect) {
        if (!note.getContent().equals("")) {
            noteService.save(note);
            redirect.addFlashAttribute("message","Add success");
        }
        else {
            redirect.addFlashAttribute("message","Add false");
        }
        return "redirect:/note/create";
    }
    @PostMapping(value = "/note/create/done", params = "cancel")
    public String cancelSaveNote(RedirectAttributes redirect) {
        return "redirect:/note/list";
    }

    @GetMapping("/note/list")
    public String listPage(Model model, @PageableDefault(size = 2) Pageable pageable) {
        model.addAttribute("notes", noteService.findAll(pageable));
        return "note/list";
    }

    @GetMapping("/note/{id}/delete")
    public String deleteNode(@PathVariable Integer id, RedirectAttributes redirect) {
        noteService.delete(id);
        redirect.addFlashAttribute("message","Delete successful!");
        return "redirect:/note/list";
    }
    @GetMapping("/note/{id}/edit")
    public String editNode(@PathVariable Integer id, Model model) {
        model.addAttribute("note",noteService.findById(id));
        return "note/edit";
    }

    @PostMapping(value = "/note/edit/done", params = "cancel")
    public String cancelEditNote(RedirectAttributes redirect) {
        return "redirect:/note/list";
    }

    @PostMapping(value = "/note/edit/done", params = "add")
    public String editNoteDone(@ModelAttribute("note") Note note, RedirectAttributes redirect) {
        noteService.save(note);
        return "redirect:/note/list";
    }
    @PostMapping("/note/search")
    public ModelAndView searchNote(@RequestParam("strTitle") String title, @RequestParam("typeValue") TypeNote note, @PageableDefault(size = 2) Pageable pageable ) {
        List<Note> notes= noteService.serchNote(title);
        List<Note> list = new ArrayList<>();
        for(Note n: notes){
            if (n.getTypeNote().getId() == note.getId()){
                list.add(n);
            }

        }
        Page<Note> pageList = new PageImpl<>(list,pageable,list.size());
        ModelAndView modelAndView = new ModelAndView("note/list", "notes" , pageList);
        return modelAndView;
    }
//    @GetMapping("/note/writeJSON")
//    public ModelAndView writeJSON(){
//
//        noteService.writeJSON();
//        ModelAndView modelAndView = new ModelAndView("redirect:/notes");
//        modelAndView.addObject("message","Export successful");
//        return modelAndView;
//    }
//
//    @GetMapping("/note/importJSON")
//    public ModelAndView importJSON(){
//        noteService.importJSON();
//        ModelAndView modelAndView = new ModelAndView("redirect:/notes");
//        modelAndView.addObject("message","Import successful");
//        return modelAndView;
//    }
}
