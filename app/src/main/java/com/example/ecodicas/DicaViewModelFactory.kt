package com.example.ecodicas

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DicaViewModelFactory (private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DicaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DicaViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}