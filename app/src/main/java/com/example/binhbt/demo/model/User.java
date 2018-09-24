package com.example.binhbt.demo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

  @SerializedName("id")
  private int userId;
  @SerializedName("cover_url")
  private String coverUrl;
  @SerializedName("full_name")
  private String fullName;
  @SerializedName("email")
  private List<String> email;
  @SerializedName("description")
  private String description;
  @SerializedName("followers")
  private int followers;

  public int getUserId() {
    return userId;
  }

  public String getCoverUrl() {
    return coverUrl;
  }

  public void setCoverUrl(String coverUrl) {
    this.coverUrl = coverUrl;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public List<String> getEmail() {
    return email;
  }

  public void setEmail(List<String> email) {
    this.email = email;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getFollowers() {
    return followers;
  }

  public void setFollowers(int followers) {
    this.followers = followers;
  }
  /*
  @Override public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("***** User Details *****\n");
    stringBuilder.append("id=" + this.getUserId() + "\n");
    stringBuilder.append("cover url=" + this.getCoverUrl() + "\n");
    stringBuilder.append("fullname=" + this.getFullName() + "\n");
    stringBuilder.append("email=" + this.getEmail() + "\n");
    stringBuilder.append("description=" + this.getDescription() + "\n");
    stringBuilder.append("followers=" + this.getFollowers() + "\n");
    stringBuilder.append("*******************************");

    return stringBuilder.toString();

  }
  */
}
