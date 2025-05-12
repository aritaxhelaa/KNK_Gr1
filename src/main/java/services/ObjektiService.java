package services;

import models.Objekti;
import models.Dto.ObjektiDto.CreateObjektiDto;
import models.Dto.ObjektiDto.UpdateObjektiDto;
import repository.ObjektiRepository;

public class ObjektiService {
    private ObjektiRepository objektiRepository;

    public ObjektiService() {
        this.objektiRepository = new ObjektiRepository();
    }

    public Objekti getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID nuk është valid!");
        }
        Objekti objekti = this.objektiRepository.getById(id);
        if (objekti == null) {
            throw new Exception("Objekti me ID: " + id + " nuk ekziston.");
        }
        return objekti;
    }

    public Objekti create(CreateObjektiDto dto) throws Exception {
        if (dto.getEmri() == null || dto.getEmri().trim().isEmpty()) {
            throw new Exception("Emri i objektit nuk duhet të jetë bosh.");
        }
        if (dto.getTipiObjektitId() <= 0 || dto.getRrugaId() <= 0 || dto.getKodiPostarId() <= 0) {
            throw new Exception("Të dhënat për tipin, rrugën ose kodin postar nuk janë valide.");
        }

        Objekti objekti = this.objektiRepository.create(dto);
        if (objekti == null) {
            throw new Exception("Objekti nuk u krijua!");
        }
        return objekti;
    }

    public Objekti update(UpdateObjektiDto dto) throws Exception {
        if (dto.getId() <= 0) {
            throw new Exception("ID nuk është valid për përditësim.");
        }

        if ((dto.getEmri() == null || dto.getEmri().trim().isEmpty()) &&
                dto.getNumriNderteses() == null &&
                dto.getTipiObjektitId() <= 0 &&
                dto.getRrugaId() <= 0 &&
                dto.getKodiPostarId() <= 0) {
            throw new Exception("Të dhënat për përditësim nuk janë valide.");
        }

        Objekti objekti = this.objektiRepository.update(dto);
        if (objekti == null) {
            throw new Exception("Përditësimi i objektit dështoi!");
        }
        return objekti;
    }

    public void delete(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID nuk është valid për fshirje.");
        }

        this.objektiRepository.delete(id);
    }
}
