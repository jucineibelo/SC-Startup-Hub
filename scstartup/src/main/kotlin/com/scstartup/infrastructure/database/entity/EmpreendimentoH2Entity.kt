package com.scstartup.infrastructure.database.entity

import com.scstartup.core.enums.Segmento
import com.scstartup.core.enums.Status
import jakarta.persistence.*

@Entity
@Table(name = "empreendimentos")
class EmpreendimentoH2Entity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "nome")
    var nome: String,

    @Column(name = "empreendedor")
    var empreendedor: String,

    @Column(name = "municipio")
    var municipio: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "segmento")
    var segmento: Segmento,

    @Column(name = "contato")
    var contato: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: Status
)