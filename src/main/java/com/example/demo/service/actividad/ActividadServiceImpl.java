package com.example.demo.service.actividad;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.actividad.ActividadRepository;
import com.example.demo.model.Actividad;

@Service
public class ActividadServiceImpl implements ActividadService{

    @Autowired          
  private ActividadRepository actividadRepository;

    @Override
    public void createActividad(Actividad Actividad) {
    	calcularHorasTotales(Actividad);
        actividadRepository.save(Actividad);
        
    }

    @Override
    public void updateActividad(Integer id, Actividad Actividad) {
        actividadRepository.deleteById(id);
        calcularHorasTotales(Actividad);
        actividadRepository.save(Actividad);
    }

    @Override
    public void deleteActividad(Integer id) {        
        actividadRepository.deleteById(id);        
    }

    @Override
    public Collection<Actividad> getActividads() {
        return (Collection<Actividad>) actividadRepository.findAll();
    }

    @Override
    public Optional<Actividad> getActividad(Integer id) {
        return actividadRepository.findById(id);
    }

	@Override
	public void calcularHorasTotales(Actividad actividad) {
		ZoneId zid = ZoneId.systemDefault();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("H:mm");

        // 1) Fechas → LocalDate
        LocalDate ldIni = actividad.getFechaInicio().toInstant().atZone(zid).toLocalDate();
        LocalDate ldFin = actividad.getFechaFinal().toInstant().atZone(zid).toLocalDate();

        // 2) Horas → LocalTime
        LocalTime ltIni = LocalTime.parse(actividad.getHoraInicio(), fmt);
        LocalTime ltFin = LocalTime.parse(actividad.getHoraFinal(),   fmt);

        // 3) Duración bruta
        LocalDateTime inicio = LocalDateTime.of(ldIni, ltIni);
        LocalDateTime fin    = LocalDateTime.of(ldFin, ltFin);
        Duration durTotal    = Duration.between(inicio, fin);

        // 4) Parseo de interrupción "H:mm"
        Duration durInterrup = Duration.ZERO;
        String inter = actividad.getInterrupcion();
        if (inter != null && !inter.isBlank()) {
            String[] p = inter.split(":");
            int hInt = Integer.parseInt(p[0]);
            int mInt = Integer.parseInt(p[1]);
            durInterrup = Duration.ofHours(hInt).plusMinutes(mInt);
        }

        // 5) Minutos netos y conversión a horas decimales
        long minutosNetos = durTotal.minus(durInterrup).toMinutes();
        double horasNetas = minutosNetos / 60.0;
        actividad.setHorasTotales(horasNetas);
        System.out.println(">>> Horas totales calculadas: " + actividad.getHorasTotales());
    
		
	}    
}
