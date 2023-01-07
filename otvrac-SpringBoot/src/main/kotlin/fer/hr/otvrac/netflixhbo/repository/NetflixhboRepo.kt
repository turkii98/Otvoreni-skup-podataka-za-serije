package fer.hr.otvrac.netflixhbo.repository

import fer.hr.otvrac.netflixhbo.DTO.SerijaDTO
import fer.hr.otvrac.netflixhbo.DTO.SerijaUpdateDTO
import fer.hr.otvrac.netflixhbo.entity.Redatelj
import fer.hr.otvrac.netflixhbo.entity.Serija
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

interface NetflixhboRepository{
    fun returnJson(): MutableList<Any>
    fun returnCsv(): MutableMap<String, MutableList<MutableList<String>>>
    fun addSeries(serijaDTO: SerijaDTO): Boolean
    fun returnCsvFilter(filter:String): MutableMap<String, MutableList<MutableList<String>>>
    fun updateSeries(serijaRequest: SerijaUpdateDTO, name: String)
    fun deleteSeries(name: String): Boolean
    fun getGenres(): MutableMap<Int, String>
    fun getTematika(): MutableMap<Int, String>
    fun getSeriesByGenre(genre: String): MutableList<Serija>
}
@Repository
class NetflixhboRepo (
    private val jdbcTemplate: NamedParameterJdbcTemplate): NetflixhboRepository {

    override fun returnJson(): MutableList<Any> {
        val lista = mutableListOf<Any>()

        jdbcTemplate.query(
            "select serija.naziv, prvo_emitiranje, imdb_ocjena::varchar, završeno::varchar, prosjecno_trajanje::varchar, \n" +
                    "zanr.naziv zanr, streamingplatforme.naziv platforma, tematika.naziv tematika,\n" +
                    "\tcoalesce(json_agg( distinct\n" +
                    "\t\tjsonb_build_object( \n" +
                    "\t\t\t'ime', redatelji.ime, \n" +
                    "\t\t\t'prezime', redatelji.prezime\n" +
                    "\t\t)) \n" +
                    "\t\t\tfilter (where redatelji is not null), '[]') as redatelj,\n" +
                    "\t\n" +
                    "\t\n" +
                    "\tcoalesce(json_agg( distinct\n" +
                    "\t\tjsonb_build_object(\n" +
                    "\t\t\t'ime', glumci.ime,\n" +
                    "\t\t\t'prezime', glumci.prezime\n" +
                    "\t\t))\n" +
                    "\t\t\tfilter(where glumci is not null), '[]') as glumci\n" +
                    "\t\n" +
                    "from serija join zanr on zanr.id = serija.zanr_id\n" +
                    "join streamingplatforme on streamingplatforme.id = serija.platforma_id\n" +
                    "join tematika on tematika.id = serija.tematika_id\n" +
                    "join redatelji on serija.id = redatelji.serija_id\n" +
                    "join glumci_u_seriji on glumci_u_seriji.serija_id = serija.id\n" +
                    "join glumci on glumci_u_seriji.glumac_id = glumci.id\n" +
                    "\t\n" +
                    "group by serija.naziv, serija.prvo_emitiranje, imdb_ocjena, završeno, prosjecno_trajanje, \n" +
                    "\tzanr.naziv, streamingplatforme.naziv, tematika.naziv\n" +
                    "\n"
        ) { resultSet, _ ->
            val b = resultSet.getObject(9)
            println(b)

            //val c = resultSet.get
            lista.add("@context=http://schema.org")
            lista.add("@type=Serija")
            lista.add(
                Serija(
                    naziv = resultSet.getString("naziv"),
                    prvo_emitiranje = resultSet.getDate("prvo_emitiranje"),
                    imdb_ocjena = resultSet.getString("imdb_ocjena"),
                    zavrseno = resultSet.getString("završeno"),
                    prosjecno_trajanje = resultSet.getString("prosjecno_trajanje"),
                    zanr = resultSet.getString("zanr"),
                    platforma = resultSet.getString("platforma"),
                    tematika = resultSet.getString("tematika"),
                    redatelj = resultSet.getObject(9),
                    glumci = resultSet.getObject(10)
                )
            )
        }
        println(lista)
        return lista
    }

    override fun returnCsv(): MutableMap<String, MutableList<MutableList<String>>> {
        val lista = mutableListOf<MutableList<String>>()
        jdbcTemplate.query(
            "select serija.naziv, prvo_emitiranje, imdb_ocjena, završeno, prosjecno_trajanje, \n" +
                    "zanr.naziv zanr, streamingplatforme.naziv platforma, tematika.naziv tematika, \n" +
                    "\tstring_agg(distinct redatelji.ime || ' ' || redatelji.prezime,';') as redatelj,\n" +
                    "\tstring_agg(distinct glumci.ime || ' ' || glumci.prezime,';') as glumci\n" +
                    "from serija join zanr on zanr.id = serija.zanr_id\n" +
                    "join streamingplatforme on streamingplatforme.id = serija.platforma_id\n" +
                    "join tematika on tematika.id = serija.tematika_id\n" +
                    "join redatelji on serija.id = redatelji.serija_id\n" +
                    "join glumci_u_seriji on glumci_u_seriji.serija_id = serija.id\n" +
                    "join glumci on glumci_u_seriji.glumac_id = glumci.id\n" +
                    "group by serija.naziv, serija.prvo_emitiranje, imdb_ocjena, završeno, prosjecno_trajanje, \n" +
                    "\tzanr.naziv, streamingplatforme.naziv, tematika.naziv"
        ) { resultSet, _ ->
            val unutLista = mutableListOf<String>()
            unutLista.add(resultSet.getString("naziv"))
            unutLista.add(resultSet.getDate("prvo_emitiranje").toString())
            unutLista.add(resultSet.getString("imdb_ocjena"))
            unutLista.add(resultSet.getString("završeno"))
            unutLista.add(resultSet.getString("prosjecno_trajanje"))
            unutLista.add(resultSet.getString("zanr"))
            unutLista.add(resultSet.getString("platforma"))
            unutLista.add(resultSet.getString("tematika"))
            unutLista.add(resultSet.getString("redatelj"))
            unutLista.add(resultSet.getString("glumci"))
            lista.add(unutLista)

        }
        val mapa = mutableMapOf<String, MutableList<MutableList<String>>>()
        mapa.put("data", lista)
        return mapa

    }

    override fun returnCsvFilter(filter : String): MutableMap<String, MutableList<MutableList<String>>> {
        val lista = mutableListOf<MutableList<String>>()
        jdbcTemplate.query(
            "select serija.naziv, prvo_emitiranje, imdb_ocjena, završeno, prosjecno_trajanje, \n" +
                    "zanr.naziv zanr, streamingplatforme.naziv platforma, tematika.naziv tematika, \n" +
                    "\tstring_agg(distinct redatelji.ime || ' ' || redatelji.prezime,';') as redatelj,\n" +
                    "\tstring_agg(distinct glumci.ime || ' ' || glumci.prezime,';') as glumci\n" +
                    "from serija join zanr on zanr.id = serija.zanr_id\n" +
                    "join streamingplatforme on streamingplatforme.id = serija.platforma_id\n" +
                    "join tematika on tematika.id = serija.tematika_id\n" +
                    "join redatelji on serija.id = redatelji.serija_id\n" +
                    "join glumci_u_seriji on glumci_u_seriji.serija_id = serija.id\n" +
                    "join glumci on glumci_u_seriji.glumac_id = glumci.id\n" +
                    "where serija.naziv = '" + filter + "' \n" +
                    "group by serija.naziv, serija.prvo_emitiranje, imdb_ocjena, završeno, prosjecno_trajanje, \n" +
                    "\tzanr.naziv, streamingplatforme.naziv, tematika.naziv"
        ) { resultSet, _ ->
            val unutLista = mutableListOf<String>()
            unutLista.add(resultSet.getString("naziv"))
            unutLista.add(resultSet.getDate("prvo_emitiranje").toString())
            unutLista.add(resultSet.getString("imdb_ocjena"))
            unutLista.add(resultSet.getString("završeno"))
            unutLista.add(resultSet.getString("prosjecno_trajanje"))
            unutLista.add(resultSet.getString("zanr"))
            unutLista.add(resultSet.getString("platforma"))
            unutLista.add(resultSet.getString("tematika"))
            unutLista.add(resultSet.getString("redatelj"))
            unutLista.add(resultSet.getString("glumci"))
            lista.add(unutLista)

        }
        val mapa = mutableMapOf<String, MutableList<MutableList<String>>>()
        mapa.put("data", lista)
        return mapa
    }

    override fun updateSeries(serijaRequest: SerijaUpdateDTO, name: String){
        var seriesId = 999999
        jdbcTemplate.query("select id from serija where naziv = '" +name+ "' "){
                resultSet, _ ->
            seriesId = resultSet.getInt("id")
        }

        jdbcTemplate.update("update serija set naziv = :naziv, prvo_emitiranje = :prvo_emitiranje, imdb_ocjena = :imdb_ocjena," +
                "završeno = :završeno, prosjecno_trajanje= :prosjecno_trajanje, zanr_id = :zanr_id, platforma_id = :platforma_id, " +
                "tematika_id = :tematika_id where id = "+seriesId+";"
            , mapOf("naziv" to serijaRequest.naziv, "prvo_emitiranje" to serijaRequest.prvo_emitiranje, "imdb_ocjena" to serijaRequest.imdb_ocjena,
                "završeno" to serijaRequest.zavrseno, "prosjecno_trajanje" to serijaRequest.prosjecno_trajanje, "zanr_id" to serijaRequest.zanr,
                "platforma_id" to serijaRequest.platforma,
                "tematika_id" to serijaRequest.tematika)
        )
    }

    override fun deleteSeries(name: String): Boolean{
        var seriesId = 999999
        jdbcTemplate.query("select id from serija where naziv = '" +name+ "' "){
                resultSet, _ ->
            seriesId = resultSet.getInt("id")
        }
        if(seriesId == 999999) {return false}

        jdbcTemplate.update("delete from redatelji where serija_id = :id",
            mapOf("id" to seriesId)
        )

        jdbcTemplate.update("delete from glumci_u_seriji where serija_id = :id",
            mapOf("id" to seriesId)
        )

        jdbcTemplate.update("delete from serija where id = :id",
            mapOf("id" to seriesId)
        )
        return true

    }
    override fun addSeries(serijaRequest: SerijaDTO): Boolean {

        var glumciId = 999999999
        var serijaId = 999999999
        var redateljiId = 999999999

        var exists = false
        jdbcTemplate.query("select exists(select id from serija where serija.naziv = '"+serijaRequest.naziv +"') ")
        {
                resultSet, _ ->
            exists = resultSet.getBoolean("exists")
        }
        if(exists) return false
        jdbcTemplate.query(
            "select id + 1 as id\n" +
                    "from glumci\n" +
                    "order by id desc\n" +
                    "limit 1"
        )
        { resultSet, _ ->
            glumciId = resultSet.getInt("id")
        }

        jdbcTemplate.query(
            "select id + 1 as id\n" +
                    "from serija\n" +
                    "order by id desc\n" +
                    "limit 1"
        )
        { resultSet, _ ->
            serijaId = resultSet.getInt("id")
        }

        jdbcTemplate.query(
            "select id + 1 as id\n" +
                    "from redatelji\n" +
                    "order by id desc\n" +
                    "limit 1"
        )
        { resultSet, _ ->
            redateljiId = resultSet.getInt("id")
        }
        println(glumciId)
        println(serijaId)
        println(redateljiId)

        var pocGlumci = glumciId
        var pocRedatelj = redateljiId


        for (i in serijaRequest.glumci) {
            jdbcTemplate.update(
                "insert into glumci values(:id , :ime, :prezime);",
                mapOf("id" to glumciId, "ime" to i.ime, "prezime" to i.prezime)
            )
            glumciId += 1
        }

        glumciId -= 1

        jdbcTemplate.update(
            "insert into serija values(:id, :naziv, :prvo_emitiranje, :imdb_ocjena, :završeno, :prosjecno_trajanje, :zanr_id, :platforma_id, :tematika_id);",
            mapOf(
                "id" to serijaId,
                "naziv" to serijaRequest.naziv,
                "prvo_emitiranje" to serijaRequest.prvo_emitiranje,
                "imdb_ocjena" to serijaRequest.imdb_ocjena,
                "završeno" to serijaRequest.zavrseno,
                "prosjecno_trajanje" to serijaRequest.prosjecno_trajanje,
                "zanr_id" to serijaRequest.zanr,
                "platforma_id" to serijaRequest.platforma,
                "tematika_id" to serijaRequest.tematika
            )
        )

        for (i in serijaRequest.redatelj) {
            jdbcTemplate.update(
                "insert into redatelji values(:id , :ime, :prezime, :serija_id);",
                mapOf("id" to redateljiId, "ime" to i.ime, "prezime" to i.prezime, "serija_id" to serijaId)
            )
            redateljiId += 1
        }
        redateljiId -= 1
        for (i in pocGlumci..glumciId) {
            jdbcTemplate.update(
                "insert into glumci_u_seriji values(:glumac_id , :serija_id);",
                mapOf("glumac_id" to i, "serija_id" to serijaId)
            )
        }
        return true
    }

    override fun getGenres(): MutableMap<Int, String>{
        var mapa = mutableMapOf<Int, String>()
        jdbcTemplate.query("select * from zanr"){
                resultSet, _ ->
            var key = resultSet.getInt("id")
            var valu = resultSet.getString("naziv")
            mapa.put(key, valu)
        }
        return mapa
    }

    override fun getSeriesByGenre(genre: String): MutableList<Serija> {
        val lista = mutableListOf<Serija>()
        jdbcTemplate.query(
            "select serija.naziv, prvo_emitiranje, imdb_ocjena::varchar, završeno::varchar, prosjecno_trajanje::varchar, \n" +
                    "zanr.naziv zanr, streamingplatforme.naziv platforma, tematika.naziv tematika,\n" +
                    "\tcoalesce(json_agg( distinct\n" +
                    "\t\tjsonb_build_object( \n" +
                    "\t\t\t'ime', redatelji.ime, \n" +
                    "\t\t\t'prezime', redatelji.prezime\n" +
                    "\t\t)) \n" +
                    "\t\t\tfilter (where redatelji is not null), '[]') as redatelj,\n" +
                    "\t\n" +
                    "\t\n" +
                    "\tcoalesce(json_agg( distinct\n" +
                    "\t\tjsonb_build_object(\n" +
                    "\t\t\t'ime', glumci.ime,\n" +
                    "\t\t\t'prezime', glumci.prezime\n" +
                    "\t\t))\n" +
                    "\t\t\tfilter(where glumci is not null), '[]') as glumci\n" +
                    "\t\n" +
                    "from serija join zanr on zanr.id = serija.zanr_id\n" +
                    "join streamingplatforme on streamingplatforme.id = serija.platforma_id\n" +
                    "join tematika on tematika.id = serija.tematika_id\n" +
                    "join redatelji on serija.id = redatelji.serija_id\n" +
                    "join glumci_u_seriji on glumci_u_seriji.serija_id = serija.id\n" +
                    "join glumci on glumci_u_seriji.glumac_id = glumci.id\n" +
                    "where zanr.naziv = '"+genre+"'\n" +
                    "group by serija.naziv, serija.prvo_emitiranje, imdb_ocjena, završeno, prosjecno_trajanje, \n" +
                    "\tzanr.naziv, streamingplatforme.naziv, tematika.naziv\n" +
                    "\n"
        ) { resultSet, _ ->
            val b = resultSet.getObject(9)
            println(b)

            //val c = resultSet.get


            lista.add(
                Serija(
                    naziv = resultSet.getString("naziv"),
                    prvo_emitiranje = resultSet.getDate("prvo_emitiranje"),
                    imdb_ocjena = resultSet.getString("imdb_ocjena"),
                    zavrseno = resultSet.getString("završeno"),
                    prosjecno_trajanje = resultSet.getString("prosjecno_trajanje"),
                    zanr = resultSet.getString("zanr"),
                    platforma = resultSet.getString("platforma"),
                    tematika = resultSet.getString("tematika"),
                    redatelj = resultSet.getObject(9),
                    glumci = resultSet.getObject(10)
                )
            )
        }
        return lista
    }

    override fun getTematika(): MutableMap<Int, String> {
        var mapa = mutableMapOf<Int, String>()
        jdbcTemplate.query("select * from tematika"){
                resultSet, _ ->
            var key = resultSet.getInt("id")
            var valu = resultSet.getString("naziv")
            mapa.put(key, valu)
        }
        return mapa

    }
}