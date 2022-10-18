package com.ismin.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private val books: Bookshelf) : RecyclerView.Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(
            R.layout.row_book, parent,
            false
        )
        return BookViewHolder(row)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books.getAllBooks()[position];
        holder.title.text = book.title
        holder.author.text = book.author
        holder.date.text = book.date
    }

    override fun getItemCount(): Int {
        return books.getTotalNumberOfBooks()
    }
}