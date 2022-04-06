package eecs285.proj1.qiranli;

public class Location {
  private double lat;
  private double lon;

  public Location(double lat, double lon) {
    this.lat = lat;
    this.lon = lon;
  }
  public double getLatitude() {
    return lat;
  }
  public double getLongitude() {
    return lon;
  }

  @Override
  public String toString() {
    return "[" + lat + "," + lon +"]";
  }

  public boolean isWithin(Location lowerBound, Location upperBound) {
    double lowerLat = lowerBound.getLatitude();
    double lowerLon = lowerBound.getLongitude();
    double upperLat = upperBound.getLatitude();
    double upperLon = upperBound.getLongitude();
    return (lowerLat <= lat & lat <= upperLat) && (lowerLon <= lon & lon <= upperLon);
  }

}
