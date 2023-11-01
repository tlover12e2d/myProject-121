package org.example.vebinarcrud.notes.dao;


import java.util.ArrayList;
import java.util.List;

import org.example.vebinarcrud.notes.models.WebNote;

import org.springframework.stereotype.Component;

@Component
public class WebNoteDAO {
    private static int NOTES_COUNT;
    private List<WebNote> notes;

    public WebNoteDAO() {
        notes = new ArrayList<WebNote>();

        notes.add(new WebNote(++NOTES_COUNT, "test1")); //Список заметок
        notes.add(new WebNote(++NOTES_COUNT, "test2"));
        notes.add(new WebNote(++NOTES_COUNT, "test3"));
        notes.add(new WebNote(++NOTES_COUNT, "test4"));
        notes.add(new WebNote(++NOTES_COUNT, "test5"));
    }

    public List<WebNote> index() { //Индекс заметки
        return notes;
    }

    public WebNote show(int id) { //Ищет заметку
        return notes.stream().filter(n -> n.getId() == id).findAny().orElse(null);
    }

    public void save(WebNote webNote) { //Сохраняет новую заметку
        webNote.setId(++NOTES_COUNT);
        notes.add(webNote);
    }

    public void update(int id, WebNote updatedNote) { //Обновляет заметку
        WebNote toUpdateNote = show(id);
        toUpdateNote.setNote(updatedNote.getNote());
    }

    public void delete(int id) { //Удаляем заметку
        notes.removeIf(n -> n.getId() == id);
    }
}
