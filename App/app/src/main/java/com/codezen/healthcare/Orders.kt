package com.codezen.healthcare

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_orders.*

data class Order(
        val status: String = "",
        val date: String = "",
)

class  OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class Orders : AppCompatActivity() {
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        val query = db.collection("orders").orderBy("datetime", Query.Direction.DESCENDING)
        val options = FirestoreRecyclerOptions.Builder<Order>().setQuery(query, Order::class.java)
            .setLifecycleOwner(this).build()
        val adapter = object : FirestoreRecyclerAdapter<Order, OrderViewHolder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
                val view = LayoutInflater.from(this@Orders).inflate(android.R.layout.simple_list_item_2, parent, false)
                return OrderViewHolder(view)
            }

            override fun onBindViewHolder(holder: OrderViewHolder, position: Int, model: Order) {
                val tvStatus: TextView = holder.itemView.findViewById(android.R.id.text1)
                val tvDate: TextView = holder.itemView.findViewById(android.R.id.text2)
                tvStatus.text = model.status
                tvDate.text = model.date

                tvDate.setTextColor(Color.parseColor("#000000"))
                tvDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)

                tvStatus.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                tvStatus.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)


                if(model.status == "Pending"){
                    tvStatus.setTextColor(Color.parseColor("#dbba00"))
                }
                else if(model.status == "Packing"){
                    tvStatus.setTextColor(Color.parseColor("#ffaa00"))
                }
                else if(model.status == "Delivering"){
                    tvStatus.setTextColor(Color.parseColor("#0091ff"))
                }
                else if(model.status == "Done"){
                    tvStatus.setTextColor(Color.parseColor("#00ff00"))
                }
                else {
                    tvStatus.setTextColor(Color.parseColor("#ff0000"))
                }

                val documentId = snapshots.getSnapshot(position).id

                holder.itemView.setOnClickListener{
                    changePageToSingleOrderView(documentId)
                }
            }
        }

        rvOrders.adapter = adapter
        rvOrders.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.button_profile){
            startActivity(Intent(this, Profile::class.java))
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    fun changePageToSingleOrderView(docID: String){
        val intent = Intent(this, SingleOrderView::class.java)
        intent.putExtra("docID", docID)
        startActivity(intent)
    }

    fun addNewOrder(view: View){
        startActivity(Intent(this, NewOrder::class.java))
        finish()
    }
}