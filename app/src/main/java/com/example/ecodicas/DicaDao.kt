package com.example.ecodicas

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Insert
import androidx.lifecycle.LiveData

@Dao
interface DicaDao {

    @Query("SELECT * FROM DicaModel")
    fun getAll(): LiveData<List<DicaModel>>

    @Insert
    fun insert(dica: DicaModel)

    @Delete
    fun delete(dica: DicaModel)

}