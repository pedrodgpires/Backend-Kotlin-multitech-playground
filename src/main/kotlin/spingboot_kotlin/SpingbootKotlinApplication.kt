package spingboot_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpingbootKotlinApplication

fun main(args: Array<String>) {
	runApplication<SpingbootKotlinApplication>(*args)
}
