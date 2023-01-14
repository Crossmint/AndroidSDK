package com.crossmint.android.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class SolanaNFT(
    val chain: Blockchain = Blockchain.SOLANA,
    val mintHash: String,
    val metadata: Metadata,
) : Serializable, Parcelable {
    data class Metadata(
        override val name: String?,
        val symbol: String,
        override val description: String?,
        override val image: String,
        override val collection: NFT.Collection?,
        override val attributes: List<NFT.Metadata.Attribute>,
        val properties: NFT.Metadata.Properties,
        @SerializedName("animation_url")
        override val animationUrl: String?,
        @SerializedName("external_url")
        override val externalUrl: String?,
        @SerializedName("seller_fee_basis_points")
        val sellerFeeBasisPoints: Int,
    ): MetadataType, Serializable
}
