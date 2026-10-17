package com.hiroinaprotagonist.wordsaurus.db.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wordId;

    private String name;

    private Boolean explicit;

    @OneToMany(mappedBy = "word", cascade = {CascadeType.MERGE})
    private Set<CloudWord> cloudWords;

}
