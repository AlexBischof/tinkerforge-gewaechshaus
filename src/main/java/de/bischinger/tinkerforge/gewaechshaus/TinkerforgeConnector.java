package de.bischinger.tinkerforge.gewaechshaus;

import com.google.common.eventbus.EventBus;
import com.tinkerforge.*;
import de.bischinger.tinkerforge.BrickletReader;
import de.bischinger.tinkerforge.gewaechshaus.events.AmbientLightEvent;
import de.bischinger.tinkerforge.gewaechshaus.events.HumidityEvent;
import de.bischinger.tinkerforge.gewaechshaus.events.MoistureEvent;
import de.bischinger.tinkerforge.gewaechshaus.events.TemperatureEvent;

/**
 * Created by Alexander Bischof on 16.03.15.
 */
public class TinkerforgeConnector {

  public static final long CALLBACK_PERIOD = 1000l;

  public TinkerforgeConnector(EventBus eventBus) {
	IPConnection ipConnection = null;
	try {
	  ipConnection = new IPConnection();

	  //Create EventBus with Handler
	  // eventBus.register(new MapDBHandler());

	  BrickletReader brickletReader = new BrickletReader();
	  brickletReader.init(ipConnection);

	  //Hole Sensoren
	  initTemperatureSensor(ipConnection, eventBus, brickletReader);
	  initHumiditySensor(ipConnection, eventBus, brickletReader);
	  initMoistureSensor(ipConnection, eventBus, brickletReader);
	  initAmbientSensor(ipConnection, eventBus, brickletReader);
	} catch (Exception e) {
	  e.printStackTrace();
	} finally {
	  try {
		System.in.read();
		ipConnection.disconnect();
	  } catch (Exception e) {
		e.printStackTrace();
	  }
	}
  }

  private void initTemperatureSensor(IPConnection ipConnection, EventBus eventBus, BrickletReader brickletReader)
		  throws TimeoutException, NotConnectedException {
	BrickletTemperatureIR temperatureBrick = new BrickletTemperatureIR(
			brickletReader.getBrickletUid(BrickletTemperatureIR.class),
			ipConnection);
	temperatureBrick.setAmbientTemperatureCallbackPeriod(CALLBACK_PERIOD);
	temperatureBrick.addAmbientTemperatureListener(temperature -> eventBus.post(new TemperatureEvent(temperature)));
  }

  private void initHumiditySensor(IPConnection ipConnection, EventBus eventBus, BrickletReader brickletReader)
		  throws TimeoutException, NotConnectedException {
	BrickletHumidity humidityBrick = new BrickletHumidity(
			brickletReader.getBrickletUid(BrickletHumidity.class),
			ipConnection);
	humidityBrick.setHumidityCallbackPeriod(CALLBACK_PERIOD);
	humidityBrick.addHumidityListener(humidity -> eventBus.post(new HumidityEvent(humidity)));
  }

  private void initMoistureSensor(IPConnection ipConnection, EventBus eventBus, BrickletReader brickletReader)
		  throws TimeoutException, NotConnectedException {
	BrickletMoisture moistureBrick = new BrickletMoisture(
			brickletReader.getBrickletUid(BrickletMoisture.class),
			ipConnection);
	moistureBrick.setMoistureCallbackPeriod(CALLBACK_PERIOD);
	moistureBrick.addMoistureListener(moisture -> eventBus.post(new MoistureEvent(moisture)));
  }

  private void initAmbientSensor(IPConnection ipConnection, EventBus eventBus, BrickletReader brickletReader)
		  throws TimeoutException, NotConnectedException {
	BrickletAmbientLight ambientBrick = new BrickletAmbientLight(
			brickletReader.getBrickletUid(BrickletAmbientLight.class),
			ipConnection);
	ambientBrick.setIlluminanceCallbackPeriod(CALLBACK_PERIOD);
	ambientBrick.addIlluminanceListener(ambient -> eventBus.post(new AmbientLightEvent(ambient)));
  }
}
