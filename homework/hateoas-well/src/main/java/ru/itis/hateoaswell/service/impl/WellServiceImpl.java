package ru.itis.hateoaswell.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoaswell.model.Well;
import ru.itis.hateoaswell.repository.WellRepository;
import ru.itis.hateoaswell.service.WellService;

@Service
public class WellServiceImpl implements WellService {

    @Autowired
    WellRepository wellRepository;

    @Override
    public Well closeWell(Long wellId) {
        Well well = wellRepository.findById(wellId).orElseThrow(IllegalArgumentException::new);
        well.closeWell();
        wellRepository.save(well);
        return well;
    }
}
