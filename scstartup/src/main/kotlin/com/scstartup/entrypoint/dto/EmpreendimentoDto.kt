package com.scstartup.entrypoint.dto

import com.scstartup.core.enums.Segmento
import com.scstartup.core.enums.Status
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class EmpreendimentoCreateRequest(
    @field:NotBlank
    val nome: String,
    @field:NotBlank
    val empreendedor: String,
    @field:NotBlank
    val municipio: String,
    @field:NotNull
    val segmento: Segmento,
    @field:NotBlank
    val contato: String,
    @field:NotNull
    val status: Status
)

data class EmpreendimentoUpdateRequest(
    val nome: String? = null,
    val empreendedor: String? = null,
    val municipio: String? = null,
    val segmento: Segmento? = null,
    val contato: String? = null,
    val status: Status? = null
)

data class EmpreendimentoResponse(
    val id: Long,
    val nome: String,
    val empreendedor: String,
    val municipio: String,
    val segmento: Segmento,
    val contato: String,
    val status: Status
)