package com.enterprise.appstockmarket.dependencyinjection

import android.content.Context
import com.enterprise.appstockmarket.remotedatasource.mock.FlowMockApi
import com.enterprise.appstockmarket.remotedatasource.mock.mockApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideFlowMockApi(@ApplicationContext context: Context): FlowMockApi {
        return mockApi(context)
    }

}