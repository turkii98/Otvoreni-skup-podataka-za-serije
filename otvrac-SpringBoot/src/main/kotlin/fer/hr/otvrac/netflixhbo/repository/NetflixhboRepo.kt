package fer.hr.otvrac.netflixhbo.repository

import fer.hr.otvrac.netflixhbo.entity.Redatelj
import fer.hr.otvrac.netflixhbo.entity.Serija
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

interface NetflixhboRepository{
    fun returnJson(): MutableList<Serija>
    fun returnCsv(): MutableMap<String, MutableList<MutableList<String>>>
}
@Repository
class NetflixhboRepo (
    private val jdbcTemplate: NamedParameterJdbcTemplate): NetflixhboRepository{

    override fun returnJson(): MutableList<Serija> {
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
                        "\t\n" +
                        "group by serija.naziv, serija.prvo_emitiranje, imdb_ocjena, završeno, prosjecno_trajanje, \n" +
                        "\tzanr.naziv, streamingplatforme.naziv, tematika.naziv\n" +
                        "\n") {
                resultSet, _ ->
                val b = resultSet.getObject(9)
                println(b)

                //val c = resultSet.get


                lista.add(Serija(
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
                ))
            }
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
        ){
            resultSet,_ ->
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



}