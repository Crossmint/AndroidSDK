package com.crossmint.crossmint_ui.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NFTList(
    val nftList: List<NFT>
): Serializable, Parcelable

@Parcelize
data class NFT (
    val chain: String,
    val mintHash: String,
    val metadata: Metadata,
    val locator: String
): Serializable, Parcelable {

    data class Metadata(
        val name: String,
        val symbol: String,
        val description: String,
        @SerializedName("seller_fee_basis_points")
        val sellerFeeBasisPoints: Int,
        val image: String,
        val attributes: List<Attribute>,
        val properties: Properties
    ): Serializable {

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
}