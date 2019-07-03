package no.nav.maksdato

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MaksdatoApplication

fun main(args: Array<String>) {
	runApplication<MaksdatoApplication>(*args)
}
