package com.haw.srs.loadtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessingController {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessingController.class);

    @RequestMapping("/process")
    public ProcessingStatus blockingProcessing(
            @RequestParam(value = "minMs", required = false, defaultValue = "0") int minMs,
            @RequestParam(value = "maxMs", required = false, defaultValue = "0") int maxMs) {

        int processingTimeMs = calculateProcessingTime(minMs, maxMs);

        LOG.debug("Start request, processing time: {} ms.", processingTimeMs);

        try {
            Thread.sleep(processingTimeMs);
        }
        catch (InterruptedException ignored) {}

        finally {
            LOG.debug("Processing of request done");
        }

        return new ProcessingStatus("Ok", processingTimeMs);
    }

    private int calculateProcessingTime(int minMs, int maxMs) {
        if (maxMs < minMs) maxMs = minMs;
        return minMs + (int) (Math.random() * (maxMs - minMs));
    }

}