package services;

import models.Lagjja;
import models.Dto.LagjjaDto.CreateLagjjaDto;
import models.Dto.LagjjaDto.UpdateLagjjaDto;
import repository.LagjjaRepository;

public class LagjjaService {
    private LagjjaRepository lagjjaRepository;

    public LagjjaService() {
        this.lagjjaRepository = new LagjjaRepository();
    }

    public Lagjja getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID nuk është valid!");
        }
        Lagjja lagjja = this.lagjjaRepository.getById(id);
        if (lagjja == null) {
            throw new Exception("Lagjja me ID: " + id + " nuk ekziston.");
        }
        return lagjja;
    }

    public Lagjja create(CreateLagjjaDto dto) throws Exception {
        if (dto.getEmri() == null || dto.getEmri().trim().isEmpty()) {
            throw new Exception("Emri i lagjes nuk duhet të jetë bosh.");
        }
        if (dto.getQytetiId() <= 0 || dto.getKomunaId() <= 0) {
            throw new Exception("Qyteti ose Komuna nuk janë validë.");
        }

        Lagjja lagjja = this.lagjjaRepository.create(dto);
        if (lagjja == null) {
            throw new Exception("Lagjja nuk u krijua!");
        }
        return lagjja;
    }

    public Lagjja update(UpdateLagjjaDto dto) throws Exception {
        if (dto.getId() <= 0) {
            throw new Exception("ID nuk është valid për përditësim.");
        }

        if ((dto.getEmri() == null || dto.getEmri().trim().isEmpty()) &&
                dto.getQytetiId() <= 0 &&
                dto.getKomunaId() <= 0) {
            throw new Exception("Të dhënat për përditësim nuk janë valide.");
        }

        Lagjja lagjja = this.lagjjaRepository.update(dto);
        if (lagjja == null) {
            throw new Exception("Përditësimi i lagjes dështoi!");
        }
        return lagjja;
    }

    public void delete(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID nuk është valid për fshirje.");
        }

        this.lagjjaRepository.delete(id);
    }
}
