package com.cts.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations={
		"classpath:/spring/accesoDatos/daos.xml","classpath:/spring/biz/controladores.xml","classpath:/spring/presentacion/actions.xml",
		"classpath:/spring/presentacion/comun.xml"
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
