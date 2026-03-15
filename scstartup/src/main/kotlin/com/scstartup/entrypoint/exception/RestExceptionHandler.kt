package com.scstartup.entrypoint.exception

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.scstartup.core.exception.EmpreendimentoNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {

    data class ErrorResponse(
        val timestamp: LocalDateTime = LocalDateTime.now(),
        val status: Int,
        val error: String,
        val path: String?,
        val errors: List<FieldErrorResponse>? = null
    )

    data class FieldErrorResponse(
        val field: String,
        val message: String
    )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(
        ex: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errors = ex.bindingResult.fieldErrors.map {
            FieldErrorResponse(it.field, it.defaultMessage ?: "Erro de validação")
        }
        val body = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = "Validation error",
            path = request.getDescription(false).removePrefix("uri="),
            errors = errors
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val rootCause = ex.rootCause
        val message = when (rootCause) {
            is MismatchedInputException -> {
                val path = rootCause.path.joinToString(".") { it.fieldName ?: "?" }
                "Campo obrigatório ausente ou tipo inválido: $path"
            }
            else -> ex.localizedMessage ?: "JSON malformado ou campo obrigatório ausente"
        }
        val body = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = message,
            path = request.getDescription(false).removePrefix("uri="),
            errors = null
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body)
    }

    @ExceptionHandler(EmpreendimentoNotFoundException::class)
    fun handleEmpreendimentoNotFound(
        ex: EmpreendimentoNotFoundException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        println("[DEBUG] Handler EmpreendimentoNotFoundException chamado!")
        val body = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = ex.localizedMessage ?: "Empreendimento não encontrado",
            path = request.getDescription(false).removePrefix("uri="),
            errors = null
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body)
    }

    @ExceptionHandler(IllegalArgumentException::class, IllegalStateException::class)
    fun handleIllegalArgument(
        ex: RuntimeException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val body = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = ex.localizedMessage ?: "Requisição inválida",
            path = request.getDescription(false).removePrefix("uri="),
            errors = null
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(
        ex: Exception,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val body = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = ex.localizedMessage ?: "Erro interno",
            path = request.getDescription(false).removePrefix("uri=")
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body)
    }
}
