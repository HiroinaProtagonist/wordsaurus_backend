package com.hiroinaprotagonist.wordsaurus.unit.rest.controller;

import com.hiroinaprotagonist.wordsaurus.rest.controller.CombinedController;

import com.hiroinaprotagonist.wordsaurus.rest.service.RhymeBrainService;
import com.hiroinaprotagonist.wordsaurus.rest.service.WordService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestClient;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.ExecutionException;

import static com.hiroinaprotagonist.wordsaurus.data.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.*;

@WebMvcTest(CombinedController.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
class CombinedControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RestClient restClient = RestClient.create();

    @MockitoBean
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    @MockitoBean
    private WordService wordService = new WordService(restClient);

    @MockitoBean
    private RhymeBrainService rhymeBrainService = new RhymeBrainService(restClient);

    private AutoCloseable closeable;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        this.mockMvc = MockMvcBuilders.standaloneSetup(new CombinedController(
                        wordService,
                        rhymeBrainService,
                        executorService))
                .build();

        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    void positive_getCombinedResponse() throws Exception {
        mockMvc.perform(get("/api/wordsaurus/{word}", PANTS)
                        .accept(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void negative_getCombinedResponse() throws Exception {
        mockMvc.perform(get("/api/wordsaurus/{word}", "")
                        .accept(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(status().is4xxClientError());
    }

//    @Test
//    void negative_interruptedException_getCombinedResponse() throws Exception {
//        Mockito.when(executorService.submit(Any::new)).thenThrow(new InterruptedException());
//
//        mockMvc.perform(get("/api/wordsaurus/{word}", PANTS)
//                        .accept(String.valueOf(MediaType.APPLICATION_JSON)))
//                .andExpect(sta)
//                .andExpect(content().contentType("application/json"));
//    }

    @Test
    void positive_sample_thread_test() throws ExecutionException, InterruptedException {
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();

        MyCallable c = new MyCallable();
        Future<Long> callable_future = executorService1.submit(c);
        Long test = callable_future.get();
        assertEquals(2305843005992468481L, test);

        MyRunnable r = new MyRunnable();
        Future<?> runnable_future = executorService1.submit(r);
        runnable_future.get();
        assertEquals(2305843005992468481L, r.getResult());

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