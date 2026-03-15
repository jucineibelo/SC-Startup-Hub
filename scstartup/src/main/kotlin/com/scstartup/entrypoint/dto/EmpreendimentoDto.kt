package com.scstartup.entrypoint.dto

data class EmpreendimentoCreateRequest(
    val nome: String,
    val empreendedor: String,
    val municipio: String,
    val segmento: String,
    val contato: String,
    val status: Boolean
)

data class EmpreendimentoUpdateRequest(
    val nome: String? = null,
    val empreendedor: String? = null,
    val municipio: String? = null,
    val segmento: String? = null,
    val contato: String? = null,
    val status: Boolean? = null
)

data class EmpreendimentoResponse(
    val id: Long,
    val nome: String,
    val empreendedor: String,
    val municipio: String,
    val segmento: String,
    val contato: String,
    val status: Boolean
)