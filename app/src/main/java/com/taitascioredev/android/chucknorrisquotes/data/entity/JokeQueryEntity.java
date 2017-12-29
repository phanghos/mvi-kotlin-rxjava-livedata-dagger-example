package com.taitascioredev.android.chucknorrisquotes.data.entity;

import com.google.gson.annotations.SerializedName;
import com.taitascioredev.android.chucknorrisquotes.model.Joke;

import java.util.List;

/**
 * Created by rrtatasciore on 27/12/17.
 */

public class JokeQueryEntity {

    @SerializedName("total")
    private int total;
    @SerializedName("result")
    private List<Joke> result;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Joke> getResult() {
        return result;
    }

    public void setResult(List<Joke> result) {
        this.result = result;
    }

}
