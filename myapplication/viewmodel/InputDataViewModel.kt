package com.example.myapplication.viewmodel

import android.app.Application
import android.view.Display
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.database.DatabaseClient.Companion.getInstance
import com.example.myapplication.database.dao.DatabaseDao
import com.example.myapplication.model.ModelDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class InputDataViewModel(application: Application) : AndroidViewModel(application) {
    var databaseDao: DatabaseDao?

    fun addDataSampah(
        nama_pengguna: String,
        jenis_mobil: String,
        orang: Int,
        harga: Int,
        tanggal: String,
        alamat: String,
        catatan: String
    ){
        Completable.fromAction {
            val modelDatabase = ModelDatabase()
            modelDatabase.namaPengguna = nama_pengguna
            modelDatabase.jenisMobil = jenis_mobil
            modelDatabase.orang = orang
            modelDatabase.harga = harga
            modelDatabase.tanggal = tanggal
            modelDatabase.alamat = alamat
            modelDatabase.catatan = catatan
            databaseDao?.insertData(modelDatabase)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    private fun ModelDatabase(): ModelDatabase {
        return ModelDatabase()
    }

    init {
        databaseDao = getInstance(application)?.appDatabase?.databaseDao()
    }
}