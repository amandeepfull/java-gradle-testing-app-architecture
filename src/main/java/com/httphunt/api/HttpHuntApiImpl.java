package com.httphunt.api;

import com.httphunt.constants.ApiContants;
import com.httphunt.exception.HttpHuntApiException;
import com.httphunt.http.*;
import com.httphunt.model.ChallengeInfo;
import com.httphunt.model.response.HttpHuntApiResp;
import com.httphunt.utlity.ObjUtil;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class HttpHuntApiImpl implements HttpHuntApi {
    private String userId;

    public HttpHuntApiImpl(String userId) {
        this.userId = userId;
    }

    @Override
    public ChallengeInfo getChallengeInfo() throws HttpHuntApiException {

        ChallengeInfo challengeInfo = null;
        try {

            HttpRequest request = new HttpRequest(BaseApiUrl("/challenge"), HttpMethod.GET);

            Map<String, String> headers = new HashMap<>();
            headers.put("userId", this.userId);
            request.setHeaders(headers);
            HttpResponse httpResponse = HttpRequester.makeRequest(request);
            if (httpResponse.wasSuccessful()) {
                challengeInfo = ObjUtil.safeConvertJson(httpResponse.getResponseContent(), ChallengeInfo.class);
            }else {
                HttpHuntApiResp errorResp = ObjUtil.safeConvertJson(httpResponse.getResponseContent(), HttpHuntApiResp.class);
                throw new HttpHuntApiException(errorResp.getMessage(), httpResponse.getStatusCode());
            }

        } catch (Exception e) {
            throw new HttpHuntApiException(e.getMessage(), e);
        }
        return challengeInfo;
    }

    @Override
    public Map<String, Object> getInput() throws HttpHuntApiException {

        Map<String, Object> inputChallenge = null;
        try {

            HttpRequest request = new HttpRequest(BaseApiUrl("/challenge/input"), HttpMethod.GET);

            Map<String, String> headers = new HashMap<>();
            headers.put("userId", this.userId);
            request.setHeaders(headers);

            HttpResponse httpResponse = HttpRequester.makeRequest(request);
            if(httpResponse.wasSuccessful())
            inputChallenge = ObjUtil.getMapFromJson(httpResponse.getResponseContent());
            else{
                HttpHuntApiResp errorResp = ObjUtil.safeConvertJson(httpResponse.getResponseContent(), HttpHuntApiResp.class);
                throw new HttpHuntApiException(errorResp.getMessage(), httpResponse.getStatusCode());
            }

        } catch (Exception e) {
            throw new HttpHuntApiException(e.getMessage(), e);

        }
        return inputChallenge;
    }

    @Override
    public HttpHuntApiResp submitOutput(Map<String, Object> output) throws HttpHuntApiException {

        if(output == null || output.isEmpty())
            throw new IllegalArgumentException("Invalid output");

        HttpHuntApiResp httpHuntApiResp = null;
        try {

            HttpRequest request = new HttpRequest(BaseApiUrl("/challenge/output"), HttpMethod.POST);

            Map<String, String> headers = new HashMap<>();
            headers.put("userId", this.userId);

            request.setHeaders(headers);
            request.setPayload(ObjUtil.getJson(output));

            HttpResponse httpResponse = HttpRequester.makeRequest(request);
            if(httpResponse.wasSuccessful())
                httpHuntApiResp = ObjUtil.safeConvertJson(httpResponse.getResponseContent(), HttpHuntApiResp.class);
            else{
                HttpHuntApiResp errorResp = ObjUtil.safeConvertJson(httpResponse.getResponseContent(), HttpHuntApiResp.class);
                throw new HttpHuntApiException(errorResp.getMessage(),httpResponse.getStatusCode());
            }

        } catch (Exception e) {
            throw new HttpHuntApiException(e.getMessage(), e);
        }

        return httpHuntApiResp;
    }

    private String BaseApiUrl(String apiUrl) {
        return ApiContants.HTTP_HUNT_URL + apiUrl;
    }
}
