package com.crossmint.crossmintuidemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.crossmint.android.models.NFT
import com.crossmint.android.ui.NFTGridFragment

class WalletFragment : Fragment() {
    private var nfts: List<NFT> = listOf()
    private val arguments: WalletFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let { args ->
            nfts = args.nfts.toList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.commit {
            val nftGridFragment = NFTGridFragment.newInstance(nfts)
            nftGridFragment.setCallback(::showNFTDetails)
            add(R.id.nftGridContainerView, nftGridFragment)
        }
    }

    private fun showNFTDetails(nft: NFT) {
        val showNFTDetails = WalletFragmentDirections.actionWalletFragmentToNFTFragment(nft)
        findNavController().navigate(showNFTDetails)
    }
}