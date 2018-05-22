package com.example.lengineer.githubrepolist.di.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    private final Context appContext;
//    private Cicerone<Router> cicerone;

    public AppModule(Context appContext) {
        this.appContext = appContext;
//        cicerone = Cicerone.create();
    }

    @Provides
    @Singleton
    Context provideContext(){
        return appContext;
    }

//    @Provides
//    @Singleton
//    Router provideRouter(){
//        return cicerone.getRouter();
//    }

//    @Provides
//    @Singleton
//    NavigatorHolder provideNavigatorHolder(){
//        return cicerone.getNavigatorHolder();
//    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
