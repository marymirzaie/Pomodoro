package com.mmb.setting.di

import com.mmb.core.SettingRepository
import com.mmb.setting.datasource.SettingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingBindingModule() {

    @Binds
    abstract fun bindSettingRepository(impl: SettingRepositoryImpl): SettingRepository
}