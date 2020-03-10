package com.example.appdrawer.entity

import com.google.gson.annotations.SerializedName
import java.util.*

data class LicitacaoModel (

    @SerializedName("IdLicitacao")
    var IdLicitacao: String,

    @SerializedName("DescricaoTipoEventoPUBNET")
    var DescricaoTipoEventoPUBNET: String,

    @SerializedName("SiglaUnidadeFiscalizacao")
    var SiglaUnidadeFiscalizacao: String,

    @SerializedName("Relator")
    var Relator : String,

    @SerializedName("DataPublicacao")
    var DataPublicacao : Date,

    @SerializedName("NumeroLicitacao")
    var NumeroLicitacao : String,

    @SerializedName("DescricaoModalidade")
    var DescricaoModalidade : String,

    @SerializedName("NumeroPA")
    var NumeroPA : String,

    @SerializedName("DescricaoOrgao")
    var DescricaoOrgao : String,

    @SerializedName("Objeto")
    var Objeto : String,

    @SerializedName("Valor")
    var Valor : Double,

    @SerializedName("DescricaoSituacaoLicitacao")
    var DescricaoSituacaoLicitacao : String,

    @SerializedName("DescricaoTipoLicitacaoPUBNET")
    var DescricaoTipoLicitacaoPUBNET : String
)