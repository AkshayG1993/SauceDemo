// src/test/java/com/sourcedemo/automation/Hooks/StepDescriptionLogger.java
package com.sourcedemo.automation.Hooks;

import com.aventstack.extentreports.Status;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestStepStarted;

import static com.sourcedemo.automation.Hooks.ExtentHooks.getTest;

public class StepDescriptionLogger implements ConcurrentEventListener {
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, this::logStepDescription);
    }

    private void logStepDescription(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
            String stepText = step.getStep().getText();
            getTest().log(Status.INFO, "Step: " + stepText);
        }
    }
}