
### PARTIE 2
### TP4 2/2 : Utilisation d'API web avec Spring Boot
### J'ai ajouté des captures d'écran dans le fichier Captures Ecran Site Météo

LIEN GITHUB TP4 : 

### Etape 6 : Appel de l'API MeteoConcept

### Faut-il une clé API pour appeler MeteoConcept ?
Oui, une clé API est nécessaire pour accéder aux services de MeteoConcept. 
Voici ma clé API pour météo-concept : 
meteoConceptApiKey=d5fdc1814b5510362df6eb8e8a65444ca8695c17118ad22be66ac04f97a8fc7f

### Quelle URL appeler ?
L'URL à utiliser pour obtenir la longitude et la latitude est : 
etalabApiUrl = "https://api-adresse.data.gouv.fr/search/?q=" + addressForm.getAddress(); 

L'URL à utiliser pour obtenir les prévisions météorologiques quotidiennes est : 
meteoConceptApiUrl = "https://api.meteo-concept.com/api/forecast/daily?token="
              + meteoConceptApiKey  + "&latlng=" + latitude + ","+ longitude;

### Quelle méthode HTTP utiliser ?
Utilisez la méthode HTTP GET pour récupérer les données météorologiques.
Response response = restTemplate.getForObject(meteoConceptApiUrl, Response.class);

### Comment passer les paramètres d'appels ?
Les paramètres requis pour la requête GET sont :
- `token`: Votre clé API obtenue lors de l'inscription.
- `longitude`: La longitude de l'emplacement souhaité.
- `latitude`: La latitude de l'emplacement souhaité.

Exemple d'URL avec paramètres :
meteoConceptApiUrl = "https://api.meteo-concept.com/api/forecast/daily?token="
              + meteoConceptApiKey  + "&latlng=" + latitude + ","+ longitude;

### Où est l'information dont j'ai besoin dans la réponse :
Pour afficher la température et la météo du lieu visé par les coordonnées GPS, 
j'ai récupéré ces informations dans la réponse JSON : 

        double tmin = response.getForecast().get(0).getTmin();
        double tmax = response.getForecast().get(0).getTmax();
        double weather = response.getForecast().get(0).getWeather();
        double wind10m = response.getForecast().get(0).getWind10m();
      
Après j'ai fait une methode dans la classe Forecast permettant de 
calculer la température ressentie : 

        double temperature = Forecast.calculateTemperature(tmin,tmax,wind10m);





### Partie 1 (A voir dans l'autre fichier pour la partie 1 TP3)
### TP3: 1/2 Réalisation d'une application Web avec Spring Boot
### Utilisant H2 comme base de donnée
### Regarder dans le fichier TP_API_POO_2 pour voir l'utilisation avec MySQL.
### J'ai ajouté des captures d'écran dans le fichier capture test

LIEN GITHUB TP3 : 

### Description Succincte des dépendances dans pom.xml 

1. spring-boot-starter-data-jpa:
   Description : Starter pour l'utilisation de Spring Data JPA, qui simplifie l'accès
    et la manipulation de bases de données relationnelles en utilisant les concepts 
    de JPA (Java Persistence API).
   
2. h2:
   Description : Base de données relationnelle en mémoire, souvent utilisée pour 
   le développement et les tests. Cette dépendance est utilisée en tant que dépendance 
   de portée "runtime", ce qui signifie qu'elle n'est nécessaire qu'à l'exécution, 
   pas à la compilation.

3. spring-boot-starter-thymeleaf:
   Description : Starter pour l'utilisation de Thymeleaf, un moteur de template pour 
   la création de vues web dans les applications Spring Boot.

4. tomcat-embed-jasper:
   Description : Intégration de Tomcat et de Jasper (un moteur de compilation de JSP) 
   pour la prise en charge du rendu des pages JSP dans les applications Spring Boot.

5. spring-boot-starter-web:
   Description : Starter pour le développement d'applications web Spring Boot, incluant
    des fonctionnalités telles que les contrôleurs, les vues et la gestion des ressources statiques.

6. mysql-connector-java:
   Description : Connecteur JDBC pour MySQL, permettant à une application Java de 
   se connecter à une base de données MySQL.
    
7. spring-boot-starter-test:
   Description : Starter pour l'écriture de tests unitaires et d'intégration dans 
   les applications Spring Boot. Il inclut des bibliothèques telles que JUnit et Spring Test.

8. bootstrap:
   Description : Framework CSS qui facilite le stylisme et la mise en page d'une page web. 
   Il est utilisé ici via WebJars, qui permet d'inclure des bibliothèques JavaScript 
   et CSS dans les applications Java.

9. bootstrap-datepicker:
   Description : Plugin jQuery pour ajouter un sélecteur de date à un champ de formulaire. 
   Il est utilisé ici via WebJars.

