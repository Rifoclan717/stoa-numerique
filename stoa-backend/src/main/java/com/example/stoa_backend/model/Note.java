package com.example.stoa_backend.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notes")
public class Note {

    @Id //obligatoire en JPA pour une entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // On laisse la db s'occuper de l'id de l'objet
    private long id;
    @Lob
    private String contenu;

    @CreatedDate //  fAIT parti de Spring data JPA pour faire en sorte que Hibernate remplisse automatiquement ce champ
    @Column(updatable = false) // champs jamais modifié
    private LocalDateTime createdAt;


    @LastModifiedDate // Hibernate s'occupe aussi de ca
    private LocalDateTime updatedAt;

    private String titre;

    
    public Long getId() {
        return id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


    
}
