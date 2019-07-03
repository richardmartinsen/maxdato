package no.nav.maksdato


import no.nav.helse.Grunnlagsdata
import no.nav.helse.Tidsperiode
import no.nav.helse.maksdato
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import no.nav.helse.Yrkesstatus.*
import org.springframework.beans.factory.annotation.Autowired

@RestController
class StatusController(val periodeService: periodeService) {

    @GetMapping("/isAlive")
    fun isAlive() : String {

        var tidsperiode = Tidsperiode(LocalDate.parse("2019-03-01"), LocalDate.parse("2019-03-30"))
        var perioder = mutableListOf<Tidsperiode>()
        perioder.add(tidsperiode)
        var grunnlagsdata = Grunnlagsdata(LocalDate.parse("2019-06-14"), LocalDate.parse("2019-06-28"), 10, ARBEIDSTAKER, perioder)
        var maksdato = maksdato(grunnlagsdata)
        System.out.println(maksdato)


        System.out.println(periodeService.hentPerioder())
        return "ok"
    }
}