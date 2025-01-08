package com.example.questfirebase.model

import androidx.room.PrimaryKey

data class mahasiswa(
    @PrimaryKey
    val nim : String,
    val nama : String,
    val alamat : String,
    val jenisKelamin : String,
    val kelas : String,
    val angkatan : String
){
    constructor() : this("", "", "", "", "", "")
}