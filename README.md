# fx-exchange
Technical task for Danske Bank

# Features

* Currency exchange API

Method  | Path | Explanation
------------- | ------------- | ------------- |
POST  | /rates/exchange | Convert one currency amount to another |

# Required dependencies to run the project

* Maven (download from https://maven.apache.org/download.cgi)

* JDK 11 (download from https://adoptopenjdk.net/?variant=openjdk11)

# Unnecessary dependencies 

* Docker (download from https://www.docker.com/products/docker-desktop)

## Steps to run without docker compose

* Navigate to the root of the project

* Run "mvn clean install"

* Run "java -jar target/fx-exchange-0.0.1-SNAPSHOT.jar"

* API will become available on http://localhost:9001/

* API Swagger docs are available at http://localhost:9001/swagger-ui.html

## Step to run with docker compose

* Navigate to the root of the project

* Run "mvn clean install"

* Run "docker-compose build"

* Run "docker-compose up"

* API will become available at http://localhost:9001/

* API Swagger docs are available at http://localhost:9001/swagger-ui.html



