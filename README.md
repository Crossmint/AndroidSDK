# Crossmint Android SDK

## Installation

The package is available on Maven Central, simply add:
```groovy
implementation "io.github.crossmint:AndroidSDK:0.0.4"
```
to the `dependencies` section of your `build.gradle` file, and you're good to go!

## Usage

For exmple usage, clone this repository locally and check out the app provided. 
Included in the demo is a real-world code example to fetch NFTs from an API and display their contents in-app.

#### `NFTDeserializer`

The structure of an NFT takes different form depending on which blockchain the NFT is deployed on.
In order to simplify working with APIs that provide NFTs in different forms, we've supplied a chain-agnostic `NFTDeserializer`.
You can easily introduce this into GSON using the following code:
```
import com.crossmint.android.utils.NFTDeserializer

val gson = GsonBuilder().apply {
    registerTypeAdapter(NFT::class.java, NFTDeserializer())
}.create()

val nfts = gson.fromJson(json, Array<NFT>::class.java)
```
