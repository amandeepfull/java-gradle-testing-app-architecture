package com.httphunt.api;

import com.httphunt.exception.HttpHuntApiException;
import com.httphunt.model.ChallengeInfo;
import com.httphunt.model.response.HttpHuntApiResp;

import java.util.Map;

public interface HttpHuntApi {

    ChallengeInfo getChallengeInfo() throws HttpHuntApiException;

    Map<String, Object> getInput() throws HttpHuntApiException;

    HttpHuntApiResp submitOutput(Map<String, Object> output) throws HttpHuntApiException;
}
