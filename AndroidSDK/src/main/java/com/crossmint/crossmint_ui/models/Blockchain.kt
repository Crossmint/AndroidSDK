package com.crossmint.crossmint_ui.models

import com.google.gson.annotations.SerializedName

enum class Blockchain {
    @SerializedName("solana")
    SOLANA,
    @SerializedName("ethereum")
    ETHEREUM,
    @SerializedName("polygon")
    POLYGON
}