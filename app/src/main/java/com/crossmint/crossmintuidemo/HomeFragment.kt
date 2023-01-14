package com.crossmint.crossmintuidemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {
    private var loadingIndicator: ProgressBar? = null
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingIndicator = getView()?.findViewById(R.id.loadingIndicator)
        getView()?.findViewById<Button>(R.id.loadNFTsButton)?.setOnClickListener {
            viewModel.loadWallet()
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.loading.observe(this) { loading ->
            loadingIndicator?.isVisible = loading
        }

        viewModel.nfts.observe(this) { nfts ->
            val action = HomeFragmentDirections.actionHomeFragmentToWalletFragment(nfts.toTypedArray(), 2)
            findNavController().navigate(action)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}