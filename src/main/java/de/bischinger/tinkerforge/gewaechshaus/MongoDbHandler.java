package de.bischinger.tinkerforge.gewaechshaus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import de.bischinger.tinkerforge.gewaechshaus.events.AmbientLightEvent;
import de.bischinger.tinkerforge.gewaechshaus.events.HumidityEvent;
import de.bischinger.tinkerforge.gewaechshaus.events.MoistureEvent;
import de.bischinger.tinkerforge.gewaechshaus.events.TemperatureEvent;

/**
 * Created by Alexander Bischof on 16.03.15.
 */
public class MongoDbHandler {

  @Subscribe
  @AllowConcurrentEvents
  public void handleTemperatureEvent(TemperatureEvent temperatureEvent) {
	System.out.println("TemperatureEvent: " + temperatureEvent);
  }

  @Subscribe
  @AllowConcurrentEvents
  public void handleAmbientEvent(AmbientLightEvent ambientLightEvent) {
	System.out.println("AmbientLightEvent: " + ambientLightEvent);
  }

  @Subscribe
  @AllowConcurrentEvents
  public void handleMoistureEvent(MoistureEvent moistureEvent) {
	System.out.println("MoistureEvent: " + moistureEvent);
  }

  @Subscribe
  @AllowConcurrentEvents
  public void handleHumidityEvent(HumidityEvent humidityEvent) {
	System.out.println("HumidityEvent: " + humidityEvent);
  }
}
