package com.crossmint.crossmint_ui.utils

import com.crossmint.crossmint_ui.models.EVMNFT
import com.crossmint.crossmint_ui.models.NFT
import com.crossmint.crossmint_ui.models.SolanaNFT
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class NFTDeserializer : JsonDeserializer<NFT> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): NFT? {
        return when (val chain = json?.asJsonObject?.get("chain")?.asString) {
            "solana" ->
                context?.deserialize<SolanaNFT>(json, SolanaNFT::class.java)?.let { NFT.Solana(it) }
            "ethereum", "polygon" ->
                context?.deserialize<EVMNFT>(json, EVMNFT::class.java)?.let { NFT.EVM(it) }
            else ->
                throw JsonParseException("Chain $chain not supported")
        }
    }
}