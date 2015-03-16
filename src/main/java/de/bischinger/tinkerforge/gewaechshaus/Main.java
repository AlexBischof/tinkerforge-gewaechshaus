package de.bischinger.tinkerforge.gewaechshaus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.rapidpm.module.iot.tinkerforge.gui.fx.DateAxis;

import java.util.concurrent.Executors;

/**
 * Created by Alexander Bischof on 16.03.15.
 */
public class Main extends Application {

  public static void main(String args[]) throws Exception {
	launch(args);
  }

  @Override
  public void start(Stage stage) {

	final EventBus eventBus = new AsyncEventBus(Executors.newCachedThreadPool());
	new Thread(() -> new TinkerforgeConnector(eventBus)).start();

	stage.setTitle("Line Chart TinkerForge Sample");
	final DateAxis dateAxis = new DateAxis();
	final NumberAxis yAxis = new NumberAxis();
	dateAxis.setLabel("Time of Temp");
	final LineChart lineChart
			= new LineChart<>(dateAxis, yAxis);

	lineChart.setTitle("Temp Monitoring");

	XYChart.Series temperatureDataSeries = new XYChart.Series();
	temperatureDataSeries.setName("Temperature");

	XYChart.Series ambientDataSeries = new XYChart.Series();
	ambientDataSeries.setName("Ambient");

	XYChart.Series moistureDataSeries = new XYChart.Series();
	moistureDataSeries.setName("Moisture");

	XYChart.Series humidityDataSeries = new XYChart.Series();
	humidityDataSeries.setName("Humidity");

	eventBus.register(
			new JavaFXHandler(temperatureDataSeries.getData(), ambientDataSeries.getData(),
			                  moistureDataSeries.getData(), humidityDataSeries.getData()));

	lineChart.getData().add(temperatureDataSeries);
	lineChart.getData().add(ambientDataSeries);
	lineChart.getData().add(moistureDataSeries);
	lineChart.getData().add(humidityDataSeries);
	Scene scene = new Scene(lineChart, 800, 600);
	stage.setScene(scene);
	stage.show();

  }
}
