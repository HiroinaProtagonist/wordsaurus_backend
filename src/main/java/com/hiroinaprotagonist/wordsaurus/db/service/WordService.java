package com.hiroinaprotagonist.wordsaurus.db.service;

import com.hiroinaprotagonist.wordsaurus.db.model.Word;

import java.util.List;

public interface WordService {
    Word findWordByName(String name);

    List<Word> findAllWordsByCloud();

    Word save(Word word);

    Word update(Word word);

    Word deleteByName(String name);

    Word deleteById(Integer id);

}
