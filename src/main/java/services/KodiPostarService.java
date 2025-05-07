package services;

import models.KodiPostar;
import models.Dto.KodiPostarDto.CreateKodiPostarDto;
import models.Dto.KodiPostarDto.UpdateKodiPostarDto;
import repository.KodiPostarRepository;

public class KodiPostarService {
    private final KodiPostarRepository kodiPostarRepository;

    public KodiPostarService() {
        this.kodiPostarRepository = new KodiPostarRepository();
    }

    public KodiPostar getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID nuk është valid.");
        }

        KodiPostar kodi = this.kodiPostarRepository.getById(id);
        if (kodi == null) {
            throw new Exception("Kodi me ID " + id + " nuk ekziston.");
        }

        return kodi;
    }

    public KodiPostar create(CreateKodiPostarDto dto) throws Exception {
        if (dto.getKodi() == null || dto.getKodi().isEmpty()) {
            throw new Exception("Kodi postar nuk është valid.");
        }

        return this.kodiPostarRepository.create(dto);
    }

    public void update(UpdateKodiPostarDto dto) throws Exception {
        if (dto.getId() <= 0) {
            throw new Exception("ID për përditësim nuk është valid.");
        }

        this.kodiPostarRepository.update(dto);
    }

    public void delete(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID për fshirje nuk është valid.");
        }

        this.kodiPostarRepository.delete(id);
    }
}
