package com.example.daggerdemoapplication

import android.R
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.daggerdemoapplication.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DogFactViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as Application).appComponent.inject(this)
        viewModel = ViewModelProvider(this,viewModelFactory)[DogFactViewModel::class.java]
        setupUi()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dogFacts.observe(this){
            if(it == null){
                Toast.makeText(this, "API failed", Toast.LENGTH_SHORT).show()
            } else{
                showList(it)
            }
        }
    }

    private fun setupUi() {
        binding.fetch.setOnClickListener {
            var count = 5
            if(binding.textInputEditText.text.isNullOrBlank().not()){
                count = binding.textInputEditText.text.toString().toInt()
            }
            viewModel.fetchDogFacts(count)
            hideKeyboard(it)
        }
    }

    private fun showList(facts: DogFact){
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, facts.facts ?: emptyList())
        binding.simpleListView.adapter = adapter
    }
    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}