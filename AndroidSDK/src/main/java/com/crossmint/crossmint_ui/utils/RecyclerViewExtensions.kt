package com.crossmint.crossmint_ui.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setColumns(numberOfColumns: Int): Int {
    this.layoutManager = GridLayoutManager(this.context, numberOfColumns)
    return this.context.resources.displayMetrics.widthPixels / numberOfColumns
}