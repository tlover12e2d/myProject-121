package org.example.vebinarcrud.notes.controllers;

import org.example.vebinarcrud.notes.dao.WebNoteDAO;
import org.example.vebinarcrud.notes.models.WebNote;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private final WebNoteDAO webNoteDAO;//Экзепляр класса

    @Autowired
    public NotesController(WebNoteDAO webNoteDAO) {//Инициализируем его в конструкторе
        this.webNoteDAO = webNoteDAO;
    }

    @GetMapping() //Возвращает индекс данной страницы
    public String index(Model model) {
        model.addAttribute("notes", webNoteDAO.index());
        return "notes/index";
    }

    @GetMapping("/{id}") //Передаём id и модель и получаем данные
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("note", webNoteDAO.show(id));
        return "notes/show";
    }

    @GetMapping("/new") //Создаём новую заметку
    public String newNote(Model model) {
        model.addAttribute("webNote", new WebNote());
        return "notes/new";
    }

    @PostMapping() //Добавляем новую заметку
    public String create(@ModelAttribute("webNote") WebNote webNote) {
        webNoteDAO.save(webNote);
        return "redirect:/notes";
    }

    @GetMapping("/{id}/edit") //Изменение заметки
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("webNote", webNoteDAO.show(id));
        return "notes/edit";
    }

    @PatchMapping("/{id}") //Обновление заметки
    public String update(@ModelAttribute("webNote") WebNote webNote, @PathVariable("id") int id) {
        webNoteDAO.update(id, webNote);
        return "redirect:/notes";
    }

    @DeleteMapping("/{id}")//Удаление заметки
    public String delete(@PathVariable("id") int id) {
        webNoteDAO.delete(id);
        return "redirect:/notes";
    }

}
