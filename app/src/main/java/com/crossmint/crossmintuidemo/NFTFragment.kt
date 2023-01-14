package com.crossmint.crossmintuidemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.navArgs
import com.crossmint.crossmint_ui.models.NFT
import com.crossmint.crossmint_ui.ui.NFTDetailsFragment

class NFTFragment : Fragment() {
    private lateinit var nft: NFT
    private val arguments: NFTFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let { args ->
            nft = args.nft
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nft, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.commit {
            val nftGridFragment = NFTDetailsFragment.newInstance(nft)
            add(R.id.nftContainerView, nftGridFragment)
        }
    }
}