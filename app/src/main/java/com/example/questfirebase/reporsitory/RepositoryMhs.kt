package com.example.questfirebase.reporsitory

import com.example.questfirebase.model.mahasiswa
import kotlinx.coroutines.flow.Flow

interface RepositoryMhs {

    fun getAllMhs() : Flow<List<mahasiswa>>
    //method ini untuk memanggil fungsi geetAllMahasiswa dari mahasiswaDao berdasarkan NIM
    fun getMhs(nim: String): Flow<mahasiswa?>

    //Method ini memanfaatkan fungsi insertMahasiswa dari MahasiswaDao
    suspend fun insertMhs(mahasiswa: mahasiswa)

    //Method ini memanfaatkan fungsi deleteMahasiswa dari MahasiswaDao
    suspend fun deleteMhs(mahasiswa: mahasiswa)

    //Method ini memanfaatkan fungsi updateMahasiswa dari MahasiswaDao
    suspend fun updateMhs(mahasiswa: mahasiswa)

}