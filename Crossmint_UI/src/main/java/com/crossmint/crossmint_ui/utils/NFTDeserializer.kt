package com.crossmint.crossmint_ui.utils

import com.crossmint.crossmint_ui.models.EVMNFT
import com.crossmint.crossmint_ui.models.NFT
import com.crossmint.crossmint_ui.models.SolanaNFT
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class NFTDeserializer : JsonDeserializer<NFT> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): NFT? {
        return when (json?.asJsonObject?.get("chain")?.asString) {
            "solana" -> {
                val nft: SolanaNFT? = context?.deserialize(json, SolanaNFT::class.java)
                return nft?.let { NFT.Solana(it) }
            }
//            "ethereum" -> context?.deserialize(json, EVMNFT::class.java)
//            "polygon" -> context?.deserialize(json, EVMNFT::class.java)
            else ->  {
                val nft: EVMNFT? = context?.deserialize(json, EVMNFT::class.java)
                return nft?.let { NFT.EVM(it) }
            }
        }
    }
}