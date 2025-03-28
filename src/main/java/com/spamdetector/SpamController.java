package com.spamdetector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/spam")
public class SpamController {

    private final SpamClassifier spamClassifierService;

    @Autowired
    public SpamController(SpamClassifier spamClassifierService) {
        this.spamClassifierService = spamClassifierService;
    }

    @PostMapping("/detect")
    public String detectSpam(@RequestBody String emailText) throws Exception {
        boolean isSpam = spamClassifierService.classify(emailText);
        return isSpam ? "Spam" : "Not Spam";
    }
}
