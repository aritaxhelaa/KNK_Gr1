package Test;

import models.KodiPostar;
import models.Dto.KodiPostarDto.CreateKodiPostarDto;
import models.Dto.KodiPostarDto.UpdateKodiPostarDto;
import services.KodiPostarService;

public class KodiPostarServiceTest {
    public static void main(String[] args) {
        KodiPostarService service = new KodiPostarService();

        try {
            // 1. Krijo kod postar
            CreateKodiPostarDto dto = new CreateKodiPostarDto("70000", "Rrafshi i Dukagjinit", "Pejë", 3);
            KodiPostar kodi = service.create(dto);
            System.out.println("Kodi i krijuar: " + kodi.getKodi());

            // 2. Lexo nga DB
            KodiPostar ngaDb = service.getById(kodi.getId());
            System.out.println("Nga DB: " + ngaDb.getKodi() + ", Regjioni: " + ngaDb.getRegjioni());

            // 3. Përditëso
            UpdateKodiPostarDto update = new UpdateKodiPostarDto(kodi.getId(), "70001", "Nenregjion i ri", "Pejë e Re", 3);
            service.update(update);

            KodiPostar updated = service.getById(kodi.getId());
            System.out.println("Pas përditësimit: " + updated.getKodi() + " - " + updated.getNenregjioni());

            // 4. Fshije
            service.delete(kodi.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
