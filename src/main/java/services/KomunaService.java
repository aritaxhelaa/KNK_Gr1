package services;

import models.Komuna;
import models.Dto.KomunaDto.CreateKomunaDto;
import models.Dto.KomunaDto.UpdateKomunaDto;
import repository.KomunaRepository;

public class KomunaService {
    private KomunaRepository komunaRepository;

    public KomunaService() {
        this.komunaRepository = new KomunaRepository();
    }


    public Komuna getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID nuk është valid!");
        }
        Komuna komuna = this.komunaRepository.getById(id);
        if (komuna == null) {
            throw new Exception("Komuna me ID: " + id + " nuk ekziston.");
        }
        return komuna;
    }


    public Komuna create(CreateKomunaDto dto) throws Exception {
        if (dto.getEmri() == null || dto.getEmri().isEmpty()) {
            throw new Exception("Emri i komunës nuk mund të jetë i zbrazët.");
        }
        Komuna komuna = this.komunaRepository.create(dto);
        if (komuna == null) {
            throw new Exception("Komuna nuk u krijua!");
        }
        return komuna;
    }

    public Komuna update(UpdateKomunaDto dto) throws Exception {
        if (dto.getId() <= 0) {
            throw new Exception("ID nuk është valid për përditësim.");
        }
        if (dto.getEmri() == null) {
            throw new Exception("Të paktën një fushë duhet të ndryshohet për përditësim.");
        }
        Komuna komuna = this.komunaRepository.update(dto);
        if (komuna == null) {
            throw new Exception("Përditësimi i komunës dështoi!");
        }
        return komuna;
    }


    public void delete(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID nuk është valid për fshirje.");
        }

        this.komunaRepository.delete(id);
    }
}
