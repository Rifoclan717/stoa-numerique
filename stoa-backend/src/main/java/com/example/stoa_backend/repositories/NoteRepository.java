package com.example.stoa_backend.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.stoa_backend.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    
    public ArrayList<Note> findAll();
    // Généricité qui permet à save d'être utiliser meme quand c'est un qqun qui hérite de S qui l'apelle  
    public <S extends Note> S save(S note);
    public void deleteById(Long noteId);
}
