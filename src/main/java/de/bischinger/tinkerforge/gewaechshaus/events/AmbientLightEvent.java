package de.bischinger.tinkerforge.gewaechshaus.events;

/**
 * Created by Alexander Bischof on 16.03.15.
 */
public class AmbientLightEvent {
  private final long ambient;

  public AmbientLightEvent(int ambient) {
	this.ambient = ambient;
  }

  public long getAmbient() {
	return ambient;
  }

  @Override public String toString() {
	return "AmbientLightEvent{" +
	       "ambient=" + ambient +
	       '}';
  }
}
