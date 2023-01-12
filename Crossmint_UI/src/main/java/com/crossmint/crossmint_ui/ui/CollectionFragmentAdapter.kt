package com.crossmint.crossmint_ui.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.crossmint.crossmint_ui.R
import com.crossmint.crossmint_ui.databinding.ItemCollectionsBinding
//import com.crossmint.mobile.databinding.ItemCollectionsBinding
import com.crossmint.mobile.models.NFT


class CollectionFragmentAdapter : RecyclerView.Adapter<CollectionFragmentAdapter.ViewHolder>() {

    var callback: ((nft: NFT)-> Unit)? = null

    //This is just the default value it needs to be set using updateCardWidth(cardWidth: Int)
    private var cardWidth = 160
    private var nfts: MutableList<NFT> = mutableListOf()

//    @SuppressLint("NotifyDataSetChanged")
    fun updateCollectionList(nftList: List<NFT>){
        nfts.clear()
        nfts.addAll(nftList)
        notifyDataSetChanged()
    }

    fun updateCardWidth(cardWidth: Int){
        this.cardWidth = cardWidth
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemCollectionsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(nfts[position], callback ,cardWidth)
    }

    override fun getItemCount() = nfts.size



    class ViewHolder(private val root: ItemCollectionsBinding) : RecyclerView.ViewHolder(root.root) {

        fun bind(nft: NFT, callback: ((nft: NFT) -> Unit)?, cardWidth: Int) {
            //Making cardImage Square so width and height should be same
//            root.cardImage.layoutParams.width = cardWidth
//            root.cardImage.layoutParams.height = cardWidth
            root.apply {
//                image.load(nft.metadata.image) {
////                    error(R.drawable.ic_launcher_background)
////                    placeholder(R.drawable.ic_launcher_background)
//                }
//                title.text = nft.metadata.name
//                desc.text = nft.metadata.sellerFeeBasisPoints.toString()
            }
            root.cardImage.setOnClickListener{ callback?.invoke(nft) }
        }

    }


}