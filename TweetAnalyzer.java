/**
 * EECS 285 Project 1 -- Class that analyzes tweet sentiments.
 *
 * Contains methods to analyze the sentiment of an individual tweet,
 * as well as the average sentiment of tweets within and outside a
 * geographic area.
 *
 * See https://eecs285.github.io/p1-sentiments/ for the specification.
 */

package eecs285.proj1.qiranli; // replace with your uniqname

import java.util.ArrayList;

public class TweetAnalyzer {
  /**
   * Constructs a TweetAnalyzer using the given sentiments file and
   * tweets file.
   *
   * @param sentimentFilename  the path of the sentiments file
   * @param tweetFilename  the path of the tweets file
   */
  public TweetAnalyzer(String sentimentFilename,
                       String tweetFilename) {
    sentiments = new Sentiments(sentimentFilename);
    tweets = new TweetReader(tweetFilename);
  }

  /**
   * Computes the overall sentiment of a tweet.
   *
   * The result is the average sentiment of the words in the tweet
   * that have a non-zero sentiment value. Returns null if no word in
   * the tweet has a non-zero sentiment.
   *
   * @param tweet  the tweet to analyze
   * @return  the overall sentiment of the tweet
   */
  public Double computeSentiment(Tweet tweet) {
    ArrayList<String> words =  tweet.getWords();
    int count = 0;
    double total = 0.0;
    for (String word:words) {
      double sentiment = sentiments.getSentiment(word);
      if (sentiment != 0) {
        count += 1;
        total += sentiment;
      }
    }
//    System.out.println(count);
//    System.out.println(total);
    return count == 0 ? null : (Double) total/count;
  }

  /**
   * Computes the average sentiment of tweets within the given
   * geographic area.
   *
   * Prints out the average sentiment to standard output in the format
   * described in the project specification.
   *
   * @param lowerBound  the lower bound of the geographic area
   * @param upperBound  the upper bound of the geographic area
   */
  public void averageSentimentWithin(Location lowerBound,
                                     Location upperBound) {

    int count = 0;
    double total = 0.0;
    for (int i = 0; i < tweets.size(); ++i) {
     Tweet tweet = tweets.get(i);
     Location tweetLoc = tweet.getLocation();
     Double sentiment = computeSentiment(tweet);
     if (tweetLoc.isWithin(lowerBound,upperBound) && (sentiment != null)) {
       count += 1;
       total += sentiment;
     }
   }
    System.out.println(count);
    System.out.println(total);
    if (count == 0) {
      System.out.println("No tweets with sentiment");
    } else {
      System.out.println("Average sentiment over " + count + " tweets: "
          + roundedString(total/count));
    }

  }

  /**
   * Computes the average sentiment of tweets outside the given
   * geographic area.
   *
   * Prints out the average sentiment to standard output in the format
   * described in the project specification.
   *
   * @param lowerBound  the lower bound of the geographic area
   * @param upperBound  the upper bound of the geographic area
   */
  public void averageSentimentOutside(Location lowerBound,
                                      Location upperBound) {
    int count = 0;
    double total = 0.0;
    for (int i = 0; i < tweets.size(); ++i) {
      Tweet tweet = tweets.get(i);
      Location tweetLoc = tweet.getLocation();
      Double sentiment = computeSentiment(tweet);
      if (!tweetLoc.isWithin(lowerBound,upperBound) && (sentiment != null)) {
        count += 1;
        total += sentiment;
      }
    }
    if (count == 0) {
      System.out.println("No tweets with sentiment");
    } else {
      System.out.println("Average sentiment over " + count + " tweets: "
          + roundedString(total/count));
    }
  }

  /**
   * Returns a string representation of the given double, rounded to
   * four digits after the decimal.
   *
   * @param value  the value to represent
   * @return  string representation of value
   */
  String roundedString(double value) {
    return String.format("%.4f", value);
  }

  /** Mapping of words to sentiment values. */
  private Sentiments sentiments;
  /** Contains the tweet data to analyze. */
  private TweetReader tweets;
}
