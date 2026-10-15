package com.hiroinaprotagonist.wordsaurus.unit.rest.controller;

import com.hiroinaprotagonist.wordsaurus.rest.controller.CombinedController;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.CombinedWordResponse;

import com.hiroinaprotagonist.wordsaurus.rest.service.RhymeBrainService;
import com.hiroinaprotagonist.wordsaurus.rest.service.WordService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestClient;

import java.util.concurrent.ExecutionException;

import static com.hiroinaprotagonist.wordsaurus.data.TestData.getMWOutputThesResList;
import static com.hiroinaprotagonist.wordsaurus.data.TestData.getRBRhymeServiceOutput;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.*;

@WebMvcTest(CombinedController.class)
class CombinedControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Mock
//    private RestClient restClient = RestClient.create();
//
//    @Mock
//    private ExecutorService executorService;
//
//    @MockitoBean
//    private WordService wordService = new WordService(restClient);
//
//    @MockitoBean
//    private RhymeBrainService rhymeBrainService = new RhymeBrainService(restClient);

//    private CombinedController combinedController;

    private AutoCloseable closeable;

    @BeforeEach
    public void setup() {
//        combinedController = new CombinedController(
//                wordService,
//                rhymeBrainService,
//                executorService);
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    void positive_getCombinedResponse() throws Exception {

        //given
        String word = "test";


        //when
        when(wordService.getWordFromThesaurus(anyString()))
                .thenReturn(getMWOutputThesResList());

        when(rhymeBrainService.getRhymes(anyString()))
                .thenReturn(getRBRhymeServiceOutput());

//        ResponseEntity<CombinedWordResponse> res =
//                combinedController.getCombinedResponse("test");

        mockMvc.perform(get("/api/wordsaurus/{word}", word)
                .accept(String.valueOf(MediaType.APPLICATION_JSON)));
//                        .andExpect(status().isOk()));



        //then
//        assertTrue(res.getStatusCode().is2xxSuccessful());
    }

    @Test
    void positive_sample_thread_test() throws ExecutionException, InterruptedException {
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        MyCallable c = new MyCallable();

//        executorService1.submit(c);
//        assertTrue(c.getRes() > 0L);

//        when(executorService1.submit(c))
//                .thenReturn(2305843005992468481L);

        Future<Long> future = executorService1.submit(c);
        Long test = future.get();
        assertEquals(2305843005992468481L, test);

//        MyRunnable r = new MyRunnable();
//        Future<?> future = executorService1.submit(r);
//        future.get();
//        assertEquals(2305843005992468481L, r.getResult());

    }

    public class MyRunnable implements Runnable {
        Long result;

        public Long getResult() {
            return result;
        }

        public void setResult(Long result) {
            this.result = result;
        }

        @Override
        public void run() {
            result = sum();
        }

        private Long sum() {
            Long result = 0L;
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                result += i;
            }
            return result;
        }
    }

    public class MyCallable implements Callable {
        Long res = 0L;

        public Long getRes() {
            return res;
        }

        public void setRes(Long val) {
            res = val;
        }

        @Override
        public Long call() {
            return res + sum();
        }

        private Long sum() {
            Long res = 0L;
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                res += i;
            }
            return res;
        }
    }

}