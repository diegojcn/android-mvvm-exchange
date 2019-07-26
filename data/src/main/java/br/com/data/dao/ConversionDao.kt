package br.com.mvvmexchange.data.dao

import androidx.room.*
import br.com.data.model.ConversionData
import br.com.iotickets.data.dao.GenericDao


@Dao
interface ConversionDao : GenericDao<ConversionData> {

//    @Query("SELECT * FROM ColaboratorData LIMIT 1")
//    fun getColaborator(): ColaboratorData
}