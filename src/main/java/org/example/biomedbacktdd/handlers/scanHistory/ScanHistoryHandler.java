package org.example.biomedbacktdd.handlers.scanHistory;

import org.example.biomedbacktdd.dto.commands.ScanHistoryCommand;
import org.example.biomedbacktdd.dto.viewmodels.ScanHistoryViewModel;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.entities.scanhistory.ScanHistory;
import org.example.biomedbacktdd.repositories.interfaces.scanHistory.IScanHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class ScanHistoryHandler {

    @Autowired
    private IScanHistoryRepository scanHistoryRepository;

    public ResponseEntity<StatusResponseViewModel<ScanHistoryCommand>> handleCreate(ScanHistoryCommand command) {
        try {
            ScanHistory scanHistory = new ScanHistory();
            scanHistory.setScanName(command.getScanName());
            scanHistory.setScanEmail(command.getScanEmail());
            scanHistory.setScanPhone(command.getScanPhone());
            scanHistory.setDepCpf(command.getDepCpf());
            scanHistory.setLatitude(command.getLatitude());
            scanHistory.setLongitude(command.getLongitude());
            scanHistory.setScanDateTime(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toOffsetDateTime());

            ScanHistory savedEntity = scanHistoryRepository.save(scanHistory);

            ScanHistoryCommand responseCommand = getScanHistoryCommand(savedEntity);

            StatusResponseViewModel<ScanHistoryCommand> response = new StatusResponseViewModel<>();
            response.setStatus(201);
            response.setIsOk(true);
            response.setInfoMessage("Histórico de scan criado com sucesso.");
            response.setStatusMessage("Success");
            response.setContentResponse(responseCommand);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            StatusResponseViewModel<ScanHistoryCommand> errorResponse = new StatusResponseViewModel<>();
            errorResponse.setStatus(500);
            errorResponse.setIsOk(false);
            errorResponse.setInfoMessage("Erro ao criar histórico de scan: " + e.getMessage());
            errorResponse.setStatusMessage("Error");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<StatusResponseViewModel<List<ScanHistoryViewModel>>> handleFindByDepCpf(String depCpf) {
        try {
            List<ScanHistory> scans = scanHistoryRepository.findByDepCpf(depCpf);

            if (scans.isEmpty()) {
                StatusResponseViewModel<List<ScanHistoryViewModel>> noContentResponse = new StatusResponseViewModel<>();
                noContentResponse.setStatus(204);
                noContentResponse.setIsOk(true);
                noContentResponse.setInfoMessage("Nenhum histórico de scan encontrado para o CPF informado.");
                noContentResponse.setStatusMessage("No Content");
                noContentResponse.setContentResponse(Collections.emptyList());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(noContentResponse);
            }

            List<ScanHistoryViewModel> viewModels = scans.stream().map(scan -> {
                ScanHistoryViewModel viewModel = new ScanHistoryViewModel();
                viewModel.setScanName(scan.getScanName());
                viewModel.setScanEmail(scan.getScanEmail());
                viewModel.setScanPhone(scan.getScanPhone());
                viewModel.setDepCpf(scan.getDepCpf());
                viewModel.setScanDateTime(scan.getScanDateTime());
                viewModel.setLatitude(scan.getLatitude());
                viewModel.setLongitude(scan.getLongitude());
                return viewModel;
            }).toList();

            StatusResponseViewModel<List<ScanHistoryViewModel>> successResponse = new StatusResponseViewModel<>();
            successResponse.setStatus(200);
            successResponse.setIsOk(true);
            successResponse.setInfoMessage("Histórico de scans recuperado com sucesso.");
            successResponse.setStatusMessage("Success");
            successResponse.setContentResponse(viewModels);

            return ResponseEntity.ok(successResponse);
        } catch (Exception e) {
            // Trata exceções
            StatusResponseViewModel<List<ScanHistoryViewModel>> errorResponse = new StatusResponseViewModel<>();
            errorResponse.setStatus(500);
            errorResponse.setIsOk(false);
            errorResponse.setInfoMessage("Erro ao buscar histórico de scans: " + e.getMessage());
            errorResponse.setStatusMessage("Error");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    private static ScanHistoryCommand getScanHistoryCommand(ScanHistory savedEntity) {
        ScanHistoryCommand responseCommand = new ScanHistoryCommand();
        responseCommand.setScanName(savedEntity.getScanName());
        responseCommand.setScanEmail(savedEntity.getScanEmail());
        responseCommand.setScanPhone(savedEntity.getScanPhone());
        responseCommand.setDepCpf(savedEntity.getDepCpf());
        responseCommand.setScanDateTime(savedEntity.getScanDateTime());
        responseCommand.setLatitude(savedEntity.getLatitude());
        responseCommand.setLongitude(savedEntity.getLongitude());
        return responseCommand;
    }
}
