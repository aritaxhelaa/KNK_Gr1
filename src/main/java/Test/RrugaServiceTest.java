package Test;

import models.Rruga;
import models.Dto.RrugaDto.CreateRrugaDto;
import models.Dto.RrugaDto.UpdateRrugaDto;
import services.RrugaService;

public class RrugaServiceTest {
    public static void main(String[] args) {
        RrugaService service = new RrugaService();

        try {
            // 1. Krijimi i rrugës (me komunaId, qytetiId, fshatiId, lagjjaId)
            CreateRrugaDto createDto = new CreateRrugaDto(
                    "Rruga Agim Ramadani",
                    1, // komunaId
                    1, // qytetiId
                    0, // fshatiId (0 nëse nuk ka)
                    1  // lagjjaId
            );

            Rruga rruga = service.create(createDto);
            System.out.println("Rruga e krijuar: " + rruga.getEmri());

            // 2. Leximi nga DB
            Rruga ngaDb = service.getById(rruga.getId());
            System.out.println("Nga DB: " + ngaDb.getEmri());

            // 3. Përditësimi
            UpdateRrugaDto updateDto = new UpdateRrugaDto(
                    rruga.getId(),
                    "Rruga Zahir Pajaziti",
                    1,  // komunaId
                    1,  // qytetiId
                    0,  // fshatiId
                    1   // lagjjaId
            );

            service.update(updateDto);

            Rruga updated = service.getById(rruga.getId());
            System.out.println("Pas përditësimit: " + updated.getEmri());

            // 4. Fshirja
            service.delete(rruga.getId());
            System.out.println("Rruga u fshi me sukses.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
