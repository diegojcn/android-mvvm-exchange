package br.com.exchange.app.base.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtils {

    @JvmStatic
    fun formatDate(date: Date?): String {
        val format = SimpleDateFormat("dd/MM/yyy")

        if(date != null){
            return format.format(date)
        }

        return ""
    }

    @JvmStatic
    fun formatDate(date: String?): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd/MM/yyyy")

        if(date != null){
            return formatter.format(parser.parse(date))
        }
        return ""
    }

    @JvmStatic
    fun formatDateTime(date: String?): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")

        if(date != null){
            return formatter.format(parser.parse(date))
        }

        return ""
    }


}