package com.example.questfirebase

import android.app.Application
import com.example.questfirebase.di.AppContainer
import com.example.questfirebase.di.MahasiswaContainer

class MahasiswaApp: Application() {
    lateinit var container: MahasiswaContainer
    override fun onCreate() {
        super.onCreate()
        container= MahasiswaContainer()
    }
}