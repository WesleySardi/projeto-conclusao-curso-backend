package org.example.biomedbacktdd.handlers.mixed;

import org.example.biomedbacktdd.DTO.commands.DependentDTO;
import org.example.biomedbacktdd.DTO.commands.DependentWebDataDTO;
import org.example.biomedbacktdd.services.interfaces.mixed.IMixedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MixedHandler {

    private final IMixedService mixedService;

    @Autowired
    public MixedHandler(IMixedService mixedService) {
        this.mixedService = mixedService;
    }

    public DependentDTO handleFindByIdWithSecurity(String cpfDep, String emergePhone) {
        try {
            return mixedService.findByIdWithSecurity(cpfDep, emergePhone);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public DependentWebDataDTO handleFindWebDataByIdWithSecurity(String cpfDep, String emergePhone) {
        try {
            return mixedService.findWebDataByIdWithSecurity(cpfDep, emergePhone);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
