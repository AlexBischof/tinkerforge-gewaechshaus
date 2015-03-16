package de.bischinger.tinkerforge.gewaechshaus.events;

/**
 * Created by Alexander Bischof on 16.03.15.
 */
public class HumidityEvent {
  private final long humidity;

  public HumidityEvent(int humidity) {
	this.humidity = humidity;
  }

  public long getHumidity() {
	return humidity;
  }

  @Override public String toString() {
	return "HumidityEvent{" +
	       "humidity=" + humidity +
	       '}';
  }
}
