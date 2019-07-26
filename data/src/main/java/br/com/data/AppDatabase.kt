package br.com.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.data.converter.DateTypeConverter
import br.com.data.model.ConversionData


@Database(entities = [ConversionData::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {


//    abstract fun employeeDao(): EmployeeDao

}