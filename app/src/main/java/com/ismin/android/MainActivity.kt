package com.ismin.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var rcvBooks: RecyclerView

    private val bookshelf = Bookshelf()
    private var bookAdapter = BookAdapter(bookshelf)

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val createdBook = result.data?.getSerializableExtra(CREATED_BOOK) as Book
            bookshelf.addBook(createdBook)
            bookAdapter.notifyDataSetChanged()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val book = Book("Le Petit Prince", "Antoine de Saint-Exupéry", "1943")
        bookshelf.addBook(book)
        val anotherBook = Book("American Gods", "Neil Gaiman", "2001")
        bookshelf.addBook(anotherBook)

        rcvBooks = findViewById(R.id.a_main_rcv_books);
        rcvBooks.adapter = bookAdapter

        var linearLayoutManager = LinearLayoutManager(this)
        rcvBooks.layoutManager = linearLayoutManager

        val btnCreateBook = findViewById<FloatingActionButton>(R.id.a_main_btn_create_book);

        btnCreateBook.setOnClickListener {
            val intent = Intent(this, CreateBookActivity::class.java)
            startForResult.launch(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_clean -> {
                val numberOfBooks = bookshelf.getTotalNumberOfBooks()
                bookshelf.clean()
                bookAdapter.notifyItemRangeRemoved(0, numberOfBooks)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}