package Test;

import models.Lagjja;
import models.Dto.LagjjaDto.CreateLagjjaDto;
import models.Dto.LagjjaDto.UpdateLagjjaDto;
import services.LagjjaService;

public class LagjjaServiceTest {
    public static void main(String[] args) {
        LagjjaService service = new LagjjaService();

        try {
            // Krijo një lagje me të gjitha fushat e nevojshme
            CreateLagjjaDto createDto = new CreateLagjjaDto(
                    "Lagjja Dardania",
                    1,                      // komunaId
                    250.5,                  // siperfaqja
                    "Lagje në pjesën qendrore të qytetit",  // pershkrimi
                    true                   // statusiZyrtar
            );
            Lagjja lagjja = service.create(createDto);
            System.out.println("Lagjja e krijuar: " + lagjja.getEmri());

            Lagjja ngaDb = service.getById(lagjja.getId());
            System.out.println("Nga DB: " + ngaDb.getEmri());

            // Përditëso emrin dhe përshkrimin e lagjes
            UpdateLagjjaDto updateDto = new UpdateLagjjaDto(
                    lagjja.getId(),
                    "Lagjja Arbëria",
                    1,              // komunaId
                    300.0,          // siperfaqja e re
                    "Lagje e njohur për ndërtesa të reja",
                    false           // statusiZyrtar
            );
            service.update(updateDto);

            Lagjja updated = service.getById(lagjja.getId());
            System.out.println("Pas përditësimit: " + updated.getEmri());

            // Fshi lagjen në fund (opsionale për testim)
            // service.delete(lagjja.getId());
            // System.out.println("Lagjja u fshi me sukses.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
