package tp3.ensim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import tp3.ensim.model.AddressForm;
import tp3.ensim.model.AdresseRepository;
import tp3.ensim.model.EtalabAdresseResponse;
import tp3.ensim.model.Forecast;
import tp3.ensim.model.Response;

@Controller
public class MeteoController {

    @Autowired
    private AdresseRepository adresseRepository;      
    String meteoConceptApiKey = "d5fdc1814b5510362df6eb8e8a65444ca8695c17118ad22be66ac04f97a8fc7f";
    @PostMapping("/meteo")
    public String getMeteo(@ModelAttribute("addressForm") AddressForm addressForm, Model model) {
        
    	//Mise en place de RestTemplate pour la gestion des appels aux API
    	RestTemplate restTemplate = new RestTemplate();
    	
    	//PARTIE 1 : API ADRESSE ETALAB GESTION
        String etalabApiUrl = "https://api-adresse.data.gouv.fr/search/?q=" + addressForm.getAddress();                                     
        EtalabAdresseResponse etalabResponse = restTemplate.getForObject(etalabApiUrl, EtalabAdresseResponse.class);
                           
        //Récupérez les coordonnées depuis la réponse d'Etalab          	
        String city = etalabResponse.getFeatures().get(0).getProperties().getCity();
        String citycode = etalabResponse.getFeatures().get(0).getProperties().getCitycode();       	
        double longitude = etalabResponse.getFeatures().get(0).getGeometry().getCoordinates().get(0);      
        double latitude = etalabResponse.getFeatures().get(0).getGeometry().getCoordinates().get(1);
        
        // Affichez les informations de ETALAB recpéré dans la console
        System.out.println("\nCity: " + city);
        System.out.println("latitude: " + latitude);
        System.out.println("longitude: " + longitude);

        
        
        
        //PARTIE 2 : API METEO-CONCEPT GESTION       
        String meteoConceptApiUrl = "https://api.meteo-concept.com/api/forecast/daily?token="
              + meteoConceptApiKey  + "&latlng=" + latitude + ","+ longitude;                          
        Response response = restTemplate.getForObject(meteoConceptApiUrl, Response.class);
        //Récupérez les coordonnées depuis la réponse d'Etalab
        double tmin = response.getForecast().get(0).getTmin();
        double tmax = response.getForecast().get(0).getTmax();
        double weather = response.getForecast().get(0).getWeather();
        double wind10m = response.getForecast().get(0).getWind10m();
        double temperature = Forecast.calculateTemperature(tmin,tmax,wind10m);
        
        
        //Affichez les informations de MeteoConcept récupéré dans la console
        System.out.println("Temperature minimale: " + tmin);
        System.out.println("Temperature maximale: " + tmax);
        System.out.println("Temperature Ressenti : " + temperature);
        System.out.println("weather: " + weather);
               
        //Ajoutez des informations météorologiques au modèle        
        model.addAttribute("weatherInfo", "Adresse : " + addressForm.getAddress());
        model.addAttribute("cityInfo", "Ville : " + city);
        model.addAttribute("temperatureInfo1", "Température maximale : "+tmax);
        model.addAttribute("temperatureInfo2", "Température minimale : " + tmin);
        model.addAttribute("latitude","Latitude : " + latitude);
        model.addAttribute("longitude","Longitude : " + longitude);
        model.addAttribute("weatherDescription","Weather : " + weather);
        model.addAttribute("temperature","Temperature Ressentie : "+ temperature);
                        
        //Renvoie le template de la page meteo
        return "meteo";
       
    }
}
