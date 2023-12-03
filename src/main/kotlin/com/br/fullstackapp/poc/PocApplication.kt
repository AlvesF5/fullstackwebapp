package com.br.fullstackapp.poc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan("com.br.fullstackapp.poc")
@ConfigurationPropertiesScan
@SpringBootApplication
class PocApplication

fun main(args: Array<String>) {
	runApplication<PocApplication>(*args)
}
