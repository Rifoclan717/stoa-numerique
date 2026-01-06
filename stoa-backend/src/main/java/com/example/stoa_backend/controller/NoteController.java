package com.example.stoa_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.stoa_backend.dto.NoteRequestDto;
import com.example.stoa_backend.model.Note;
import com.example.stoa_backend.service.NoteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class NoteController {
    
    private NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    // @PostMapping("/note")
    // public String createNote(@RequestBody String contenu) {
        
        
    // }

    @GetMapping("/all")
    public ArrayList<NoteRequestDto> getAllNotes() {
        return noteService.findAllNotes();
    }
    
    @PostMapping("/add")              // Ici je peux mettre le DTO direct car Jackson fera le mapping
    public void createNote(@RequestBody NoteRequestDto noteRequestDto) {
        noteService.createNote(noteRequestDto);;
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable(required = true) Long id) {
        noteService.deleteNote(id);
    }
    
    
    
}
