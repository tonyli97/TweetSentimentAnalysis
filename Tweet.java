package eecs285.proj1.qiranli;

import java.util.ArrayList;
import java.util.Arrays;

public class Tweet {
  private String content;
  private Location Loc;
  public Tweet(String content, double lat, double lon) {
    this.content = content;
    this.Loc = new Location(lat,lon);
  }
  public String getText() {
    return content;
  }

  public Location getLocation(){
    return Loc;
  }

  @Override
  public String toString() {
    return Loc.toString() + " " + content;
  }

  public ArrayList<String> getWords() {
    String str = content.replaceAll("[^A-Za-z\\s]+", "");
    String[] newStr = str.split("\\s+");
    ArrayList<String> words = new ArrayList<>(Arrays.asList(newStr));
    return words;
  }
}
