package com.httphunt.model;


import com.fasterxml.jackson.annotation.JsonInclude;

public class Tool {
    private String name;
    private String useStartTime;
    private String useEndTime;
    private Integer timeUsedInMinutes;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int weight;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int value;

    public Tool() {

    }

    public Tool(String name) {
        this.name = name;
    }

    public Tool(String name, Integer timeUsedInMinutes) {
        this(name);
        this.timeUsedInMinutes = timeUsedInMinutes;
    }

    public Tool(String name, int weight, int value) {
        this(name);
        this.weight = weight;
        this.value = value;
    }

    public Integer getTimeUsedInMinutes() {
        return timeUsedInMinutes;
    }

    public String getName() {
        return name;
    }

    public String getUseEndTime() {
        return useEndTime;
    }

    public String getUseStartTime() {
        return useStartTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimeUsedInMinutes(Integer timeUsedInMinutes) {
        this.timeUsedInMinutes = timeUsedInMinutes;
    }

    public void setUseEndTime(String useEndTime) {
        this.useEndTime = useEndTime;
    }

    public void setUseStartTime(String useStartTime) {
        this.useStartTime = useStartTime;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }
}
