package com.crossmint.crossmintuidemo

import android.net.Uri
import com.crossmint.crossmint_ui.models.NFT
import com.google.gson.Gson
import java.net.URL
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

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
                    println(json)
                    val typeToken = object : TypeToken<List<NFT>>() {}.type
                    val nfts = Gson().fromJson<List<NFT>>(json, typeToken)
                    callback(nfts)
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