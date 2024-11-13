package com.example.ecodicas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DicaViewModel(application: Application) : AndroidViewModel(application) {
    private val dicaDao: DicaDao
    val dicasLiveData: LiveData<List<DicaModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            DicaDatabase::class.java,
            "dicas_database"
        ).build()
        dicaDao = database.dicaDao()
        dicasLiveData = dicaDao.getAll()
    }

    fun addDica(titulo: String, descricao:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newItem = DicaModel(titulo = titulo, descricao =  descricao)
            dicaDao.insert(newItem)
        }
    }


    fun removeDica(dica: DicaModel) {
        viewModelScope.launch(Dispatchers.IO) {
            dicaDao.delete(dica)
        }
    }
}