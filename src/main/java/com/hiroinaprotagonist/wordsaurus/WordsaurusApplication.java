package com.hiroinaprotagonist.wordsaurus;

import com.hiroinaprotagonist.wordsaurus.rest.controller.WordController;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.CombinedWordResponse;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.MWResult;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.input.response.mwapi.MWThesaurusWord;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.output.response.CloudWord;
import com.hiroinaprotagonist.wordsaurus.rest.model.wordsapi.output.response.CloudWordList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootApplication
@Slf4j
public class WordsaurusApplication implements CommandLineRunner {
	private final WordController wordController;

	public static void main(String[] args) {
		SpringApplication.run(WordsaurusApplication.class, args);
	}

	@Autowired
	public WordsaurusApplication(WordController wordController) {
		this.wordController = wordController;
	}

	public void run(String... args) throws Exception {
		String testWord = "pants";

		ResponseEntity<String> coll_res = wordController
				.getWordDataFromThesaurus(testWord);
//		log.info("Res from word controller: {}", coll_res);

//		ResponseEntity<MWResult> thes_res = wordController
//				.getWordDataFromCollegiate(testWord);
//		log.info("Res from word controller: {}", thes_res);
	}
}
