package com.hiroinaprotagonist.wordsaurus.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CloudWordId implements Serializable {
    @Column(name = "cloud_id")
    private UUID cloudId;

    @Column(name = "word_id")
    private Integer wordId;
}
