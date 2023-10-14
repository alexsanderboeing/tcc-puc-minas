package com.juntamedica.processo.enumerator;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public enum DiaSemana {

    DOMINGO(Calendar.SUNDAY, "D", 6l),
    SEGUNDA(Calendar.MONDAY, "S", 7l),
    TERCA(Calendar.TUESDAY, "T", 8l),
    QUARTA(Calendar.WEDNESDAY, "Q", 9l),
    QUINTA(Calendar.THURSDAY, "I", 10l),
    SEXTA(Calendar.FRIDAY, "X", 11l),
    SABADO(Calendar.SATURDAY, "B", 12l),
    FERIADO(8, "F", 13l);

    private final Integer dia;

    private final String valorDominio;

    private final Long dominioId;

    DiaSemana(int dia, String valorDominio, Long dominioId) {
        this.dia = dia;
        this.valorDominio = valorDominio;
        this.dominioId = dominioId;
    }

    public Integer getDia() {
        return dia;
    }

    public String getValorDominio() {
        return valorDominio;
    }

    public Long getDominioId() {
        return dominioId;
    }

    public static DiaSemana getDiaSemanaDaData(LocalDateTime date){
        DiaSemana result = null;
        if (date != null){
            Date dateConvert = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateConvert);
            Integer diaSemana = calendar.get(Calendar.DAY_OF_WEEK);

            for(DiaSemana item : DiaSemana.values()){
                if (item.getDia().equals(diaSemana)){
                    result = item;
                    break;
                }
            }
        }
        return result;
    }
}
