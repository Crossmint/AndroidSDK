package com.crossmint.android.models

import com.google.gson.annotations.SerializedName

enum class Blockchain {
    @SerializedName("solana")
    SOLANA,
    @SerializedName("ethereum")
    ETHEREUM,
    @SerializedName("polygon")
    POLYGON
}