package services;

import models.Qyteti;
import models.Dto.QytetiDto.CreateQytetiDto;
import models.Dto.QytetiDto.UpdateQytetiDto;
import repository.QytetiRepository;

public class QytetiService {
    private final QytetiRepository qytetiRepository;

    public QytetiService() {
        this.qytetiRepository = new QytetiRepository();
    }

    public Qyteti getById(int id) throws Exception {
        if (id <= 0) throw new Exception("ID nuk është valid!");
        Qyteti qyteti = qytetiRepository.getById(id);
        if (qyteti == null) throw new Exception("Qyteti me ID " + id + " nuk ekziston.");
        return qyteti;
    }

    public Qyteti create(CreateQytetiDto dto) throws Exception {
        if (dto.getEmri() == null || dto.getEmri().isBlank() || dto.getKomunaId() <= 0) {
            throw new Exception("Të dhënat për qytetin nuk janë valide.");
        }
        Qyteti qyteti = qytetiRepository.create(dto);
        if (qyteti == null) throw new Exception("Qyteti nuk u krijua.");
        return qyteti;
    }

    public Qyteti update(UpdateQytetiDto dto) throws Exception {
        if (dto.getId() <= 0) throw new Exception("ID për përditësim nuk është valid.");
        Qyteti qyteti = qytetiRepository.update(dto);
        if (qyteti == null) throw new Exception("Përditësimi dështoi.");
        return qyteti;
    }

    public void delete(int id) throws Exception {
        if (id <= 0) throw new Exception("ID për fshirje nuk është valid.");
        qytetiRepository.delete(id);
    }
}
