package no.nav.maksdato

import org.springframework.stereotype.Service

@Service
class periodeService(val periodeRepository: PeriodeRepository) {

    fun hentPerioder() : List<PeriodeDTO> {
        var perioder = periodeRepository.hentPerioder()
        return perioder
    }
}