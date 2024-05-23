package tech.developer.catalogodecarros.utils.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun generalExceptionHandler(exception: Exception): ResponseEntity<ApiError> {
        val error = ApiError(exception.message)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(MakeException::class)
    fun makeExceptionHandler(exception: Exception): ResponseEntity<ApiError> {
        val error = ApiError(exception.message)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(ModelException::class)
    fun modelExceptionHandler(exception: Exception): ResponseEntity<ApiError> {
        val error = ApiError(exception.message)
        return ResponseEntity(error, error.status)
    }
}