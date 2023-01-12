package com.crossmint.crossmint_ui.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.crossmint.crossmint_ui.databinding.FragmentNftGridBinding
import com.crossmint.crossmint_ui.models.NFT
import com.crossmint.crossmint_ui.utils.setColumns
import java.io.Serializable

class NFTGridFragment: Fragment() {
    private lateinit var nfts: List<NFT>
    private var numberOfColumns: Int = 2

    private var _binding: FragmentNftGridBinding? = null
    private val binding get() = _binding!!
    private var collectionFragmentAdapter: CollectionFragmentAdapter = CollectionFragmentAdapter()

    companion object {
        fun newInstance(nfts: List<NFT>, numberOfColumns: Int = 2): NFTGridFragment{
            val fragment = NFTGridFragment()
            fragment.arguments = buildBundle(nfts, numberOfColumns)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nfts = requireArguments().getSerializable("nfts") as List<NFT>? ?: listOf()
        numberOfColumns = requireArguments().getInt("numberOfColumns")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNftGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.collection.apply {
            val width = setColumns(numberOfColumns)
            collectionFragmentAdapter.updateCardWidth(cardWidth = width)
            adapter = collectionFragmentAdapter
            collectionFragmentAdapter.updateCollectionList(nfts)
        }
    }

    fun setCallback(callback: (NFT) -> Unit) {
        collectionFragmentAdapter.callback = callback
    }
}

private fun buildBundle(nfts: List<NFT>, numberOfColumns: Int): Bundle {
    val bundle = Bundle()

    bundle.putInt("numberOfColumns", numberOfColumns)
    bundle.putSerializable("nfts", Serializable::class.java.cast(nfts))

    return bundle
}