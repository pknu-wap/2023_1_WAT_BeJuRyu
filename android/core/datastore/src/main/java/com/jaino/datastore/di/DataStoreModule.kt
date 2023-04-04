package com.jaino.datastore.di

import com.jaino.datastore.BeJuRyuDatastore
import com.jaino.datastore.BeJuRyuDatastoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {

    @Binds
    @Singleton
    abstract fun provideBeJuRyuDataBase(
        beJuRyuDatastore: BeJuRyuDatastoreImpl
    ): BeJuRyuDatastore
}