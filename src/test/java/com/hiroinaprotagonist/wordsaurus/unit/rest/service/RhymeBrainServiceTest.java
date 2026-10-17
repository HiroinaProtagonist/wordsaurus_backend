package com.hiroinaprotagonist.wordsaurus.unit.rest.service;

import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBOutputRhymeRes;
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

import java.util.List;

import static com.hiroinaprotagonist.wordsaurus.data.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(RhymeBrainService.class)
class RhymeBrainServiceTest {

    @Mock
    private MockRestServiceServer mockServer;

    @Mock
    private RestClient restClient = RestClient.create();

    @InjectMocks
    private RhymeBrainService rhymeBrainService;

    @BeforeEach
    void setup() {
        rhymeBrainService = new RhymeBrainService(restClient);
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

}
