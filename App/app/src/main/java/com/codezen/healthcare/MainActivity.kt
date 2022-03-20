package com.codezen.healthcare
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

data class  Test(
        val test: String = "",
)

class  TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


class MainActivity : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val query = db.collection("test")
        val options = FirestoreRecyclerOptions.Builder<Test>().setQuery(query, Test::class.java)
                .setLifecycleOwner(this).build()
        val adapter = object: FirestoreRecyclerAdapter<Test, TestViewHolder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
                val view = LayoutInflater.from(this@MainActivity).inflate(android.R.layout.simple_list_item_2, parent, false)
                return TestViewHolder(view)
            }

            override fun onBindViewHolder(holder: TestViewHolder, position: Int, model: Test) {
                val tvName: TextView = holder.itemView.findViewById(android.R.id.text1)
                val tvEmojies: TextView = holder.itemView.findViewById(android.R.id.text2)
                tvName.text = model.test
                tvEmojies.text = model.test
            }
        }
        rvTest.adapter = adapter
        rvTest.layoutManager = LinearLayoutManager(this)
    }
}