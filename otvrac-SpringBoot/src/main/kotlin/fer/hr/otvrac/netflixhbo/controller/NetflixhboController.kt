package fer.hr.otvrac.netflixhbo.controller

import fer.hr.otvrac.netflixhbo.DTO.SerijaDTO
import fer.hr.otvrac.netflixhbo.DTO.SerijaUpdateDTO
import fer.hr.otvrac.netflixhbo.entity.Serija
import fer.hr.otvrac.netflixhbo.repository.NetflixhboRepo
import org.apache.tomcat.util.http.fileupload.FileUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.apache.commons.io.IOUtils
import org.json.JSONObject
import org.springframework.core.io.ClassPathResource
import java.nio.charset.StandardCharsets


@Controller
class NetflixhboController(
    val netflixhboRepo: NetflixhboRepo
) {
    @RequestMapping("/*")
    @ResponseBody
    fun func(): ResponseEntity<ResponseWrap<String>> {
        return ResponseEntity(ResponseWrap("NOT IMPLEMENTED", "Not yet implemented", "null"), HttpStatus.NOT_IMPLEMENTED)
    }

    @GetMapping("/")
    @ResponseBody
    fun generic():ResponseEntity<String>{
        return ResponseEntity("Get to a generic URL", HttpStatus.OK)
    }


    @GetMapping("/json")
    @ResponseBody
    fun getTable():ResponseEntity<ResponseWrap<MutableList<Serija>>> {
        return ResponseEntity(ResponseWrap("OK","Fetched data in JSON",netflixhboRepo.returnJson()), HttpStatus.OK)
    }

    @GetMapping("/series")
    @ResponseBody
    fun getCsv():ResponseEntity<ResponseWrap<MutableMap<String, MutableList<MutableList<String>>>>> {
        return ResponseEntity(ResponseWrap("OK","Fetched all the series",netflixhboRepo.returnCsv()), HttpStatus.OK)
    }

    @GetMapping("/api")
    @ResponseBody
    fun getApi(): ResponseEntity<MutableMap<String, Any>>{
        val staticDataResource = ClassPathResource("openapi.json")
        val staticDataString = IOUtils.toString(staticDataResource.inputStream, StandardCharsets.UTF_8.name())
        return ResponseEntity(
            JSONObject(staticDataString).toMap(),
            HttpStatus.OK
        )
    }
    @GetMapping("/genre")
    @ResponseBody
    fun getGenre():ResponseEntity<ResponseWrap<MutableMap<Int, String>>>{
        return ResponseEntity(ResponseWrap("OK","Fetched Genres",netflixhboRepo.getGenres()), HttpStatus.OK)
    }

    @GetMapping("/theme")
    @ResponseBody
    fun getTematika():ResponseEntity<ResponseWrap<MutableMap<Int, String>>>{
        return ResponseEntity(ResponseWrap("OK","Fetched themes", netflixhboRepo.getTematika()), HttpStatus.OK)
    }

    @GetMapping("/genre/{name}")
    @ResponseBody
    fun getSeriesByGenre(@PathVariable("name") name:String):ResponseEntity<ResponseWrap<MutableList<Serija>>>{
        var mapa = netflixhboRepo.getSeriesByGenre(name)
        if(mapa.isEmpty())
            return ResponseEntity(ResponseWrap("Not Found","No such genre",mapa), HttpStatus.NOT_FOUND)
        return ResponseEntity(ResponseWrap("OK","Fetched series of wanted genre",mapa), HttpStatus.OK)
    }


    @GetMapping("/series/{name}")
    @ResponseBody
    fun getCsv2(@PathVariable("name") name: String):ResponseEntity<ResponseWrap<MutableList<String>>> {
        //netflixhboRepo.addSeries()
        try {
            println(netflixhboRepo.returnCsvFilter(name)["data"]!![0])
        }
        catch (e : Exception) {
            var empty = mutableListOf<String>()
            return ResponseEntity(ResponseWrap<MutableList<String>>("Not Found", "There is no such series", empty ), HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(ResponseWrap("OK", "Fetched data on wanted series", netflixhboRepo.returnCsvFilter(name)["data"]!![0]), HttpStatus.OK)
    }

    @PutMapping("/series/{name}")
    @ResponseBody
    fun updateSeries(@RequestBody req: SerijaUpdateDTO , @PathVariable("name" ) name: String):ResponseEntity<ResponseWrap<String>> {
        var serija= netflixhboRepo.returnCsvFilter(name)
        //println(serija)
        if(serija["data"]!!.isEmpty()) return ResponseEntity(ResponseWrap("Not Found", "Could not find such series", "null"),HttpStatus.NOT_FOUND)
        println(serija["data"]!![0])
        netflixhboRepo.updateSeries(req, name)
        return ResponseEntity(ResponseWrap("OK", "Series Updated", "null"),HttpStatus.OK)
    }


    @DeleteMapping("/series/{name}")
    @ResponseBody
    fun deleteSeries(@PathVariable("name")name: String):ResponseEntity<ResponseWrap<String>>{
        if(netflixhboRepo.deleteSeries(name)) {return ResponseEntity(ResponseWrap("Accepted", "Series Deleted Successfully",""),HttpStatus.ACCEPTED)}
        return ResponseEntity(ResponseWrap("Not Found", "Could not find such series","null"),HttpStatus.NOT_FOUND)

    }


    @PostMapping("/series")
    @ResponseBody
    fun addSeries(@RequestBody serijaRequest: SerijaDTO):ResponseEntity<ResponseWrap<String>> {
        if(netflixhboRepo.addSeries(serijaRequest))
        return ResponseEntity(ResponseWrap("Created", "Series Added Successfully", "null"),HttpStatus.CREATED)
        return ResponseEntity(ResponseWrap("Conflict", "Such series already exists", "null"), HttpStatus.CONFLICT)

    }
}

data class ResponseWrap<T>(val status: String, val message: String, val response: T )