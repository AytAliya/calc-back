package calc.calc.exceptions

import calc.calc.models.responses.ExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ControllerAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(exception: ResourceNotFoundException): ResponseEntity<ExceptionResponse> {
        val errorBody = ExceptionResponse(exception.message, HttpStatus.NOT_FOUND.reasonPhrase)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(exception: BadRequestException): ResponseEntity<ExceptionResponse> {
        val errorBody = ExceptionResponse(exception.message, HttpStatus.BAD_REQUEST.reasonPhrase)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody)
    }
}