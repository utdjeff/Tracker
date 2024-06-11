package com.example.trackerassignment


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookAdapter
    private lateinit var bookList: ArrayList<BookItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookList = ArrayList()
        adapter = BookAdapter(bookList)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val titleEditText = findViewById<EditText>(R.id.titleEditText)
        val genreEditText = findViewById<EditText>(R.id.genreEditText)
        val ratingEditText = findViewById<EditText>(R.id.ratingEditText)
        val addButton = findViewById<Button>(R.id.addButton)
        val removeLastButton = findViewById<Button>(R.id.removeLastButton)
        val removeAllButton = findViewById<Button>(R.id.removeAllButton)

        addButton.setOnClickListener {
            Log.d("add bttn", "onCreate: function worked")
            val title = titleEditText.text.toString()
            val genre = genreEditText.text.toString()
            val rating = ratingEditText.text.toString()
            if (title.isNotEmpty() && genre.isNotEmpty() && rating.isNotEmpty()) {
                bookList.add(BookItem(title, genre, rating))
                adapter.notifyItemInserted(bookList.size - 1)
                titleEditText.text.clear()
                genreEditText.text.clear()
                ratingEditText.text.clear()
            }
        }

        removeLastButton.setOnClickListener {
            if (bookList.isNotEmpty()) {
                bookList.removeAt(bookList.size - 1)
                adapter.notifyItemRemoved(bookList.size)
            }
        }

        removeAllButton.setOnClickListener {
            bookList.clear()
            adapter.notifyItemRangeRemoved(0, bookList.size)        }
    }
}
