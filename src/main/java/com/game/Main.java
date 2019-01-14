package com.game;

import com.httphunt.constants.ApiContants;
import com.httphunt.api.HttpHuntApi;
import com.httphunt.api.HttpHuntApiImpl;
import com.httphunt.exception.HttpHuntApiException;
import com.httphunt.model.ChallengeInfo;
import com.httphunt.model.response.HttpHuntApiResp;
import com.httphunt.enums.Stage;
import com.game.services.HttpHuntService;

import java.util.Map;

public class Main {

    public static void main(String arg[]) {


        System.out.println(".................................");
        System.out.println(".................................");
        System.out.println("Lets Start HTTP HUNT Game!!!");
        System.out.println(".................................");
        System.out.println(".................................\n");

        try {
            ChallengeInfo challengeInfo = httpHuntApi().getChallengeInfo();

            Stage stage = challengeInfo.getStage();

            if (stage == null) {
                System.out.println(challengeInfo.getMessage());
                return;
            }

            System.out.println("Stage : " + stage + " Challenge!!!");

            Map<String, Object> input = httpHuntApi().getInput();
            HttpHuntApiResp huntApiResp = httpHuntApi().submitOutput(new HttpHuntService().getOutput(input, stage));

            System.out.println(huntApiResp.getMessage());

        }catch (HttpHuntApiException e){
            e.printStackTrace();
        }

    }

    public static HttpHuntApi httpHuntApi() { return new HttpHuntApiImpl(ApiContants.userCredentials);
    }
}
