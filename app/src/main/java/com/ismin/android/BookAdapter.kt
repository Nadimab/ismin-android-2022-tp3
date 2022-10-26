package com.ismin.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private var books: List<Book>) : RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(
            R.layout.row_book, parent,
            false
        )
        return BookViewHolder(row)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position];
        holder.title.text = book.title
        holder.author.text = book.author
        holder.date.text = book.date
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun refreshData(allBooks: List<Book>) {
        this.books = allBooks;
    }
}