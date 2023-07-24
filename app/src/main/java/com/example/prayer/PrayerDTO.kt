package com.example.prayer

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class  PrayerDTO(
    val results: ResultsDTO
)
