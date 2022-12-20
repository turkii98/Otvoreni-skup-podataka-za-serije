package fer.hr.otvrac.netflixhbo.DTO


import com.fasterxml.jackson.databind.JsonNode
import fer.hr.otvrac.netflixhbo.entity.Glumac
import fer.hr.otvrac.netflixhbo.entity.Redatelj
import java.util.Date

data class SerijaDTO (
    val naziv: String,
    val prvo_emitiranje: Date,
    val imdb_ocjena: Float,
    val zavrseno: Boolean,
    val prosjecno_trajanje: Int,
    val zanr: Int,
    val platforma: Int,
    val tematika: Int,
    val redatelj: List<RedateljDTO>,
    val glumci: List<Glumac>

)