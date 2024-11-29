package org.example.biomedbacktdd.controllers.scanhistory;

import org.example.biomedbacktdd.dto.commands.ScanHistoryCommand;
import org.example.biomedbacktdd.dto.viewmodels.ScanHistoryViewModel;
import org.example.biomedbacktdd.dto.viewmodels.StatusResponseViewModel;
import org.example.biomedbacktdd.handlers.scanHistory.ScanHistoryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scanHistory")
public class ScanHistoryController {

    @Autowired
    private ScanHistoryHandler handler;

    @PostMapping
    public ResponseEntity<StatusResponseViewModel<ScanHistoryCommand>> createScanHistory(@RequestBody ScanHistoryCommand command) {
        return handler.handleCreate(command);
    }


    @GetMapping("/dependente/{depCpf}")
    public ResponseEntity<StatusResponseViewModel<List<ScanHistoryViewModel>>> getScansByDepCpf(@PathVariable String depCpf) {
        return handler.handleFindByDepCpf(depCpf);
    }
}
