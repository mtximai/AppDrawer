package com.example.appdrawer.ui.test1

import com.google.gson.annotations.SerializedName

data class Area (
    @SerializedName("CentroCusto")
    var codigo : String,

    @SerializedName("DescricaoCentroCusto")
    var descricao : String
)
