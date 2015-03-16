package de.bischinger.tinkerforge.gewaechshaus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import de.bischinger.tinkerforge.gewaechshaus.events.AmbientLightEvent;
import de.bischinger.tinkerforge.gewaechshaus.events.HumidityEvent;
import de.bischinger.tinkerforge.gewaechshaus.events.MoistureEvent;
import de.bischinger.tinkerforge.gewaechshaus.events.TemperatureEvent;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.Date;

/**
 * Created by Alexander Bischof on 16.03.15.
 */
public class JavaFXHandler {

  private final ObservableList temperatureDataSeries;
  private final ObservableList ambientDataSeries;
  private final ObservableList moistureDataSeries;
  private final ObservableList humidityDataSeries;

  public JavaFXHandler(ObservableList temperatureDataSeries, ObservableList ambientDataSeries,
                       ObservableList moistureDataSeries, ObservableList humidityDataSeries) {
	this.temperatureDataSeries = temperatureDataSeries;
	this.ambientDataSeries = ambientDataSeries;
	this.moistureDataSeries = moistureDataSeries;
	this.humidityDataSeries = humidityDataSeries;
  }

  @Subscribe
  @AllowConcurrentEvents
  public void handleTemperatureEvent(TemperatureEvent temperatureEvent) {
	Platform.runLater(() -> temperatureDataSeries.add(new XYChart.Data(new Date(), temperatureEvent.getTemperature() / 10.0)));
  }

  @Subscribe
  @AllowConcurrentEvents
  public void handleAmbientEvent(AmbientLightEvent ambientLightEvent) {
	Platform.runLater(() -> ambientDataSeries.add(new XYChart.Data(new Date(), ambientLightEvent.getAmbient() / 10.0)));
  }

  @Subscribe
  @AllowConcurrentEvents
  public void handleMoistureEvent(MoistureEvent moistureEvent) {
	Platform.runLater(() -> moistureDataSeries.add(new XYChart.Data(new Date(), moistureEvent.getMoisture())));
  }

  @Subscribe
  @AllowConcurrentEvents
  public void handleHumidityEvent(HumidityEvent humidityEvent) {
	Platform.runLater(() -> humidityDataSeries.add(new XYChart.Data(new Date(), humidityEvent.getHumidity() / 10)));
  }
}
