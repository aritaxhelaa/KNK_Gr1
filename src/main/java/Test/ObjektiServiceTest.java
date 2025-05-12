package Test;

import models.Objekti;
import models.Dto.ObjektiDto.CreateObjektiDto;
import models.Dto.ObjektiDto.UpdateObjektiDto;
import services.ObjektiService;

public class ObjektiServiceTest {
    public static void main(String[] args) {
        ObjektiService service = new ObjektiService();

        try {
            CreateObjektiDto createDto = new CreateObjektiDto(1, "Shkolla 'Naim Frashëri'", 1, "12B", 1);
            Objekti objekti = service.create(createDto);
            System.out.println("Objekti i krijuar: " + objekti.getEmri());

            Objekti ngaDb = service.getById(objekti.getId());
            System.out.println("Nga DB: " + ngaDb.getEmri() + ", Numri: " + ngaDb.getNumriNderteses());

            UpdateObjektiDto updateDto = new UpdateObjektiDto(objekti.getId(), 1, "Shkolla 'Eqrem Çabej'", 1, "12C", 1);
            service.update(updateDto);

            Objekti updated = service.getById(objekti.getId());
            System.out.println("Pas përditësimit: " + updated.getEmri());

            service.delete(objekti.getId());
            System.out.println("Objekti u fshi me sukses.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
