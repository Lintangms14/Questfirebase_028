package com.example.questfirebase.reporsitory

import com.example.questfirebase.model.mahasiswa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkRepositoryMhs(private val firestore: FirebaseFirestore
): RepositoryMhs {
    override fun getAllMhs(): Flow<List<mahasiswa>>  = callbackFlow{
        val mhsCollection = firestore.collection("mahasiswa")
            .orderBy("nim", Query.Direction.ASCENDING)
            .addSnapshotListener{value, Error ->
                if (value !=null){
                    val mhslist = value.documents.mapNotNull {
                        it.toObject(mahasiswa::class.java)!!
                    }
                    trySend(mhslist)
                }
            }
        awaitClose {
            mhsCollection.remove()
        }
    }

    override fun getMhs(nim: String): Flow<mahasiswa?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertMhs(mahasiswa: mahasiswa) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMhs(mahasiswa: mahasiswa) {
        TODO("Not yet implemented")
    }

    override suspend fun updateMhs(mahasiswa: mahasiswa) {
        TODO("Not yet implemented")
    }
}