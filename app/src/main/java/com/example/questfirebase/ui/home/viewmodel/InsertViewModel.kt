package com.example.questfirebase.ui.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questfirebase.model.mahasiswa
import com.example.questfirebase.reporsitory.RepositoryMhs
import kotlinx.coroutines.launch

class InsertViewModel(
    private val repositoryMhs: RepositoryMhs
): ViewModel(){
    var uiEvent: InsertUiState by mutableStateOf(InsertUiState())
        private set

    var uiState: FormState by mutableStateOf(FormState.Idle)
        private set

    fun updateState(mahasiswaEvent: MahasiswaEvent){
        uiEvent = uiEvent.copy(
            insertUiEvent = mahasiswaEvent,
        )
    }

    fun validateFields(): Boolean{
        val event = uiEvent.insertUiEvent
        val errorState = FormErrorState(
            nim = if(event.nim.isEmpty()) "NIM Tidak Boleh Kosong" else null,
            nama = if(event.nama.isEmpty()) "Nama Tidak Boleh Kosong" else null,
            jenisKelamin = if(event.jenisKelamin.isEmpty()) "Jenis Kelamin Tidak Boleh Kosong" else null,
            alamat = if(event.alamat.isEmpty()) "Alamat Tidak Boleh Kosong" else null,
            kelas = if(event.kelas.isEmpty()) "Kelas Tidak Boleh Kosong" else null,
            angkatan = if(event.angkatan.isEmpty()) "Angkatan Tidak Boleh Kosong" else null,
            judulSkripsi = if(event.judulSkripsi.isEmpty()) "Judul SKripsi Tidak Boleh Kosong" else null,
            dosenPembimbing1 = if(event.dosenPembimbing1.isEmpty()) "Dosen Pembimbing 1 Tidak Boleh Kosong" else null,
            dosenPembimbing2 = if(event.dosenPembimbing2.isEmpty()) "Dosen Pembimbing 2 Tidak Boleh Kosong" else null,
        )
        uiEvent = uiEvent.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun insertMhs(){
        if(validateFields()){
            viewModelScope.launch {
                uiState = FormState.Loading
                try {
                    repositoryMhs.insertMhs(uiEvent.insertUiEvent.toMhsModel())
                    uiState = FormState.Success("Data berhasil disimpan")
                }catch (e: Exception) {
                    uiState = FormState.Error("Data gagal disimpan")
                }
            }
        } else{
            uiState = FormState.Error("Data tidak valid")
        }
    }

    fun resetForm(){
        uiEvent = InsertUiState()
        uiState = FormState.Idle
    }

    fun resetSnackBarMessage(){
        uiState = FormState.Idle
    }
}

sealed class FormState {
    object Idle : FormState()
    object Loading : FormState()
    data class Success(val message: String) : FormState()
    data class Error(val message: String) : FormState()
}

data class InsertUiState(
    val insertUiEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null,
    val judulSkripsi: String? = null,
    val dosenPembimbing1: String? = null,
    val dosenPembimbing2: String? = null
){
    fun isValid():Boolean{
        return nim == null && nama == null && jenisKelamin == null &&
                alamat == null && kelas == null && angkatan == null &&
                judulSkripsi == null && dosenPembimbing1 == null &&
                dosenPembimbing2 == null
    }
}

data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val jenisKelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val angkatan: String = "",
    val judulSkripsi: String = "",
    val dosenPembimbing1: String = "",
    val dosenPembimbing2: String = ""
)

fun MahasiswaEvent.toMhsModel(): mahasiswa = mahasiswa(
    nim = nim,
    nama = nama,
    jenisKelamin = jenisKelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan,
    judulSkripsi = judulSkripsi,
    dosenPembimbing1 = dosenPembimbing1,
    dosenPembimbing2 = dosenPembimbing2
)