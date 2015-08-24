package com.example.administrator.guess;

/**
 * Created by Administrator on 2015/4/30.
 */
public class RankData implements Comparable{
    private int times;
    private String name;

    public RankData(int times,String name) {
        this.times = times;
        this.name=name;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object another) {
        return this.times-((RankData)another).getTimes();
    }
}
