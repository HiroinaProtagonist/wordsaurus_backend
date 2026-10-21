package com.hiroinaprotagonist.wordsaurus.db.repo;

import com.hiroinaprotagonist.wordsaurus.db.model.Word;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WordRepository extends CrudRepository<Word, Integer> {
    Word findWordByName(String name);

    List<Word> findAllWordsByCloud();

//    Word save(Word word);

}
