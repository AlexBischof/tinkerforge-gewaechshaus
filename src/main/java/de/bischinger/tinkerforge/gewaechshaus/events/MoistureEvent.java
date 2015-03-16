package de.bischinger.tinkerforge.gewaechshaus.events;

/**
 * Created by Alexander Bischof on 16.03.15.
 */
public class MoistureEvent {
  private final long moisture;

  public MoistureEvent(int moisture) {
	this.moisture = moisture;
  }

  public long getMoisture() {
	return moisture;
  }

  @Override public String toString() {
	return "MoistureEvent{" +
	       "moisture=" + moisture +
	       '}';
  }
}
