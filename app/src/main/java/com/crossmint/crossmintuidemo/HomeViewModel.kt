package com.crossmint.crossmintuidemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.crossmint.android.models.NFT
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {
    val nfts: MutableLiveData<List<NFT>> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    fun loadWallet() {
        loading.postValue(true)

        viewModelScope.launch {
            NFTService().fetchNFTs(
                Blockchain.SOLANA,
                "D6bDg4DcCpsprQEmnUdcWhpkj2pMDxEJa615oyvS3sQL",
                devnet = true
            ) { nfts ->
                this@HomeViewModel.nfts.postValue(nfts)
            }
        }
    }
}