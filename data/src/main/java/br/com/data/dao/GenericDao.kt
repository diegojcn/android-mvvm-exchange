package br.com.iotickets.data.dao

import androidx.room.*


interface GenericDao<E> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntity(entity: E)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg entity: E)

    @Update
    fun updateEntity(entity: E)

    @Update
    fun updateAll(vararg entity: E)

    @Delete
    fun deleteEntity(entity: E)

}