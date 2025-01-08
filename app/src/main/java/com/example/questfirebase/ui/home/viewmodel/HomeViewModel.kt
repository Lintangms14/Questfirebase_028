package com.example.questfirebase.ui.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questfirebase.model.mahasiswa
import com.example.questfirebase.reporsitory.RepositoryMhs
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repositoryMhs: RepositoryMhs
): ViewModel() {
    var mhsUiState: HomeUiState by mutableStateOf(HomeUiState.loading)
        private set

    init {
        getMhs()
    }

    fun getMhs() {
        viewModelScope.launch {
            repositoryMhs.getAllMhs()
                .onStart {
                    mhsUiState = HomeUiState.loading
                }
                .catch {
                    mhsUiState = HomeUiState.Error(e = it)
                }
                .collect {
                    mhsUiState = if (it.isEmpty()) {
                        HomeUiState.Error(Exception("Belum ada data mahasiswa"))
                    } else {
                        HomeUiState.Success(it)
                    }
                }
        }
    }
}

sealed class HomeUiState{
    object loading : HomeUiState()

    data class Success(val data: List<mahasiswa>) : HomeUiState()

    data class Error(val e: Throwable) : HomeUiState()
}