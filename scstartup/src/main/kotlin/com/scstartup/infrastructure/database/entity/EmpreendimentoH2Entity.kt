package com.scstartup.infrastructure.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "empreendimentos")
class EmpreendimentoH2Entity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var nome: String,

    @Column(nullable = false)
    var empreendedor: String,

    @Column(nullable = false)
    var municipio: String,

    @Column(nullable = false)
    var segmento: String,

    @Column(nullable = false)
    var contato: String,

    @Column(nullable = false)
    var status: String
)