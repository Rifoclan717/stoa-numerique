package com.example.stoa_backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.example.stoa_backend.dto.NoteRequestDto;
import com.example.stoa_backend.mapper.NoteMapper;
import com.example.stoa_backend.model.Note;
import com.example.stoa_backend.repositories.NoteRepository;

@Service
public class NoteService {
    
    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    public ArrayList<NoteRequestDto> findAllNotes() {
        return NoteMapper.NoteRequestList(noteRepository.findAll());
    }

    public void createNote(NoteRequestDto noteRequestDto){
        Note note = NoteMapper.DtoToNote(noteRequestDto);
        noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
