package com.hiroinaprotagonist.wordsaurus.unit.rest.service;

import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBOutputRhymeRes;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBShortOutputRhymeResItem;
import com.hiroinaprotagonist.wordsaurus.rest.service.RhymeBrainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.test.autoconfigure.RestClientTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.hiroinaprotagonist.wordsaurus.data.TestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

//@RunWith(SpringRunner.class)
//@RestClientTest(RhymeBrainService.class)
class RhymeBrainServiceTest {

    @Mock
    private MockRestServiceServer mockServer;

    @Mock
    private RestClient restClient = RestClient.create();

    @Mock
    private ExecutorService executorService = Executors.newCachedThreadPool();

    @InjectMocks
    private RhymeBrainService rhymeBrainService;

    @BeforeEach
    void setup() {
        rhymeBrainService = new RhymeBrainService(restClient, executorService);
    }

    @Test
    void positive_getRhymes() {
        //given
        String word = OBFUSCATE;

        //when
        mockServer.expect(requestTo(""))
                .andRespond(withSuccess(getMWJson("obfuscate"),
                        MediaType.APPLICATION_JSON));

//        when(restClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .scheme("https")
//                        .host("rhymebrain.com")
//                        .path("talk")
//                        .queryParam("function", "getRhymes")
//                        .queryParam("word", word)
//                        .build())
//                .headers(headers -> {})
//                .retrieve()
//                .toEntity(new ParameterizedTypeReference<>(){}))
//                .thenReturn(ResponseEntity.ok(getRBInputRhymeResItemList()));

        List<RBOutputRhymeRes> output = rhymeBrainService.getRhymes(word);

        //then
        assertEquals(output, getRBOutputRhymeResList());
    }

    @Test
    void positive_processRhymeWordIntoMap() {
        //given
        RBShortOutputRhymeResItem item1 = getRBShortOutputRhymeResItem();
        item1.setWord(PANTS);
        RBShortOutputRhymeResItem item2 = getRBShortOutputRhymeResItem();
        item2.setWord(OBFUSCATE);
        RBShortOutputRhymeResItem item3 = getRBShortOutputRhymeResItem();
        item3.setWord(HOP);
        item3.setScore(MID_HIGH_SCORE);

        Map<Integer, List<String>> map = new HashMap<>();

        //when
        rhymeBrainService.processRhymeWordIntoMap(map, item1);
        //then
        assertEquals(map.get(HIGH_SCORE), List.of(PANTS));
        assertNull(map.get(MID_HIGH_SCORE));

        //when
        rhymeBrainService.processRhymeWordIntoMap(map, item2);
        //then
        assertEquals(map.get(HIGH_SCORE), List.of(PANTS, OBFUSCATE));
        assertNull(map.get(MID_HIGH_SCORE));

        //when
        rhymeBrainService.processRhymeWordIntoMap(map, item3);
        //then
        assertEquals(map.get(HIGH_SCORE), List.of(PANTS, OBFUSCATE));
        assertEquals(map.get(MID_HIGH_SCORE), List.of(HOP));
    }

}
