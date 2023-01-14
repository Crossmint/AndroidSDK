package com.crossmint.crossmintuidemo

import android.net.Uri
import com.crossmint.android.models.NFT
import com.crossmint.android.utils.NFTDeserializer
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import java.net.URL

enum class Blockchain(val chain: String) {
    SOLANA("solana"),
    ETHEREUM("ethereum"),
    POLYGON("polygon")
}

class NFTService {
    fun fetchNFTs(blockchain: Blockchain, address: String, devnet: Boolean = false, callback: (List<NFT>) -> Unit) {
        val client = OkHttpClient()
        val url = url(blockchain, address, devnet)
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("error: $e")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val json = response.body!!.string()
                    val gson = GsonBuilder().apply {
                        registerTypeAdapter(NFT::class.java, NFTDeserializer())
                    }.create()

                    val nfts = gson.fromJson(json, Array<NFT>::class.java)
                    callback(nfts.toList())
                }
            }
        })
    }

    private fun url(blockchain: Blockchain, address: String, devnet: Boolean): URL {
        val builder = Uri.parse("https://demo.crossmint.io/api/wallets/nfts").buildUpon()
            .appendQueryParameter("blockchain", blockchain.chain)
            .appendQueryParameter("address", address)
            .appendQueryParameter("devnet", devnet.toString())

        val urlString = builder.build().toString()
        return URL(urlString)
    }
}