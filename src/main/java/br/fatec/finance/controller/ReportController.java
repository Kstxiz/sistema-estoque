package br.fatec.finance.controller;

import br.fatec.finance.dto.MonthlySummaryResponse;
import br.fatec.finance.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/monthly-summary")
    public MonthlySummaryResponse monthlySummary(
            @RequestParam UUID userId
    ) {
        return reportService.getMonthlySummary(userId);
    }
}