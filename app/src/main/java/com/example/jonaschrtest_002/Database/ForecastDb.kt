package com.example.jonaschrtest_002.Database

import org.jetbrains.anko.db.select


class ForecastDb(
    private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance
    //private val dataMapper: DbDataMapper = DbDataMapper()
)

{

    fun requestForecastByZipCode(zipCode: Long, data: Long)= forecastDbHelper.use{

        val dailyRequest = "daglig request"
        val dailyForecast = "daglig forudsigelse"

        val city = select(CityForecastTable.NAME)
            .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())

        if (city == null) null
        //if (city != null) dataMapper.convertToDomain(city) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use{

    }

}

