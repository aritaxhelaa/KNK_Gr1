package services;

import models.Adresa;
import models.Dto.AdresaDto.CreateAdresaDto;
import models.Dto.AdresaDto.UpdateAdresaDto;
import repository.AdresaRepository;

public class AdresaService {
    private AdresaRepository adresaRepository;

    public AdresaService() {
        this.adresaRepository = new AdresaRepository();
    }

    public Adresa getById(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID nuk është valid!");
        }
        Adresa adresa = this.adresaRepository.getById(id);
        if (adresa == null) {
            throw new Exception("Adresa me ID: " + id + " nuk ekziston.");
        }
        return adresa;
    }

    public Adresa create(CreateAdresaDto dto) throws Exception {
        if (dto.getRruga().isEmpty() || dto.getKodi_postar() <= 0) {
            throw new Exception("Të dhënat për krijim nuk janë valide.");
        }
        Adresa adresa = this.adresaRepository.create(dto);
        if (adresa == null) {
            throw new Exception("Adresa nuk u krijua!");
        }
        return adresa;
    }

    public Adresa update(UpdateAdresaDto dto) throws Exception {
        if (dto.getId() <= 0) {
            throw new Exception("ID nuk është valid për përditësim.");
        }
        if (dto.getRruga() == null && dto.getNumri() == null && dto.getKodi_postar() == null) {
            throw new Exception("Të paktën një fushë duhet të ndryshohet për përditësim.");
        }
        Adresa adresa = this.adresaRepository.update(dto);
        if (adresa == null) {
            throw new Exception("Përditësimi i adresës dështoi!");
        }
        return adresa;
    }

    public void delete(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID nuk është valid për fshirje.");
        }

        this.adresaRepository.delete(id);
    }

}
