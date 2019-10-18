package com.codegym.controller;

import com.codegym.model.TypeNote;
import com.codegym.service.TypeNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TypeNoteControlle {
    @Autowired
    private TypeNoteService typeNoteService;

    @GetMapping("/type/create")
    public ModelAndView CreatePage() {
        return new ModelAndView("type_note/create","type",new TypeNote());
    }
    @PostMapping(value = "/type_note/create/done" , params = "add")
    public ModelAndView createDone(@ModelAttribute("type") TypeNote typeNote) {
        ModelAndView modelAndView = new ModelAndView("type_note/create");

        if (typeNote.getName()!=null) {
            typeNoteService.save(typeNote);
            modelAndView.addObject("message","Add successful");
        }else {
            modelAndView.addObject("message","Add not successful");
        }
        return modelAndView;

    }
    @PostMapping(value = "/type_note/create/done", params = "cancel")
    public String createCancle(RedirectAttributes redirect) {
        return "redirect:/note/list";
    }

    @GetMapping("/type_note/list")
    public String listPage(Model model, @PageableDefault(size = 2) Pageable pageable) {
        model.addAttribute("type", typeNoteService.findAll(pageable));
        return "type_note/list";
    }


    @GetMapping("/type_note/{id}/delete")
    public String deleteNode(@PathVariable Integer id, RedirectAttributes redirect) {
        typeNoteService.delete(id);
        redirect.addFlashAttribute("message","Delete successful!");
        return "redirect:/type_note/list";
    }

//    @GetMapping("/note/{id}/edit")
//    public String editNode(@PathVariable Integer id, Model model) {
//        model.addAttribute("note",typeNoteService.findById(id));
//        return "note/edit";
//    }
//
//    @PostMapping(value = "/note/edit/done", params = "cancel")
//    public String cancelEditNote(RedirectAttributes redirect) {
//        return "redirect:/note/list";
//    }
//
//    @PostMapping(value = "/note/edit/done", params = "add")
//    public String editNoteDone(@ModelAttribute("note") Note note, RedirectAttributes redirect) {
//        noteService.save(note);
//        return "redirect:/note/list";
//    }

}
