package com.crossmint.crossmint_ui.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import kotlinx.parcelize.Parcelize

interface NFTType {
    val chain: Blockchain
    val metadata: MetadataType
}

interface MetadataType {
    val name: String?
    val description: String?
    val image: String
    val collection: NFT.Collection?
    val attributes: List<NFT.Metadata.Attribute>
    val animationUrl: String?
    val externalUrl: String?
}

@Parcelize
sealed class NFT: NFTType, Serializable, Parcelable {
    class Solana(private val nft: SolanaNFT) : NFT() {
        override val chain: Blockchain
            get() = nft.chain
        override val metadata: MetadataType
            get() = nft.metadata
    }
    class EVM(private val nft: EVMNFT) : NFT() {
        override val chain: Blockchain
            get() = nft.chain
        override val metadata: MetadataType
            get() = nft.metadata
    }

    data class Metadata(val x: Int): Serializable {
        data class Attribute(
            val trait_type: String,
            val value: String
        ): Serializable

        data class Properties(
            val files: List<File>,
            val creators: List<Creator>,
            val category: String?,
        ): Serializable {

            data class File(
                val uri: String,
                val type: String
            ): Serializable

            data class Creator(
                val address: String,
                val share: String
            ): Serializable
        }
    }

    data class Collection(val name: String): Serializable
}