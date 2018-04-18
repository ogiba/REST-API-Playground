/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ogiba.mavenwebproject.models;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author ogiba
 */
public class Post {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;

    public Post(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
