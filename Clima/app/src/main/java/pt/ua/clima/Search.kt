package pt.ua.clima

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Search: AppCompatActivity(){

    var searchResultsLayout: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val profileButton = findViewById<Button>(R.id.profileButtonS)
        profileButton.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        val definitionsButton = findViewById<Button>(R.id.definitionsButtonS)
        definitionsButton.setOnClickListener{
            val intent = Intent(this, Definitions::class.java)
            startActivity(intent)
        }

        searchResultsLayout = findViewById<LinearLayout>(R.id.querieResults)
        val searchedText = (findViewById<EditText>(R.id.searchText)).text
        val search = findViewById<Button>(R.id.search)
        search.setOnClickListener{
            doSearch(searchedText.toString())
        }
    }

    fun doSearch(searchedText: String?){
        searchResultsLayout?.removeAllViews()
        //pesquisar na db
    }

    fun createQuerieView(searchResult: String){
        val resultView = TextView(applicationContext)
        //criar view
    }
}