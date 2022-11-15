package fer.hr.otvrac.netflixhbo.entity

import com.fasterxml.jackson.databind.JsonNode
import java.util.Date

data class Serija (
    val naziv: String,
    val prvo_emitiranje: Date,
    val imdb_ocjena: String,
    val zavrseno: String,
    val prosjecno_trajanje: String,
    val zanr: String,
    val platforma: String,
    val tematika: String,
    val redatelj: Any,
    val glumci: Any

        )