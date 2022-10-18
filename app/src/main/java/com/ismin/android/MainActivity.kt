package com.ismin.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rcvBooks: RecyclerView

    private val bookshelf = Bookshelf()
    private  var bookAdapter = BookAdapter(bookshelf)

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

        rcvBooks = findViewById(R.id.a_main_rcv_books);
        rcvBooks.adapter = bookAdapter

        var linearLayoutManager = LinearLayoutManager(this)
        rcvBooks.layoutManager = linearLayoutManager

        val btnCreateBook = findViewById<Button>(R.id.a_main_btn_create_book);

        btnCreateBook.setOnClickListener {
            val intent = Intent(this, CreateBookActivity::class.java)
            startForResult.launch(intent)
        }
    }
}