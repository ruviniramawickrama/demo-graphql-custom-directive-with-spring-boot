/**
 * @author  Ruvini Ramawickrama
 */
package com.example.demo;

import com.example.demo.directive.UppercaseDirective;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@SpringBootApplication
public class DemoGraphqlCustomDirectiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoGraphqlCustomDirectiveApplication.class, args);
	}

	@Bean
	public RuntimeWiringConfigurer runtimeWiringConfigurer() {
		return builder -> builder.directiveWiring(new UppercaseDirective());
	}

}
