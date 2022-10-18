package com.ismin.android

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class BookViewHolder(rootView: View): ViewHolder(rootView) {
    var title = rootView.findViewById<TextView>(R.id.r_book_txv_title)
    var author = rootView.findViewById<TextView>(R.id.r_book_txv_author)
    var date = rootView.findViewById<TextView>(R.id.r_book_txv_date)
}