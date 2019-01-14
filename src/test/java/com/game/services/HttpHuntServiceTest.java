package com.game.services;

import com.httphunt.model.Tool;
import com.httphunt.test.Tester;
import com.httphunt.utlity.ObjUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class HttpHuntServiceTest {

    private static final HttpHuntService httpService = new HttpHuntService();


    @Test
    public void testGetDecryptedMsg_0() {

        Map<String, Object> input = new HashMap<>();
        input.put("encryptedMessage", "DRO AESMU LBYGX PYH TEWZ YFOB DRO VKJI NYQ, SC'XD QBOKD");
        input.put("key", 10);

        Tester.test(() -> httpService.getDecryptedMsg(input), "THE QUICK BROWN FOX JUMP OVER THE LAZY DOG, IS'NT GREAT");

    }

    @Test
    public void testGetDecryptedMsg_1() {

        Map<String, Object> input = new HashMap<>();
        input.put("encryptedMessage", "P SPRL KVPUN JVKPUN HUK ZV P HT KVPUN, KPK FVB PTWYLZZLK!!");
        input.put("key", 7);

        Tester.test(() -> httpService.getDecryptedMsg(input), "I LIKE DOING CODING AND SO I AM DOING, DID YOU IMPRESSED!!");

    }

    @Test
    public void testGetDecryptedMsg_2() {

        Map<String, Object> input = new HashMap<>();
        input.put("encryptedMessage", "KWWS KXQW LV D QLFH JDPH, L HQMRBHG VROYLQJ LW, UHDOOB...");
        input.put("key", 3);

        Tester.test(() -> httpService.getDecryptedMsg(input), "HTTP HUNT IS A NICE GAME, I ENJOYED SOLVING IT, REALLY...");

    }

    @Test
    public void testGetHiddenTools_0() {

        Map<String, Object> input = new HashMap<>();
        input.put("hiddenTools", "fasfaladsfadsaafdsrfadsfesferwiterwrgstfadsgifghdugurynrgrdfovdfpgsge");

        List<String> tools = new ArrayList<>();

        tools.add("flare");
        tools.add("firstaid");
        tools.add("crowbar");
        tools.add("gun");
        tools.add("rope");

        input.put("tools", tools);

        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("flare");
        expectedOutput.add("firstaid");

        Tester.test(() -> httpService.getHiddenTools(input), expectedOutput);

    }

    @Test
    public void testGetHiddenTools_1() {

        Map<String, Object> input = new HashMap<>();
        input.put("hiddenTools", "fasflarasdfadsfeadfacsdfashdfadsofadscfoalafdsafstfadsefasfcasgrdfohdfgswhgdfbgdsafrasdfgaufnfrdsgofghspdhesgfhfg");

        List<String> tools = new ArrayList<>();

        tools.add("flare");
        tools.add("chocolate");
        tools.add("crowbar");
        tools.add("gun");
        tools.add("rope");

        input.put("tools", tools);

        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("flare");
        expectedOutput.add("chocolate");
        expectedOutput.add("crowbar");
        expectedOutput.add("gun");
        expectedOutput.add("rope");

        Tester.test(() -> httpService.getHiddenTools(input), expectedOutput);

    }


    @Test
    public void testGetTimeUsage_0(){

        Tester.test(() -> httpService.getTimeUsage("2017-01-30 10:10:00","2017-01-30 10:40:00"), 30);
        Tester.test(() -> httpService.getTimeUsage("2017-01-30 11:10:00","2017-01-30 13:30:00"), 140);
        Tester.test(() -> httpService.getTimeUsage("2017-01-30 12:10:00","2017-01-30 12:50:00"), 40);
        Tester.test(() -> httpService.getTimeUsage("2017-01-30 10:10:00","2017-01-30 10:15:00"), 5);

    }

    @Test
    public void testGetToolsSortedOnUsage_0() {

        Map<String, Object> input = new HashMap<>();

        List<LinkedHashMap<String, String>> toolUsage = new ArrayList<>();

        LinkedHashMap<String, String> tool_0 = new LinkedHashMap();
        tool_0.put("name", "flare");
        tool_0.put("useStartTime", "2017-01-30 10:10:00");
        tool_0.put("useEndTime", "2017-01-30 10:40:00");
        toolUsage.add(tool_0);

        LinkedHashMap<String, String> tool_1 = new LinkedHashMap<>();
        tool_1.put("name", "firstaid");
        tool_1.put("useStartTime", "2017-01-30 10:15:00");
        tool_1.put("useEndTime", "2017-01-30 10:20:00");
        toolUsage.add(tool_1);

        LinkedHashMap<String, String> tool_2 = new LinkedHashMap<>();
        tool_2.put("name", "firstaid");
        tool_2.put("useStartTime", "2017-01-30 11:00:00");
        tool_2.put("useEndTime", "2017-01-30 11:10:00");
        toolUsage.add(tool_2);

        LinkedHashMap<String, String> tool_3 = new LinkedHashMap<>();
        tool_3.put("name", "flare");
        tool_3.put("useStartTime", "2017-01-30 11:10:00");
        tool_3.put("useEndTime", "2017-01-30 11:15:00");
        toolUsage.add(tool_3);

        LinkedHashMap<String, String> tool_4 = new LinkedHashMap<>();
        tool_4.put("name", "rope");
        tool_4.put("useStartTime", "2017-01-30 13:00:00");
        tool_4.put("useEndTime", "2017-01-30 14:00:00");
        toolUsage.add(tool_4);

        LinkedHashMap<String, String> tool_5 = new LinkedHashMap<>();
        tool_5.put("name", "gun");
        tool_5.put("useStartTime", "2017-01-30 13:10:00");
        tool_5.put("useEndTime", "2017-01-30 13:30:00");
        toolUsage.add(tool_5);

        LinkedHashMap<String, String> tool_6 = new LinkedHashMap<>();
        tool_6.put("name", "gun");
        tool_6.put("useStartTime", "2017-01-30 09:00:00");
        tool_6.put("useEndTime", "2017-01-30 09:20:00");
        toolUsage.add(tool_6);
        input.put("toolUsage", toolUsage);


        List<Tool> expectedOutput = new ArrayList<>();
        expectedOutput.add(new Tool("rope", 60));
        expectedOutput.add(new Tool("gun", 40));
        expectedOutput.add(new Tool("flare", 35));
        expectedOutput.add(new Tool("firstaid", 15));

        Tester.test(() -> httpService.getToolsSortedOnUsage(input), expectedOutput);

    }

    @Test
    public void testGetToolsToTakeSorted_0() {

        Map<String, Object> input = new HashMap<>();

        List<LinkedHashMap<String, Object>> toolUsage = new ArrayList<>();

        LinkedHashMap<String, Object> tool_0 = new LinkedHashMap();
        tool_0.put("name", "knife");
        tool_0.put("weight", 10);
        tool_0.put("value", 60);
        toolUsage.add(tool_0);

        LinkedHashMap<String, Object> tool_1 = new LinkedHashMap<>();
        tool_1.put("name", "guns");
        tool_1.put("weight", 20);
        tool_1.put("value", 100);
        toolUsage.add(tool_1);

        LinkedHashMap<String, Object> tool_2 = new LinkedHashMap<>();
        tool_2.put("name", "rope");
        tool_2.put("weight", 30);
        tool_2.put("value", 120);
        toolUsage.add(tool_2);

        LinkedHashMap<String, Object> tool_3 = new LinkedHashMap<>();
        tool_3.put("name", "water");
        tool_3.put("weight", 60);
        tool_3.put("value", 500);
        toolUsage.add(tool_3);

        input.put("tools", toolUsage);
        input.put("maximumWeight", 60);

        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("water");

        Tester.test(() -> httpService.getToolsToTakeSorted(input), expectedOutput);
    }

    @Test
    public void testGetToolsToTakeSorted_1() {

        Map<String, Object> input = new HashMap<>();

        List<LinkedHashMap<String, Object>> toolUsage = new ArrayList<>();

        LinkedHashMap<String, Object> tool_0 = new LinkedHashMap();
        tool_0.put("name", "knife");
        tool_0.put("weight", 10);
        tool_0.put("value", 60);
        toolUsage.add(tool_0);

        LinkedHashMap<String, Object> tool_1 = new LinkedHashMap<>();
        tool_1.put("name", "guns");
        tool_1.put("weight", 20);
        tool_1.put("value", 100);
        toolUsage.add(tool_1);

        LinkedHashMap<String, Object> tool_2 = new LinkedHashMap<>();
        tool_2.put("name", "rope");
        tool_2.put("weight", 30);
        tool_2.put("value", 120);
        toolUsage.add(tool_2);

        LinkedHashMap<String, Object> tool_3 = new LinkedHashMap<>();
        tool_3.put("name", "flare");
        tool_3.put("weight", 60);
        tool_3.put("value", 200);
        toolUsage.add(tool_3);

        input.put("tools", toolUsage);
        input.put("maximumWeight", 60);

        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("flare");

        Tester.test(() -> httpService.getToolsToTakeSorted(input), expectedOutput);

    }
}