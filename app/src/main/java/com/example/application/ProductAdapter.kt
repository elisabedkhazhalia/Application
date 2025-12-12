package com.example.application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val productList: ArrayList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // ეს მეთოდი ქმნის ახალ View-ს (ბარათს) ჩვენი product_item.xml-ის მიხედვით
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(itemView)
    }

    // ეს მეთოდი ავსებს შექმნილ ბარათს კონკრეტული პროდუქტის მონაცემებით
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.tvName.text = currentItem.name
        holder.tvPrice.text = "$${currentItem.price}" // ფასს წინ დოლარის ნიშანს ვუმატებთ
        holder.tvDesc.text = currentItem.description

        // კალათის ღილაკზე დაჭერის დამუშავება
        holder.btnAddToCart.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Added to cart: ${currentItem.name}", Toast.LENGTH_SHORT).show()
        }
    }

    // აბრუნებს სიაში ელემენტების რაოდენობას
    override fun getItemCount(): Int {
        return productList.size
    }

    // ეს კლასი ინახავს ბარათის შიგნით არსებულ View-ებს
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvProductName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        val tvDesc: TextView = itemView.findViewById(R.id.tvProductDesc)
        val btnAddToCart: ImageView = itemView.findViewById(R.id.btnAddToCart)
    }
}