package com.hiroinaprotagonist.wordsaurus.db.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
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
