package com.crossmint.android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.crossmint.crossmint_ui.R
import com.crossmint.crossmint_ui.databinding.FragmentNftDetailsBinding
import com.crossmint.android.models.NFT

class NFTDetailsFragment : Fragment() {
    private lateinit var nft: NFT

    companion object {
        fun newInstance(nft: NFT): NFTDetailsFragment {
            val fragment = NFTDetailsFragment()
            fragment.arguments = buildBundle(nft)
            return fragment
        }
    }

    private var _binding: FragmentNftDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nft = requireArguments().getSerializable("nft") as NFT
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNftDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            image.load(nft.metadata.image) {
                error(R.drawable.ic_launcher_background)
                placeholder(R.drawable.ic_launcher_background)
            }
            title.text = nft.metadata.name
            brand.text = nft.chain.toString()
            desc.text = nft.metadata.description
        }
    }
}

private fun buildBundle(nft: NFT): Bundle {
    val bundle = Bundle()
    bundle.putSerializable("nft", nft)
    return bundle
}