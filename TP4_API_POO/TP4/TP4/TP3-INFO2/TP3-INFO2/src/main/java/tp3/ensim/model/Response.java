package tp3.ensim.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	@JsonProperty("forecast")
	private List<Forecast> forecast;

	public List<Forecast> getForecast() {
		return forecast;
	}

	public void setForecast(List<Forecast> forecast) {
		this.forecast = forecast;
	}			
}
