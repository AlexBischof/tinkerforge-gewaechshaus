package de.bischinger.tinkerforge.gewaechshaus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import de.bischinger.tinkerforge.gewaechshaus.events.AmbientLightEvent;
import de.bischinger.tinkerforge.gewaechshaus.events.HumidityEvent;
import de.bischinger.tinkerforge.gewaechshaus.events.MoistureEvent;
import de.bischinger.tinkerforge.gewaechshaus.events.TemperatureEvent;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

/**
 * Created by Alexander Bischof on 16.03.15.
 */
public class MapDBHandler {
  private final BTreeMap<Object, Object> temperatureMap;
  private final DB db;

  public MapDBHandler() {
    db = DBMaker.newMemoryDB().make();

	   temperatureMap = db.getTreeMap("temperature");
	//  treeMap.put("something", "here");
	//  db.commit();
  }

  @Subscribe
  @AllowConcurrentEvents
  public void handleTemperatureEvent(TemperatureEvent temperatureEvent) {
    System.out.println("MapDB-TemperatureEvent: " + temperatureEvent);
    temperatureMap.put(System.currentTimeMillis(), temperatureEvent.getTemperature());
    db.commit();
  }

  @Subscribe
  @AllowConcurrentEvents
  public void handleAmbientEvent(AmbientLightEvent ambientLightEvent) {
	System.out.println("MapDB-AmbientLightEvent: " + ambientLightEvent);
  }

  @Subscribe
  @AllowConcurrentEvents
  public void handleMoistureEvent(MoistureEvent moistureEvent) {
	System.out.println("MapDB-MoistureEvent: " + moistureEvent);
  }

  @Subscribe
  @AllowConcurrentEvents
  public void handleHumidityEvent(HumidityEvent humidityEvent) {
	System.out.println("MapDB-HumidityEvent: " + humidityEvent);
  }
}
