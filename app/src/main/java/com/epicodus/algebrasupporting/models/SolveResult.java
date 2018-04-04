package com.epicodus.algebrasupporting.models;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by rodnier.borrego on 3/21/18.
 */

@Parcel
public class SolveResult {
    private String inputInterpretationPlainText;
    private String resultsPlainText;
    private String possibleIntermediateStepsPlainText;
    private String description;
    private String pushId;

    public SolveResult(){};

    public SolveResult(String inputInterpretationPlainText, String resultsPlainText, String possibleIntermediateStepsPlainText, String description) {
        this.inputInterpretationPlainText = inputInterpretationPlainText;
        this.resultsPlainText = resultsPlainText;
        this.possibleIntermediateStepsPlainText = possibleIntermediateStepsPlainText;
        this.description = description;
    }

    public String getInputInterpretationPlainText() {
        return inputInterpretationPlainText;
    }

    public String getPossibleIntermediateStepsPlainText() {
        return possibleIntermediateStepsPlainText;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPossibleIntermediateStepsPlainText(String possibleIntermediateStepsPlainText) {
        this.possibleIntermediateStepsPlainText = possibleIntermediateStepsPlainText;
    }

    public void setInputInterpretationPlainText(String inputInterpretationPlainText) {
        this.inputInterpretationPlainText = inputInterpretationPlainText;
    }

    public String getResultsPlainText() {
        return resultsPlainText;
    }

    public void setResultsPlainText(String resultsPlainText) {
        this.resultsPlainText = resultsPlainText;
    }
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

}

