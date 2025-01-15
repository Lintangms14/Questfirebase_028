package com.example.questfirebase.model

import androidx.room.PrimaryKey

data class mahasiswa(
    @PrimaryKey
    val nim : String,
    val nama : String,
    val alamat : String,
    val jenisKelamin : String,
    val kelas : String,
    val angkatan : String,
    val judulSkripsi : String,
    val dosenPembimbing1 : String,
    val dosenPembimbing2: String
){
    constructor() : this("", "", "", "", "", "","","","")
}