package br.com.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ConversionData")
class ConversionData(

        @PrimaryKey(autoGenerate = false)
        val id: Long?,
        val name: String?,
        val price: Float?,
        val description: String?,
        val batch: String?,
        val eventTerminalId: Long?
)
{}