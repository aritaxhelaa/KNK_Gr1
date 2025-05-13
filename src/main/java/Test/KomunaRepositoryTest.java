package Test;

import models.Komuna;
import models.Dto.KomunaDto.CreateKomunaDto;
import models.Dto.KomunaDto.UpdateKomunaDto;
import repository.KomunaRepository;

import java.util.List;

public class KomunaRepositoryTest {

    private KomunaRepository komunaRepository;

    public static void main(String[] args) {
        KomunaRepositoryTest test = new KomunaRepositoryTest();
        test.setUp();
        test.testCreateKomuna();
        test.testUpdateKomuna();
        test.testGetById();
        test.testGetAll();
    }

    public void setUp() {
        komunaRepository = new KomunaRepository();  // Krijo instancë të repository-t
    }

    public void testCreateKomuna() {
        // Testimi i krijimit të një komune
        CreateKomunaDto createKomunaDto = new CreateKomunaDto("Komuna Test");
        Komuna komuna = komunaRepository.create(createKomunaDto);

        if (komuna != null && komuna.getEmri().equals("Komuna Test")) {
            System.out.println("Testi i krijimit të komunës kaloi: " + komuna.getEmri());
        } else {
            System.out.println("Testi i krijimit të komunës dështoi");
        }
    }

    public void testUpdateKomuna() {
        // Testimi i përditësimit të një komune
        CreateKomunaDto createKomunaDto = new CreateKomunaDto("Komuna Test");
        Komuna komuna = komunaRepository.create(createKomunaDto);

        // Përdorim të tre argumentet që kërkohen nga konstruktorja e UpdateKomunaDto
        UpdateKomunaDto updateKomunaDto = new UpdateKomunaDto(komuna.getId(), "Komuna Test Update"); // Kalojmë id, emrin dhe kodi_postar
        Komuna updatedKomuna = komunaRepository.update(updateKomunaDto);

        if (updatedKomuna != null && updatedKomuna.getEmri().equals("Komuna Test Update")) {
            System.out.println("Testi i përditësimit të komunës kaloi: " + updatedKomuna.getEmri());
        } else {
            System.out.println("Testi i përditësimit të komunës dështoi");
        }
    }

    public void testGetById() {
        // Testimi i marrjes së një komune nga ID
        CreateKomunaDto createKomunaDto = new CreateKomunaDto("Komuna Test");
        Komuna komuna = komunaRepository.create(createKomunaDto);

        Komuna result = komunaRepository.getById(komuna.getId());

        if (result != null && result.getId() == komuna.getId()) {
            System.out.println("Testi i marrjes së komunës nga ID kaloi: " + result.getEmri());
        } else {
            System.out.println("Testi i marrjes së komunës nga ID dështoi");
        }
    }

    public void testGetAll() {
        // Testimi i marrjes së të gjitha komunave
        CreateKomunaDto createKomunaDto1 = new CreateKomunaDto("Komuna 1");
        CreateKomunaDto createKomunaDto2 = new CreateKomunaDto("Komuna 2");
        komunaRepository.create(createKomunaDto1);
        komunaRepository.create(createKomunaDto2);

        List<Komuna> komunaList = komunaRepository.getAll();

        if (komunaList.size() > 0) {
            System.out.println("Testi i marrjes së të gjitha komunave kaloi: " + komunaList.size() + " komuna");
        } else {
            System.out.println("Testi i marrjes së të gjitha komunave dështoi");
        }
    }
}

