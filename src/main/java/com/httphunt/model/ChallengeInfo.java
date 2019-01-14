package com.httphunt.model;

import com.httphunt.model.response.HttpHuntApiResp;
import com.httphunt.enums.Stage;

public class ChallengeInfo extends HttpHuntApiResp {
    private Stage stage;
    private String statement;
    private String instructions;
    private ChallengeInput sampleInput;
    private ChallengeOutput sampleOutput;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setSampleInput(ChallengeInput sampleInput) {
        this.sampleInput = sampleInput;
    }

    public void setSampleOutput(ChallengeOutput sampleOutput) {
        this.sampleOutput = sampleOutput;
    }

    public Stage getStage() {
        return stage;
    }

    public String getStatement() {
        return statement;
    }

    public String getInstructions() {
        return instructions;
    }

    public ChallengeInput getSampleInput() {
        return sampleInput;
    }

    public ChallengeOutput getSampleOutput() {
        return sampleOutput;
    }


}
