package Test;

import models.Lagjja;
import models.Dto.LagjjaDto.CreateLagjjaDto;
import models.Dto.LagjjaDto.UpdateLagjjaDto;
import services.LagjjaService;

public class LagjjaServiceTest {
    public static void main(String[] args) {
        LagjjaService service = new LagjjaService();

        try {
            CreateLagjjaDto createDto = new CreateLagjjaDto("Lagjja Dardania", 1, 2);
            Lagjja lagjja = service.create(createDto);
            System.out.println("Lagjja e krijuar: " + lagjja.getEmri());

            Lagjja ngaDb = service.getById(lagjja.getId());
            System.out.println("Nga DB: " + ngaDb.getEmri());

            UpdateLagjjaDto updateDto = new UpdateLagjjaDto(lagjja.getId(), "Lagjja Arbëria", 1, 2);
            service.update(updateDto);

            Lagjja updated = service.getById(lagjja.getId());
            System.out.println("Pas përditësimit: " + updated.getEmri());

//            service.delete(lagjja.getId());
//            System.out.println("Lagjja u fshi me sukses.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
