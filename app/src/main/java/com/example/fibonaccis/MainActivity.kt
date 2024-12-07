package com.example.fibonaccis

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fibonaccis.databinding.ActivityMainBinding

class MainActivity() : AppCompatActivity(), Parcelable {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Use ViewBinding to inflate the layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set Window Insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Handle the button click to generate Fibonacci series
        binding.generateButton.setOnClickListener {
            val input = binding.inputNumber.text.toString().toIntOrNull()

            // Check if input is valid
            if (input != null && input > 0) {
                // Generate and display the Fibonacci series
                val result = generateFibonacci(input)
                binding.textview.text = result
            } else {
                // Show error message if input is invalid
                binding.textview.text = "Please enter a valid number."
            }
        }
    }

    // Function to generate Fibonacci series
    private fun generateFibonacci(input: Int): String {
        var first = 0
        var second = 1
        var result = "$first $second"

        for (i in 3..input) {
            val next = first + second
            result += "$result "+" $next"
            first = second
            second = next
        }
        return result
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

}
