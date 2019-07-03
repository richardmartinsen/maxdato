package no.nav.maksdato

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.util.stream.Collectors

@Repository
class PeriodeRepository {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    fun PeriodeRepository(jdbcTemplate: JdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate
    }

    fun hentPerioder(): List<PeriodeDTO> {
        var sql = "select "
        sql += "   is01_personkey "
        sql += " , f_nr "
        sql += " , tk_nr "
        sql += " , is10_arbufoer_seq "

        // Periode
        sql += " , is10_arbufoer "           // sykmeldtfom
        sql += " , is10_arbufoer_tom "       // sykmeldttom
        sql += " , is10_ufoeregrad "         // grad
        sql += " , is10_max "                // slutt
        sql += " , is10_arbper "             // erarbeidsgiverperiode
        sql += " , is10_ferie_fom "          // ferie1 fom
        sql += " , is10_ferie_tom "          // ferie1 tom
        sql += " , is10_ferie_fom2 "         // ferie2 fom
        sql += " , is10_ferie_tom2 "         // ferie2 tom
        sql += " , is10_stans "              // stansaarsak
        sql += " , is10_unntak_aktivitet "   // unntakaktivitet
        sql += " , is10_arbkat "             // arbeidskategori
        sql += " , is10_arbkat_99 "          // arbeidskategori_99
        sql += " , is10_sanksjon_fom "       // sanksjon fom
        sql += " , is10_sanksjon_tom "       // sanksjon tom
        sql += " , is10_sanksjon_bekreftet " // ersanksjonbekreftet
        sql += " , is10_sanksjonsdager "     // sanksjonsdager
        sql += " , is10_stoppdato "          // opphoerfom

        // Sykemelding
        sql += " , is10_legenavn "           // sykmelder
        sql += " , is10_behdato "            // behandlet


        // Yrkesskade
        sql += " , is10_skadeart "           // skadeart
        sql += " , is10_skdato "             // skadet
        sql += " , is10_skm_mott "           // vedtatt

        sql += " from is_periode_10 "
        sql += " where f_nr = '123'"
        sql += " and is10_stoenads_type = '  '" //  -- dvs. sykepenger
        sql += " and is10_frisk != 'H' "
        sql += " order by is10_arbufoer "

        val periodeDTOList = jdbcTemplate.query(sql, arrayOf()
        ) { rs, rowNum ->
            PeriodeDTO(
                    rs.getLong("is01_personkey"), rs.getString("tk_nr").trim(), rs.getInt("is10_arbufoer_seq"), rs.getInt("is10_arbufoer"), rs.getInt("is10_arbufoer_tom"), rs.getString("is10_ufoeregrad").trim(), rs.getInt("is10_max"), rs.getString("is10_arbper").trim(), rs.getInt("is10_ferie_fom"), rs.getInt("is10_ferie_tom"), rs.getInt("is10_ferie_fom2"), rs.getInt("is10_ferie_tom2"), rs.getString("is10_stans").trim(), rs.getString("is10_unntak_aktivitet").trim(), rs.getString("is10_arbkat").trim(), rs.getString("is10_arbkat_99").trim(), rs.getInt("is10_sanksjon_fom"), rs.getInt("is10_sanksjon_tom"), rs.getString("is10_sanksjon_bekreftet").trim(), rs.getInt("is10_sanksjonsdager"), rs.getInt("is10_stoppdato"), rs.getString("is10_legenavn").trim(), rs.getInt("is10_behdato"), rs.getString("is10_skadeart").trim(), rs.getInt("is10_skdato"), rs.getInt("is10_skm_mott")
            )
        }.stream().collect(Collectors.toList())

        return periodeDTOList
    }
}