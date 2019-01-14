package com.game.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.httphunt.enums.Stage;
import com.httphunt.model.Tool;
import com.httphunt.utlity.JacksonObjectMapper;
import com.httphunt.utlity.ObjUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HttpHuntService {


    public Map<String, Object> getOutput(Map<String, Object> input, Stage stage) {

        Map<String, Object> output = new HashMap<>();
        switch (stage) {
            case FIRST:

                output.put("message", getDecryptedMsg(input));
                break;

            case SECOND:

                output.put("toolsFound", getHiddenTools(input));
                break;

            case THIRD:

                output.put("toolsSortedOnUsage", getToolsSortedOnUsage(input));
                break;

            case FOURTH:

                output.put("toolsToTakeSorted", getToolsToTakeSorted(input));
        }
        return output;
    }

    public List<String> getToolsToTakeSorted(Map<String, Object> input) {

        List<Tool> tools = new JacksonObjectMapper().convertValue(input.get("tools"), new TypeReference<List<Tool>>() {
        });
        int maximumWeight = (int) input.get("maximumWeight");

        tools.sort((Tool o1, Tool o2) -> (o2.getValue() - o1.getValue()));

        List<String> toolsToTakeSorted = new ArrayList<>();

        int sumOfweight = 0;
        for (Tool tool : tools) {

            sumOfweight = sumOfweight + tool.getWeight();
            if (sumOfweight <= maximumWeight)
                toolsToTakeSorted.add(tool.getName());

            if (sumOfweight >= maximumWeight)
                break;
        }

        return toolsToTakeSorted;

    }

    public List<Tool> getToolsSortedOnUsage(Map<String, Object> input) {

        List<Map<String, String>> toolUsagesInput = (List<Map<String, String>>) input.get("toolUsage");

        Map<String, Integer> tempUsageData = new HashMap<>();

        for (Map<String, String> toolUsage : toolUsagesInput) {

            String toolName = toolUsage.get("name");
            Integer timeUsage = tempUsageData.get(toolName);
            timeUsage = timeUsage == null ? 0 : timeUsage;
            timeUsage = timeUsage + getTimeUsage(toolUsage.get("useStartTime"), toolUsage.get("useEndTime"));
            tempUsageData.put(toolName, timeUsage);
        }

        List<Tool> toolsSortedOnUsage = new ArrayList<>();
        for (String key : tempUsageData.keySet()) {
            toolsSortedOnUsage.add(new Tool(key, tempUsageData.get(key)));
        }

        toolsSortedOnUsage.sort((Tool o1, Tool o2) -> (int) (o2.getTimeUsedInMinutes() - o1.getTimeUsedInMinutes()));

        return toolsSortedOnUsage;
    }

    public int getTimeUsage(String useStartTime, String useEndTime) {

        final int miliseconds = 1000;
        final int seconds = 60;

        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int timeUsage = 0;
        try {
            Date startDate = SimpleDateFormat.parse(useStartTime);
            long startTime = (startDate.getTime() / (miliseconds * seconds));// converting to minutes

            Date endDate = SimpleDateFormat.parse(useEndTime);
            long endTime = (endDate.getTime() / (miliseconds * seconds));

            timeUsage = Integer.parseInt(new Long(endTime - startTime).toString());

        } catch (ParseException e) {
            System.out.println(e);
            return 0;
        }

        return timeUsage;

    }

    public List<String> getHiddenTools(Map<String, Object> input) {

        String hiddenTools = (String) input.get("hiddenTools");
        List<String> tools = (List<String>) input.get("tools");
        Iterator iterator = tools.iterator();
        int jIndex = 0;
        char[] newStrChars = hiddenTools.toCharArray();
        while (iterator.hasNext()) {
            String word = (String) iterator.next();
            char[] wordChars = word.toCharArray();

            int wordSize = word.length();
            int count = 0;
            for (int i = 0; i < wordChars.length; i++) {

                for (int j = jIndex; j < newStrChars.length; j++) {

                    if (wordChars[i] == newStrChars[j]) {
                        count++;
                        jIndex = j;
                        break;
                    }

                }

            }
            if (count != wordSize)
                iterator.remove();
        }

        System.out.println(ObjUtil.getJson(tools));
        return tools;

    }

    public String getDecryptedMsg(Map<String, Object> input) {

        String encryptedMsg = (String) input.get("encryptedMessage");
        int key = (int) input.get("key");

        String decryptedString = "";
        char[] chars = encryptedMsg.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            int chatAsci = (int) chars[i];
            if (chatAsci >= 65 && chatAsci <= 90) {
                chatAsci = chatAsci - key;

                if (chatAsci < 65) { // in case it decreases than 65, we have to start with last alphabet that is Z(90)
                    chatAsci = 90 - (65 - chatAsci - 1);
                }

                decryptedString = decryptedString.concat(new Character((char) chatAsci).toString());

            } else {
                decryptedString = decryptedString.concat(new Character(chars[i]).toString());
            }

        }
        return decryptedString;

    }

}
