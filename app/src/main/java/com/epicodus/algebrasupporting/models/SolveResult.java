package com.epicodus.algebrasupporting.models;

import java.util.ArrayList;

/**
 * Created by rodnier.borrego on 3/21/18.
 */

public class SolveResult {
    private String inputInterpretationPlainText;
    private String inputInterpretationImageUrl;
    private String resultsPlainText;
    private String resultsPlainTextImageUrl;
    private ArrayList<String> possibleIntermediateStepsPlainText;
    private String possibleIntermediateStepsImageUrl;
    private String plotImageUrl;

    public SolveResult(String inputInterpretationPlainText, String inputInterpretationImageUrl, String resultsPlainText,
                      String resultsPlainTextImageUrl, ArrayList<String> possibleIntermediateStepsPlainText,
                      String possibleIntermediateStepsImageUrl, String plotImageUrl) {
        this.inputInterpretationPlainText = inputInterpretationPlainText;
        this.inputInterpretationImageUrl = inputInterpretationImageUrl;
        this.resultsPlainText = resultsPlainText;
        this.resultsPlainTextImageUrl = resultsPlainTextImageUrl;
        this.possibleIntermediateStepsPlainText = possibleIntermediateStepsPlainText;
        this.possibleIntermediateStepsImageUrl = possibleIntermediateStepsImageUrl;
        this.plotImageUrl = plotImageUrl;

    }

    public String getInputInterpretationPlainText() {
        return inputInterpretationPlainText;
    }

    public void setInputInterpretationPlainText(String inputInterpretationPlainText) {
        this.inputInterpretationPlainText = inputInterpretationPlainText;
    }

    public String getInputInterpretationImageUrl() {
        return inputInterpretationImageUrl;
    }

    public void setInputInterpretationImageUrl(String inputInterpretationImageUrl) {
        this.inputInterpretationImageUrl = inputInterpretationImageUrl;
    }

    public String getResultsPlainText() {
        return resultsPlainText;
    }

    public void setResultsPlainText(String resultsPlainText) {
        this.resultsPlainText = resultsPlainText;
    }

    public String getResultsPlainTextImageUrl() {
        return resultsPlainTextImageUrl;
    }

    public void setResultsPlainTextImageUrl(String resultsPlainTextImageUrl) {
        this.resultsPlainTextImageUrl = resultsPlainTextImageUrl;
    }

    public ArrayList<String> getPossibleIntermediateStepsPlainText() {
        return possibleIntermediateStepsPlainText;
    }

    public void setPossibleIntermediateStepsPlainText(ArrayList<String> possibleIntermediateStepsPlainText) {
        this.possibleIntermediateStepsPlainText = possibleIntermediateStepsPlainText;
    }

    public String getPossibleIntermediateStepsImageUrl() {
        return possibleIntermediateStepsImageUrl;
    }

    public void setPossibleIntermediateStepsImageUrl(String possibleIntermediateStepsImageUrl) {
        this.possibleIntermediateStepsImageUrl = possibleIntermediateStepsImageUrl;
    }

    public String getPlotImageUrl() {
        return plotImageUrl;
    }

    public void setPlotImageUrl(String plotImageUrl) {
        this.plotImageUrl = plotImageUrl;
    }
}

