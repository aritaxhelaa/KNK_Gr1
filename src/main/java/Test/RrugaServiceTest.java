package Test;

import models.Rruga;
import models.Dto.RrugaDto.CreateRrugaDto;
import models.Dto.RrugaDto.UpdateRrugaDto;
import services.RrugaService;

public class RrugaServiceTest {
    public static void main(String[] args) {
        RrugaService service = new RrugaService();

        try {
            CreateRrugaDto createDto = new CreateRrugaDto("Rruga Agim Ramadani", 1, 1);
            Rruga rruga = service.create(createDto);
            System.out.println("Rruga e krijuar: " + rruga.getEmri());

            Rruga ngaDb = service.getById(rruga.getId());
            System.out.println("Nga DB: " + ngaDb.getEmri());

            UpdateRrugaDto updateDto = new UpdateRrugaDto(rruga.getId(), "Rruga Zahir Pajaziti", 1, 1);
            service.update(updateDto);

            Rruga updated = service.getById(rruga.getId());
            System.out.println("Pas përditësimit: " + updated.getEmri());

            service.delete(rruga.getId());
            System.out.println("Rruga u fshi me sukses.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
