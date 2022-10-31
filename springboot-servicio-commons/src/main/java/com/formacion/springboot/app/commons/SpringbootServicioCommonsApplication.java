package com.formacion.springboot.app.commons;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class}) //Quitamos la configuración  del datasource (bbdd)
public class SpringbootServicioCommonsApplication {

//Al ser un proyecto que será una librería y no será necesario ejecutar, no necesita de la clase main.

}
