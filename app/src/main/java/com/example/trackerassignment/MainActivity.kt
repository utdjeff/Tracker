package com.example.trackerassignment


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
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

        val titleEditText = findViewById<EditText>(R.id.titleEditText)
        val genreEditText = findViewById<EditText>(R.id.genreEditText)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val addButton = findViewById<Button>(R.id.addButton)
        val removeLastButton = findViewById<Button>(R.id.removeLastButton)
        val removeAllButton = findViewById<Button>(R.id.removeAllButton)

        addButton.setOnClickListener {
            Log.d("add bttn", "onCreate: function worked")
            val title = titleEditText.text.toString()
            val genre = genreEditText.text.toString()
            val rating = ratingBar.rating.toInt().toString()
            if (title.isNotEmpty() && genre.isNotEmpty() && rating.isNotEmpty()) {
                Log.d("MainActivity", "Adding book: $title, $genre, $rating")
                bookList.add(BookItem(title, genre, rating))
                adapter.notifyItemInserted(bookList.size - 1)
                titleEditText.text.clear()
                genreEditText.text.clear()
                ratingBar.rating = 0F
                Log.d("MainActivity", "Book list size: ${bookList.size}")
            }
            else
                Log.d("MainActivity", "no input found ")
        }

        removeLastButton.setOnClickListener {

            if (bookList.isNotEmpty()) {
                bookList.removeAt(bookList.size - 1)
                adapter.notifyItemRemoved(bookList.size)
                Log.d("MainActivity", "Removed last book. Book list size: ${bookList.size}")

            }
        }

        removeAllButton.setOnClickListener {
            val size = bookList.size
            if (size > 0)
            {
                bookList.clear()
                adapter.notifyItemRangeRemoved(0,bookList.size)
                Log.d("MainActivity"," cleared all books. you have ${bookList.size} books left")
            }
        }
    }
}
