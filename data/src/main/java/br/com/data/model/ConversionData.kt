package br.com.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "ConversionData")
data class ConversionData (

        @PrimaryKey(autoGenerate = true)
        val id: Long?,
        val valueTo: Float?,
        val rateTo: Float?,
        val symbolTo: String,
        val symbolFrom: String,
        val valueFrom: Float?,
        val date: Date?


)
{}