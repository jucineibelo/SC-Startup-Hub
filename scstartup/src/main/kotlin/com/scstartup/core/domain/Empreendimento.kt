package com.scstartup.core.domain

import com.scstartup.core.enums.Segmento
import com.scstartup.core.enums.Status

class Empreendimento(
    val id: Long?,
    var nome: String,
    var empreendedor: String,
    var municipio: String,
    var segmento: Segmento?,
    var contato: String,
    var status: Status?
)