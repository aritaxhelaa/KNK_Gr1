
package Test;

import models.Komuna;
import models.Dto.KomunaDto.CreateKomunaDto;
import models.Dto.KomunaDto.UpdateKomunaDto;
import services.KomunaService;

public class KomunaServiceTest {

    private KomunaService komunaService;

    public static void main(String[] args) {
        KomunaServiceTest test = new KomunaServiceTest();
        test.setUp();
        test.testCreateKomuna();
        test.testUpdateKomuna();
        test.testGetById();
        test.testDeleteKomuna();
    }

    public void setUp() {
        komunaService = new KomunaService();  // Krijo instancë të service-t
    }

    public void testCreateKomuna() {
        // Testimi i krijimit të një komune
        CreateKomunaDto createKomunaDto = new CreateKomunaDto("Komuna Test");
        try {
            Komuna komuna = komunaService.create(createKomunaDto);
            if (komuna != null && komuna.getEmri().equals("Komuna Test")) {
                System.out.println("Testi i krijimit të komunës kaloi: " + komuna.getEmri());
            } else {
                System.out.println("Testi i krijimit të komunës dështoi");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testUpdateKomuna() {
        // Testimi i përditësimit të një komune
        CreateKomunaDto createKomunaDto = new CreateKomunaDto("Komuna Test");
        try {
            Komuna komuna = komunaService.create(createKomunaDto);
            // Duhet të kalohen të tre argumentet (id, emri, kodi_postar)
            UpdateKomunaDto updateKomunaDto = new UpdateKomunaDto(komuna.getId(), "Komuna Test Update"); // Kodi postari është i nevojshëm
            Komuna updatedKomuna = komunaService.update(updateKomunaDto);

            if (updatedKomuna != null && updatedKomuna.getEmri().equals("Komuna Test Update")) {
                System.out.println("Testi i përditësimit të komunës kaloi: " + updatedKomuna.getEmri());
            } else {
                System.out.println("Testi i përditësimit të komunës dështoi");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testGetById() {
        // Testimi i marrjes së një komune nga ID
        CreateKomunaDto createKomunaDto = new CreateKomunaDto("Komuna Test");
        try {
            Komuna komuna = komunaService.create(createKomunaDto);
            Komuna result = komunaService.getById(komuna.getId());

            if (result != null && result.getId() == komuna.getId()) {
                System.out.println("Testi i marrjes së komunës nga ID kaloi: " + result.getEmri());
            } else {
                System.out.println("Testi i marrjes së komunës nga ID dështoi");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testDeleteKomuna() {
        // Testimi i fshirjes së një komune
        CreateKomunaDto createKomunaDto = new CreateKomunaDto("Komuna Test");
        try {
            Komuna komuna = komunaService.create(createKomunaDto);
            komunaService.delete(komuna.getId());
            System.out.println("Testi i fshirjes së komunës kaloi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
