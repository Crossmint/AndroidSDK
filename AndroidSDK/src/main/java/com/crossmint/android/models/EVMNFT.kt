package com.crossmint.android.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class EVMNFT(
    val chain: Blockchain,
    val contractAddress: String,
    val tokenId: String,
    val metadata: Metadata,
): Serializable, Parcelable {
    data class Metadata(
        override val image: String,
        override val collection: NFT.Collection?,
        override val name: String?,
        override val description: String?,
        override val attributes: List<NFT.Metadata.Attribute>,
        @SerializedName("animation_url")
        override val animationUrl: String?,
        @SerializedName("external_url")
        override val externalUrl: String?
    ): MetadataType, Serializable
}
