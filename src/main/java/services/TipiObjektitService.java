package services;

import models.TipiObjektit;
import models.Dto.TipiObjektitDto.CreateTipiObjektitDto;
import models.Dto.TipiObjektitDto.UpdateTipiObjektitDto;
import repository.TipiObjektitRepository;

public class TipiObjektitService {
    private final TipiObjektitRepository tipiObjektitRepository;

    public TipiObjektitService() {
        this.tipiObjektitRepository = new TipiObjektitRepository();
    }

    public TipiObjektit getById(int id) throws Exception {
        if (id <= 0) throw new Exception("ID nuk është valid!");
        TipiObjektit tipi = tipiObjektitRepository.getById(id);
        if (tipi == null) throw new Exception("TipiObjektit me ID " + id + " nuk ekziston.");
        return tipi;
    }

    public TipiObjektit create(CreateTipiObjektitDto dto) throws Exception {
        if (dto.getEmri() == null || dto.getEmri().isBlank()) {
            throw new Exception("Emri nuk është valid për krijim.");
        }
        TipiObjektit tipi = tipiObjektitRepository.create(dto);
        if (tipi == null) throw new Exception("TipiObjektit nuk u krijua.");
        return tipi;
    }

    public TipiObjektit update(UpdateTipiObjektitDto dto) throws Exception {
        if (dto.getId() <= 0 || dto.getEmri() == null || dto.getEmri().isBlank()) {
            throw new Exception("Të dhënat për përditësim nuk janë valide.");
        }
        TipiObjektit tipi = tipiObjektitRepository.update(dto);
        if (tipi == null) throw new Exception("Përditësimi dështoi.");
        return tipi;
    }

    public void delete(int id) throws Exception {
        if (id <= 0) throw new Exception("ID për fshirje nuk është valid.");
        tipiObjektitRepository.delete(id);
    }
}
