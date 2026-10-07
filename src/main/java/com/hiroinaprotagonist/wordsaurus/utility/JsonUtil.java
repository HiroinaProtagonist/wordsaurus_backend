package com.hiroinaprotagonist.wordsaurus.utility;

import lombok.Getter;
import tools.jackson.databind.ObjectMapper;

public class JsonUtil {
    @Getter
    private static final ObjectMapper objectMapper = new ObjectMapper();
}
