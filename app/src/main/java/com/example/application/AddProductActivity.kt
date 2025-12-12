package com.example.application

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddProductActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        val etName = findViewById<EditText>(R.id.etName)
        val etPrice = findViewById<EditText>(R.id.etPrice)
        val etDesc = findViewById<EditText>(R.id.etDesc)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        // ვირჩევთ მთავარ საქაღალდეს "Products"
        database = FirebaseDatabase.getInstance().getReference("Products")

        btnAdd.setOnClickListener {
            val name = etName.text.toString().trim()
            val price = etPrice.text.toString().trim()
            val desc = etDesc.text.toString().trim()

            if (name.isNotEmpty() && price.isNotEmpty()) {

                // ვქმნით უნიკალურ ID-ს
                val productId = database.push().key!!
                val product = Product(productId, name, price, desc)

                // ⚠️ ვინახავთ პირდაპირ, ავტორიზაციის გარეშე!
                // ძველი ვერსია: database.child(userId).child(productId)...
                // ახალი (სატესტო) ვერსია:
                database.child(productId).setValue(product)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Product added successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }

            } else {
                Toast.makeText(this, "Please fill fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}