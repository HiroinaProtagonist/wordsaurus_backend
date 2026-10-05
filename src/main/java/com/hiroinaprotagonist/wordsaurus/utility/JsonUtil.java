package com.hiroinaprotagonist.wordsaurus.utility;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import tools.jackson.databind.ObjectMapper;

public class JsonUtil {
    @Getter
    private static final ObjectMapper objectMapper = new ObjectMapper();
}
