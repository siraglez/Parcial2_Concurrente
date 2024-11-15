package org.example.parcial2_concurrente.servicios;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SchedulerService {

    private final List<ProductorService> productores;
    private int currentIndex = 0;

    public SchedulerService(List<ProductorService> productores) {
        this.productores = productores;
    }

    @Scheduled(fixedRate = 1000)
    public void scheduleProduction() {
        productores.get(currentIndex).produceComponent()
                .doOnSuccess(v -> System.out.println("Producción programada para productor: " + currentIndex))
                .subscribe();
        currentIndex = (currentIndex + 1) % productores.size();
    }
}
