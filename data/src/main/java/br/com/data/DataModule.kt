package br.com.data

import android.content.Context
import androidx.room.Room
import br.com.mvvmexchange.data.dao.ConversionDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<AppDatabase> { provideAppDatabase(androidContext()) }
    single<ConversionDao> {get<AppDatabase>().conversionDao()}
}


fun provideAppDatabase(context: Context): AppDatabase {

    return Room.databaseBuilder(context, AppDatabase::class.java, "exchangeDB")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

}