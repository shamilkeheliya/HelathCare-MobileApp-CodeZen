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

        val query = db.collection("orders")
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
            }
        }
        rvOrders.adapter = adapter
        rvOrders.layoutManager = LinearLayoutManager(this)
    }
}