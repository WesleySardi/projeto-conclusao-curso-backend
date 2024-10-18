package org.example.biomedbacktdd.services.interfaces.mixed;

import org.example.biomedbacktdd.DTO.commands.DependentDTO;
import org.example.biomedbacktdd.DTO.commands.DependentWebDataDTO;

public interface IMixedService {

    DependentDTO findByIdWithSecurity(String cpfDep, String emergePhone);
    DependentWebDataDTO findWebDataByIdWithSecurity(String cpfDep, String emergePhone);
}