10. jquery:
    Description : Bibliothèque JavaScript populaire qui simplifie la manipulation du DOM et 
    l'interaction avec le côté client dans les applications web. Utilisé ici via WebJars.

Ces dépendances couvrent un large éventail de fonctionnalités, allant de l'accès aux bases 
de données, à la création de vues web, en passant par le développement et les tests. 
L'utilisation de WebJars permet d'inclure facilement des bibliothèques JavaScript 
et CSS dans mon projet.

### Paramétrage de l'URL d'appel /greeting :
   - L'URL d'appel /greeting est paramétrée avec l'annotation `@GetMapping("/greeting")` 
   qui précède la méthode `greeting` du contrôleur. Cela signifie que lorsque l'application 
   reçoit une requête GET à l'URL /greeting, elle appelle la méthode `greeting` de ce contrôleur.

### Choix du fichier HTML à afficher :
   - Le choix du fichier HTML à afficher est déterminé par la valeur retournée par 
   la méthode `greeting`. Dans ce cas, la méthode retourne la chaîne "greeting". 
   Dans Spring Boot, cela est interprété comme le nom de la vue à afficher. Par défaut, 
   Spring Boot recherche un fichier HTML avec le même nom que la vue dans le dossier 
   des templates (souvent `src/main/resources/templates`). Ainsi, le fichier HTML 
   correspondant est "greeting.html".

### Envoi du nom pour saluer avec le second lien :
   - Le nom à qui dire bonjour est envoyé en tant que paramètre de requête dans l'URL. 
   Cela est géré par le paramètre `@RequestParam(name="nameGET", required=false, defaultValue="World") String nameGET`
   de la méthode `greeting`. Ce paramètre récupère la valeur du paramètre de requête "nameGET".
   Si ce paramètre n'est pas fourni dans l'URL, il prend la valeur par défaut "World". 
   La méthode ajoute ensuite ce nom au modèle (`Model`) avec la ligne `model.addAttribute("nomTemplate", nameGET)`, 
   ce qui le rend disponible pour être utilisé dans le fichier HTML associé.


### Console H2

Oui, j'ai remarqué que ma table est automatiquement inséré dans ma base de donnée.

    Au lancement de mon application :
        Hibernate analyse les entités de mon application (par exemple, les classes 
        annotées avec @Entity). Il vérifie si la structure de la base de données 
        correspond à la structure définie dans mes entités.

    Si une différence est détectée :
        Hibernate peut générer automatiquement les tables nécessaires dans ma base 
        de donnée H2 pour correspondre aux entités de mon application via @Entity.

    Dans la console H2 :
        Si Hibernate a détecté une différence entre la structure de la base de 
        données et mes entités. Il peut créer une nouvelle table pour correspondre 
        à une nouvelle entité ou à des modifications apportées à une entité existante.

    Apparition de la nouvelle table :
        L'apparition de la nouvelle table dans H2 pourrait être le résultat de cette
         synchronisation effectuée par Hibernate au démarrage de l'application.

     En relançant mon application, Oui je vois les contenus de data.sql

### Utilité de @Autowired

@Autowired est utilisée en Spring pour effectuer l'injection de dépendances automatique. 
Elle permet à Spring de résoudre et d'injecter automatiquement les dépendances dans 
les composants gérés par le conteneur Spring, tels que les contrôleurs, les services etc.

### Methodes pour ajouter Bootstrap dans le projet 

J'ai testé 2 méthodes différentes pour ajouter Bootstrap et tous les 2 marche très Bien ! 

### Première Méthode : 

- Télécharger Bootstrap sur le site officiel et dézipper le fichier .zip
- Ouvrir le fichier Bootstrap
- Copier les fichiers css et js
- Coller les fichiers dans le dossier main/resources/static de mon application
- Ajouter la dépendance Maven pour WebJars dans mon fichier pom.xml
- Ajouter les dépendances WebJars pour Bootstrap dans mon fichier pom.xml
- Aller sur mes pages Thymeleaf et insérer : 
	>link rel="stylesheet" href="/css/bootstrap.min.css"< dans le head 
	et >script src="/css/bootstrap.min.js"></script< dans le body
- Sauvegarder Tout et Redemarrer mon application

### Deuxième Méthode : 

- Ajouter la dépendance Maven pour WebJars dans mon fichier pom.xml
- Ajouter les dépendances WebJars pour Bootstrap dans mon fichier pom.xml
- Configurer Spring Boot pour exposer les ressources WebJars 
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
- Utiliser Bootstrap dans mes fichiers HTML Thymeleaf :
	>link rel="stylesheet" href="/webjars/bootstrap/5.3.0/css/bootstrap.min.css"< dans le head
	et >script src="/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script< dans le body
- Sauvegarder Tout et Redémarrer mon application