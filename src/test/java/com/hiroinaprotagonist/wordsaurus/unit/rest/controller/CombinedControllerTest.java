package com.hiroinaprotagonist.wordsaurus.unit.rest.controller;

import com.hiroinaprotagonist.wordsaurus.rest.controller.CombinedController;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.CombinedWordResponse;
import com.hiroinaprotagonist.wordsaurus.rest.model.output.rhymebrainapi.response.RBOutputRhymeRes;
import com.hiroinaprotagonist.wordsaurus.rest.service.RhymeBrainService;
import com.hiroinaprotagonist.wordsaurus.rest.service.WordService;
import lombok.Data;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static com.hiroinaprotagonist.wordsaurus.data.TestData.*;
import static java.lang.Long.sum;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.concurrent.*;

//@ExtendWith(MockitoExtension.class)
class CombinedControllerTest {

    @Mock
    private RestClient restClient = RestClient.create();

    @Mock
    private ExecutorService executorService;

    @Spy
    private WordService wordService = new WordService(restClient);

    @Spy
    private RhymeBrainService rhymeBrainService = new RhymeBrainService(restClient);

    private CombinedController combinedController;

    private AutoCloseable closeable;

    @BeforeEach
    public void setup() {
        combinedController = new CombinedController(wordService, rhymeBrainService);
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    void positive_getCombinedResponse() throws ExecutionException, InterruptedException {
        String word = "test";

        //given
        ResponseEntity<CombinedWordResponse> res =
                combinedController.getCombinedResponse("test");

        //when
        when(executorService.submit())
                .thenReturn();

        Future<> rhymeFuture = executorService.submit();

        when(wordService.getWordFromThesaurus(anyString()))
                .thenReturn(getMWOutputThesResList());

        when(rhymeBrainService.getRhymes(anyString()))
                .thenReturn(getRBRhymeServiceOutput());

        //then
        assertTrue(res.getStatusCode().is2xxSuccessful());

    }

    @Test
    void positive_sample_thread_test() throws ExecutionException, InterruptedException {
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        MyCallable c = new MyCallable();

//        executorService1.submit(c);
//        assertTrue(c.getRes() > 0L);

//        when(executorService1.submit(c))
//                .thenReturn(2305843005992468481L);

//        Future<Long> future = executorService1.submit(c);
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