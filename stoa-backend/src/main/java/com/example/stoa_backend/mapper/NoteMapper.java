package com.example.stoa_backend.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.stoa_backend.dto.NoteRequestDto;
import com.example.stoa_backend.model.Note;

public class NoteMapper {

    public static NoteRequestDto NoteToDto(Note entity){
        NoteRequestDto noteRequestDto = new NoteRequestDto();
        noteRequestDto.setTitre(entity.getTitre());
        noteRequestDto.setContenu(entity.getContenu());
        return noteRequestDto;
    }

    public static ArrayList<NoteRequestDto> NoteRequestList( ArrayList<Note> noteList){
        ArrayList<NoteRequestDto> listNoteRequestDtos = new ArrayList<NoteRequestDto>();
        noteList.forEach(note -> {
            listNoteRequestDtos.add(NoteToDto(note));
        });

        return listNoteRequestDtos;

    }

    public static Note DtoToNote(NoteRequestDto noteRequestDto){
        Note note = new Note();
        note.setTitre(noteRequestDto.getTitre());
        note.setContenu(noteRequestDto.getContenu());

        return note;
    }
}
