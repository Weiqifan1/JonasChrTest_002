package com.example.jonaschrtest_002.Database

class CityForecast(val map: MutableMap<String, Any?>, val dailyForecast: List<String>) {

    var _id: Long by map
    var city: String by map
    var country: String by map

    constructor(id: Long, city: String, country: String, dailyForecast: List<String>)
            : this(HashMap(), dailyForecast){
        this._id = id
        this.city = city
        this.country = country
    }

}