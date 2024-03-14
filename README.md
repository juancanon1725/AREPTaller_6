# AREP - Taller 6

Juan Pablo Poveda Cañon

## Description

## Getting Started

### Dependencies

* Describe any prerequisites, libraries, OS version, etc., needed before installing program.
* ex. Windows 10

### Installing

* How/where to download your program
* Any modifications needed to be made to files/folders

### Executing program

* How to run the program
* Step-by-step bullets
```
code blocks for commands
```

## Arquitectura

Se desarrolló una aplicación conforme a la arquitectura sugerida y se desplegó en AWS mediante el uso de EC2 y Docker. La estructura de la aplicación comprende los siguientes elementos:

* Servicio de MongoDB: Una instancia de MongoDB que se ejecuta en un contenedor Docker dentro de una máquina virtual EC2.

* LogService: Un servicio REST que recibe cadenas de texto, las guarda en la base de datos y devuelve un objeto JSON con las últimas 10 cadenas almacenadas junto con la fecha de registro.

* Aplicación Web LogRoundRobin: Esta aplicación web incluye un cliente que permite a los usuarios introducir mensajes. Cada mensaje enviado se dirige al servicio REST, el cual emplea un algoritmo de balanceo de carga Round Robin. Este servicio distribuye el procesamiento del mensaje y la respuesta a tres instancias del servicio LogService.

## Authors

Contributors names and contact info

ex. Dominique Pizzie  
ex. [@DomPizzie](https://twitter.com/dompizzie)

## Version History

* 0.2
    * Various bug fixes and optimizations
    * See [commit change]() or See [release history]()
* 0.1
    * Initial Release
