package fer.hr.otvrac.netflixhbo.controller

import fer.hr.otvrac.netflixhbo.entity.Serija
import fer.hr.otvrac.netflixhbo.repository.NetflixhboRepo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class NetflixhboController(
    val netflixhboRepo: NetflixhboRepo
) {

    @GetMapping("/")
    @ResponseBody
    fun generic():ResponseEntity<String>{
        return ResponseEntity("Get to a generic URL", HttpStatus.OK)
    }

    @GetMapping("/table")
    @ResponseBody
    fun getTable():ResponseEntity<MutableList<Serija>> {
        return ResponseEntity(netflixhboRepo.returnJson(), HttpStatus.OK)
    }

    @GetMapping("/csv")
    @ResponseBody
    fun getCsv():ResponseEntity<MutableMap<String, MutableList<MutableList<String>>>> {
        return ResponseEntity(netflixhboRepo.returnCsv(), HttpStatus.OK)
    }

}