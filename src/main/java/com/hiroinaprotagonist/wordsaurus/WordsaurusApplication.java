package com.hiroinaprotagonist.wordsaurus;

import com.hiroinaprotagonist.wordsaurus.rest.controller.RhymeBrainController;
import com.hiroinaprotagonist.wordsaurus.rest.controller.WordController;
import com.hiroinaprotagonist.wordsaurus.rest.model.input.mwapi.response.MWThesaurusWord;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.mwapi.response.MWThesRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RhymeBrainOutputRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RhymeBrainOutputResItem;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
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
	private final RhymeBrainController rhymeBrainController;

	public static void main(String[] args) {
		SpringApplication.run(WordsaurusApplication.class, args);
	}

	@Autowired
	public WordsaurusApplication(WordController wordController,
								 RhymeBrainController rhymeBrainController) {
		this.wordController = wordController;
		this.rhymeBrainController = rhymeBrainController;
	}

	@Override
	public void run(String @NonNull ... args) throws Exception {
		String testWord = "fast";

//		ResponseEntity<List<MWThesRes>> thesRes = wordController
//				.getWordDataFromCollegiate(testWord);
//		log.info("Dictionary res from word controller: {}", thesRes);

		ResponseEntity<List<MWThesRes>> collRes = wordController
				.getWordDataFromThesaurus(testWord);
		log.info("Thesaurus res from word controller: {}", collRes);

		ResponseEntity<List<RhymeBrainOutputRes>> rbRes = rhymeBrainController
				.getRhymesFromRB(testWord);
		log.info("Rhymes returned from RhymeBrain controller: {}", rbRes);
	}
}
