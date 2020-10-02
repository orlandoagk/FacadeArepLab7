# AREP7 Secures App's

## Description
In this project we build two secure apps, a [FacadeApp](https://github.com/orlandoagk/FacadeArepLab7) that is in this repository and a service that offer a API that the facade consume that is called [OtherSecureApp](https://github.com/orlandoagk/OtherSecureAppArepLab7), the facade have a authentication and authorization control, the OtherSecureApp only have a API that is offered with method POST in the endpoint (/). All the application have a secure channel, thats means that the petitions use http + ssl (https).


## Docker Images

All the images are with latest tag
**Important that the images clone the correspondent repository if you need to change the code I don't recommend to use the exactly image

- [orlandogk10/facadearep7](https://hub.docker.com/repository/docker/orlandogk10/facadearep7)
- [orlandogk10/othersecureapp](https://hub.docker.com/repository/docker/orlandogk10/othersecureapp)

## Deploy
**Important:** To execute the project you need to have Java 8 and Maven or use the Docker images to run a container. Other thing is that you need to put the trustStore that you generate with the certificate of the other app (The Facade need the trustStore generated with the certificated of the OtherSecureApp and vice versa

### Code (Deploying)

[Facade](https://github.com/orlandoagk/FacadeArepLab7)
```
git clone https://github.com/orlandoagk/FacadeArepLab7.git
cd FacadeArepLab7
mvn compile
mvn clean package
java -cp target/classes:target/dependency/* edu.escuelaing.arep.secureapp.Main
```

[OtherSecureApp](https://github.com/orlandoagk/OtherSecureAppArepLab7)
```
git clone https://github.com/orlandoagk/OtherSecureAppArepLab7.git
cd OtherSecureAppLab7
mvn compile
mvn clean package
java -cp target/classes:target/dependency/* edu.escuelaing.arep.secureapp.Main
```

**Yo need to change the Facade ImplService to the new endopoint to consume the OtherSecureApp API to the server that you deploy it** 

### How to use it
[![Video](https://img.youtube.com/vi/eELPS2OGjHU/0.jpg)](https://www.youtube.com/watch?v=eELPS2OGjHU)


### Test
1. `mvn test`


## Documentation
To read the documentation of the project you need to enter to the enter the APP folder in the project and search in the Documentation folder, open the Index if you want to have a similar experience to the real Java API documentation

## Tecnology Stack
- [Java 8](https://www.java.com/es/download/)
- [Apache Maven](https://maven.apache.org/)
- [Github](https://www.github.com/)
- [AWS](https://aws.amazon.com/es/)
- [Docker](https://www.docker.com/)
- [Java Keytool](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/keytool.html)

## Author
- [orlandoagk - Github](https://www.github.com/orlandoagk)
- [Orlando Gelves - Linkedin](https://www.linkedin.com/in/orlando-antonio-gelves-kerguelen-11445b1a5/)

## References
- [Docker](https://docs.docker.com)
- [Java Keytool](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/keytool.html)
- [Statement](http://campusvirtual.escuelaing.edu.co/moodle/mod/assign/view.php?id=77528)

## Licence
This project is licensed under the GNU General Public License v3.0 - see the [LICENSE](/LICENSE) file for more details.
