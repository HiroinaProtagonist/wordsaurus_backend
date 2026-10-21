package com.hiroinaprotagonist.wordsaurus.db.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "clouds")
public class Cloud {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cloudId;

    private String title;

    private Boolean explicit;

    @OneToMany(mappedBy = "cloud", cascade = {CascadeType.MERGE})
    private Set<CloudWord> cloudWords;
}
