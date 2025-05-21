package services;

import models.Lagjja;
import models.Dto.LagjjaDto.CreateLagjjaDto;
import models.Dto.LagjjaDto.UpdateLagjjaDto;
import repository.LagjjaRepository;

public class LagjjaService {
    private final LagjjaRepository lagjjaRepository;

    public LagjjaService() {
        this.lagjjaRepository = new LagjjaRepository();
    }

    public Lagjja getById(int id) throws Exception {
        if (id <= 0) throw new Exception("ID nuk është valid.");
        Lagjja lagjja = lagjjaRepository.getById(id);
        if (lagjja == null) throw new Exception("Lagjja nuk u gjet.");
        return lagjja;
    }

    public Lagjja create(CreateLagjjaDto dto) throws Exception {
        if (dto.getEmri() == null || dto.getEmri().isBlank()) {
            throw new Exception("Emri është i detyrueshëm.");
        }
        if (dto.getKomunaId() <= 0) {
            throw new Exception("Komuna nuk është valide.");
        }
        return lagjjaRepository.create(dto);
    }

    public Lagjja update(UpdateLagjjaDto dto) throws Exception {
        if (dto.getId() <= 0) {
            throw new Exception("ID nuk është valid për përditësim.");
        }
        return lagjjaRepository.update(dto);
    }

    public void delete(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID nuk është valid për fshirje.");
        }
        lagjjaRepository.delete(id);
    }
}
