package br.com.data

import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<AppDatabase> { provideAppDatabase(androidContext()) }
}


fun provideAppDatabase(context: Context): AppDatabase {

    return Room.databaseBuilder(context, AppDatabase::class.java, "exchangeDb")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

}