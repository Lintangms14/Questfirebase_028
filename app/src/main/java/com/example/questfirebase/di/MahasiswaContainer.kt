package com.example.questfirebase.di

import com.example.questfirebase.reporsitory.NetworkRepositoryMhs
import com.example.questfirebase.reporsitory.RepositoryMhs
import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer {
    val repositoryMhs: RepositoryMhs
}

class MahasiswaContainer : AppContainer {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    override val repositoryMhs: RepositoryMhs by lazy {
        NetworkRepositoryMhs(firestore)
    }

}