package Test;

import models.Adresa;
import models.Dto.AdresaDto.CreateAdresaDto;
import models.Dto.AdresaDto.UpdateAdresaDto;
import services.AdresaService;

public class AdresaServiceTest {
    public static void main(String[] args) {
        AdresaService adresaService = new AdresaService();

        try {
            // 1. Krijo një adresë të re me ID = 0 si placeholder
            CreateAdresaDto create = new CreateAdresaDto(0, "Rruga Iliria", 15, 10000);
            Adresa adresa = adresaService.create(create);
            System.out.println("Adresa e krijuar: " + adresa.getRruga() + " nr. " + adresa.getNumri());

            // 2. Lexo adresën sipas ID nga DB
            Adresa ngaDb = adresaService.getById(adresa.getId());
            System.out.println("Nga DB: " + ngaDb.getRruga() + ", Kodi: " + ngaDb.getKodiPostar());

            // 3. Përditëso adresën me konstruktorin e UpdateAdresaDto
            UpdateAdresaDto update = new UpdateAdresaDto(adresa.getId(), "Rruga e Dëshmorëve", 16, 10001);
            adresaService.update(update);

            // 4. Lexim pas përditësimit
            Adresa updated = adresaService.getById(adresa.getId());
            System.out.println("Pas përditësimit: " + updated.getRruga() + ", Kodi: " + updated.getKodiPostar());

            // 5. Fshije nga DB
            adresaService.delete(adresa.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
