package com.spring.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.TimeZone;

@SpringBootApplication
public class JwtWithSpringSecurityApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		SpringApplication.run(JwtWithSpringSecurityApplication.class, args);
		System.out.println("\n\n");
		System.err.println("PORT : localhost8080");
		System.err.println("documentation : "+"http://localhost:8080/swagger-ui/index.html#/");
		System.err.println("  *****    *******  *******       *****   *******    *****    ******   *******" );
		System.err.println(" *     *   *      *    *         *           *      *     *   *     *     *   " );
		System.err.println("*       *  *      *    *         *           *     *       *  *     *     *   " );
		System.err.println("*       *  *******     *          *****      *     *       *  ******      *   " );
		System.err.println("*********  *           *               *     *     *********  *   *       *   " );
		System.err.println("*       *  *           *               *     *     *       *  *    *      *   " );
		System.err.println("*       *  *        *******       *****      *     *       *  *     *     *   " );
		}

}
