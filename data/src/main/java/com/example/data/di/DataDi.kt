package com.example.data.di

import android.system.Os.bind
import com.example.data.RepositoryImpl
import com.example.data.Storage
import com.example.data.storage.remote.StorageImpl
import com.example.dmain.Repository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val remoteStorageModule = module {
    singleOf(::StorageImpl) { bind<Storage.Remote>() }
}

val repositoryModule = module {
    singleOf(::RepositoryImpl) { bind<Repository>() }
}