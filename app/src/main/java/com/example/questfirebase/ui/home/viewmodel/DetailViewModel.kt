//package com.example.questfirebase.ui.home.viewmodel
//
//import android.net.http.HttpException
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.SavedStateHandle
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.questfirebase.model.mahasiswa
//import com.example.questfirebase.reporsitory.RepositoryMhs
//import kotlinx.coroutines.launch
//import java.io.IOException
//
//sealed class DetailUiState {
//    data class Success(val mahasiswa: mahasiswa) : DetailUiState()
//    object Error : DetailUiState()
//    object Loading : DetailUiState()
//}
//
//class DetailViewModel(
//    savedStateHandle: SavedStateHandle,
//    private val mhs: RepositoryMhs
//) : ViewModel() {
//
//    var mahasiswaDetailState: DetailUiState by mutableStateOf(DetailUiState.Loading)
//        private set
//
//    private val _nim: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])
//
//    init {
//        getMahasiswabyNim()
//    }
//
//    fun getMahasiswabyNim() {
//        viewModelScope.launch {
//            mahasiswaDetailState = DetailUiState.Loading
//            mahasiswaDetailState = try {
//                val mahasiswa = mhs.getMahasiswabyNim(_nim).data
//                DetailUiState.Success(mahasiswa)
//            } catch (e: IOException) {
//                DetailUiState.Error
//            } catch (e: HttpException) {
//                DetailUiState.Error
//            }
//        }
//    }
//}