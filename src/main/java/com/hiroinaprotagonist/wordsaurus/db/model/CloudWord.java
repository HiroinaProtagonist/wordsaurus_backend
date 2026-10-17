package com.hiroinaprotagonist.wordsaurus.db.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "clouds_words")
public class CloudWord {

    @EmbeddedId
    private CloudWordId cloudWordId;

    @ManyToOne()
    @MapsId("cloudId")
    @JoinColumn(name = "cloud_id")
    private Cloud cloud;

    @ManyToOne()
    @MapsId("wordId")
    @JoinColumn(name = "word_id")
    private Word word;

    private CloudWord(Cloud cloud, Word word) {
        if (cloud == null || cloud.getCloudId() == null ||
             word == null || word.getWordId() == null) {
            throw new IllegalArgumentException ("Requires cloud and word.");
        }

        this.cloud = cloud;
        this.word = word;

        this.cloudWordId = new CloudWordId(cloud.getCloudId(), word.getWordId());
    }
}
