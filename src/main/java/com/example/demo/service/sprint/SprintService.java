package com.example.demo.service.sprint;

import java.util.Collection;
import java.util.Optional;

import com.example.demo.model.Sprint;

public interface SprintService {
    public abstract void createSprint(Sprint Sprint);

    public abstract void updateSprint(Integer id, Sprint Sprint);

    public abstract void deleteSprint(Integer id);

    public abstract Collection<Sprint> getSprints();

    public abstract Optional<Sprint> getSprint(Integer id);

	Collection<Sprint> getSprintsByProyecto(Integer proyectoId);
}
