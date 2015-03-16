package de.bischinger.tinkerforge.gewaechshaus.events;

/**
 * Created by Alexander Bischof on 16.03.15.
 */
public class TemperatureEvent {
  private final int temperature;

  public TemperatureEvent(short temperature) {
	this.temperature = temperature;
  }

  public int getTemperature() {
	return temperature;
  }

  @Override public String toString() {
	return "TemperatureEvent{" +
	       "temperature=" + temperature +
	       '}';
  }
}
