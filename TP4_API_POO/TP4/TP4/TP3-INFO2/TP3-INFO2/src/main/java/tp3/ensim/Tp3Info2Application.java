package tp3.ensim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tp3Info2Application {

	public static void main(String[] args) {
		SpringApplication.run(Tp3Info2Application.class, args);
		System.out.println("\n\nCopiez et collez cette URL dans votre navigateur pour accéder au site de météo :");
		System.out.println("http://localhost:8080/greeting");

	}

}
