package fer.hr.otvrac.netflixhbo.DTO

import java.util.*

data class SerijaUpdateDTO (
    val naziv: String,
    val prvo_emitiranje: Date,
    val imdb_ocjena: Float,
    val zavrseno: Boolean,
    val prosjecno_trajanje: Int,
    val zanr: Int,
    val platforma: Int,
    val tematika: Int
    //val redatelj: List<RedateljDTO>,
    //val glumci: List<Glumac>

)