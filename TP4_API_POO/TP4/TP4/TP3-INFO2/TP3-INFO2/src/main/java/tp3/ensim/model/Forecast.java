package tp3.ensim.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Forecast {
	
	@JsonProperty("tmin")
	private double tmin;
	@JsonProperty("tmax")
	private double tmax;
	@JsonProperty("weather")
	private double weather;
	
	private double wind10m;
	
	//Calcul de la température ressentie
	public static double calculateTemperature(double tmin,double tmax, double wind10m) {   
		double temperature = (tmin+tmax) / 2;
        double feelsLikeTemperature = temperature - 0.1 * wind10m;    
        return (int)(Math.round(feelsLikeTemperature * 10.0) / 10.0);
    }
		
	public double getTmin() {
		return tmin;
	}
	public void setTmin(double tmin) {
		this.tmin = tmin;
	}	
	
	public double getTmax() {
		return tmax;
	}
	public void setTmax(double tmax) {
		this.tmax = tmax;
	}
	
	public double getWeather() {
		return weather;
	}
	public void setWeather(double weather) {
		this.weather = weather;
	}

	
	public double getWind10m() {
		return wind10m;
	}
	public void setWind10m(double wind10m) {
		this.wind10m = wind10m;
	}		
}
