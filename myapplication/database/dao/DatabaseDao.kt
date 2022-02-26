package com.example.myapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.model.ModelDatabase

@Dao
interface DatabaseDao {
    @Query("SELECT * FROM duta_trans")
    fun getAll(): LiveData<List<ModelDatabase>>

    @Query("SELECT SUM(harga) FROM duta_trans")
    fun getSaldo(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(modelDatabases: ModelDatabase)

    @Query("DELETE FROM duta_trans WHERE uid= :uid")
    fun deleteSingleData(uid: Int)
}