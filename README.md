# FoT-Gateway-IoT-Service

## Introduction

The FoT-Gateway-IoT-Service exposes sensor data of IoT system through a RESTful Web Service. It accesses data stored in local database, managed by [fot-gateway-local-storage](https://github.com/WiserUFBA/fot-gateway-local-storage), allowing users get data and information about sensors in JSON format.

This module depends of modules [fot-gateway-mapping-devices](https://github.com/WiserUFBA/fot-gateway-mapping-devices) and [fot-gateway-local-storage](https://github.com/WiserUFBA/fot-gateway-local-storage). They need to be installed and started before FoT-Gateway-IoT-Service.

## Deploy to Maven Repo

To deploy this repo into our custom maven repo, change pom according to the new version and after that execute the following command. Please ensure that both wiser-mvn-repo and this repo are on the same folder.

```sh
mvn -DaltDeploymentRepository=release-repo::default::file:../wiser-mvn-repo/releases/ deploy
```

## Installation

To install this bundle using our custom maven support execute the following commands in Karaf Shell:

```sh
config:edit org.ops4j.pax.url.mvn 
config:property-append org.ops4j.pax.url.mvn.repositories ", https://github.com/WiserUFBA/wiser-mvn-repo/raw/master/releases@id=wiser"
config:update
mvn:br.ufba.dcc.wiser.soft_iot/fot-gateway-local-storage/1.0.0
mvn:br.ufba.dcc.wiser.soft_iot/fot-gateway-mapping-devices/1.0.0
mvn:br.ufba.dcc.wiser.soft_iot/fot-gateway-iot-service/1.0.0
```

## Support and development

<p align="center">
	Developed by Leandro Andrade at </br>
  <img src="https://wiki.dcc.ufba.br/pub/SmartUFBA/ProjectLogo/wiserufbalogo.jpg"/>
</p>
