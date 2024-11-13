package com.example.ecodicas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.ecodicas.ui.theme.EcoDicasTheme

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: DicaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Eco Dicas"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val dicasAdapter = DicaAdapter { dica ->
            viewModel.removeDica(dica)
        }
        recyclerView.adapter = dicasAdapter

        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)
        val editTextDesc = findViewById<EditText>(R.id.editTextDesc)

        button.setOnClickListener {
            if (editText.text.isEmpty()) {
                editText.error = "Preencha o titulo"
                return@setOnClickListener
            }
            if (editTextDesc.text.isEmpty()) {
                editText.error = "Preencha a descricao"
                return@setOnClickListener
            }

            viewModel.addDica(editText.text.toString(), editTextDesc.text.toString())
            editText.text.clear()
            editTextDesc.text.clear()


        }

        val viewModelFactory = DicaViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DicaViewModel::class.java)

        viewModel.dicasLiveData.observe(this) { dicas ->
            dicasAdapter.updateItems(dicas)
        }
    }
}