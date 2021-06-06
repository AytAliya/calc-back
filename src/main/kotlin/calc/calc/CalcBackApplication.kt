package calc.calc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CalcBackApplication

fun main(args: Array<String>) {
    runApplication<CalcBackApplication>(*args)
}
