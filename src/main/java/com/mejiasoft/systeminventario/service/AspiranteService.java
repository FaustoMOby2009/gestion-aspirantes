package main.java.com.mejiasoft.systeminventario.service;

import main.java.com.mejiasoft.systeminventario.model.Aspirante;
import main.java.com.mejiasoft.systeminventario.repository.AspiranteRepository;

public class AspiranteService {

    private AspiranteRepository aspiranteRepository;
    private boolean estado = false;
    
        public AspiranteService(AspiranteRepository aspiranteRepository) {
        this.aspiranteRepository = aspiranteRepository;
    }
}
