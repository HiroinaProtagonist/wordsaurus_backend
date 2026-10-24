package com.hiroinaprotagonist.wordsaurus.utility;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public class ErrorUtil {
    private ErrorUtil() {}

    public static void handleErrorWithLog(Logger logger, Exception e) {
        logger.error("Error: {}; Error class: {}",
                e.getMessage(),
                e.getClass().getSimpleName());
    }
}
