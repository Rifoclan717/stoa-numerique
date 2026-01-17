package com.example.stoa_backend.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.stoa_backend.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    
    public ArrayList<Note> findAll();
    // Généricité qui permet à save d'être utiliser meme quand c'est un qqun qui hérite de S qui l'apelle  
    public <S extends Note> S save(S note);
    public void deleteById(Long noteId);

    @Modifying
    @Transactional
    @Query("UPDATE Note n SET n.contenu  = :contenu , n.titre = :titre WHERE n.id = :id ")
    public void updateById(@Param("id") Long id, @Param("contenu")String contenu, @Param("titre") String titre);
}
