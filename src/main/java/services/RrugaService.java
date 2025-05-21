package services;

import models.Rruga;
import models.Dto.RrugaDto.CreateRrugaDto;
import models.Dto.RrugaDto.UpdateRrugaDto;
import repository.RrugaRepository;

public class RrugaService {
    private RrugaRepository rrugaRepository;

    public RrugaService() {
        this.rrugaRepository = new RrugaRepository();
    }

    public Rruga getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID nuk është valid!");
        }
        Rruga rruga = this.rrugaRepository.getById(id);
        if (rruga == null) {
            throw new Exception("Rruga me ID: " + id + " nuk ekziston.");
        }
        return rruga;
    }

    public Rruga create(CreateRrugaDto dto) throws Exception {
        if (dto.getEmri() == null || dto.getEmri().trim().isEmpty()) {
            throw new Exception("Emri i rrugës nuk duhet të jetë bosh.");
        }
        if (dto.getKomunaId() <= 0 || dto.getLagjjaId() <= 0) {
            throw new Exception("Komuna ose Lagjja nuk janë valide.");
        }

        Rruga rruga = this.rrugaRepository.create(dto);
        if (rruga == null) {
            throw new Exception("Rruga nuk u krijua!");
        }
        return rruga;
    }

    public Rruga update(UpdateRrugaDto dto) throws Exception {
        if (dto.getId() <= 0) {
            throw new Exception("ID nuk është valid për përditësim.");
        }

        if ((dto.getEmri() == null || dto.getEmri().trim().isEmpty()) &&
                dto.getKomunaId() <= 0 &&
                dto.getQytetiId() <= 0 &&
                dto.getFshatiId() <= 0 &&
                dto.getLagjjaId() <= 0) {
            throw new Exception("Të dhënat për përditësim nuk janë valide.");
        }

        Rruga rruga = this.rrugaRepository.update(dto);
        if (rruga == null) {
            throw new Exception("Përditësimi i rrugës dështoi!");
        }
        return rruga;
    }

    public void delete(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID nuk është valid për fshirje.");
        }

        this.rrugaRepository.delete(id);
    }
}
