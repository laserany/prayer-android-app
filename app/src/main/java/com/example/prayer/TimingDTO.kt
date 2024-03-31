package com.example.prayer
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
data class TimingDTO (
    var Fajr: String,
    var Dhuhr: String,
    var Asr: String,
    var Maghrib: String,
    var Isha: String
)
