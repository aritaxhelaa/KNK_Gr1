package services;


import models.Lagjja;
import repository.LagjjaRepository;

public class LagjjaService {
    private LagjjaRepository lagjjaRepository;

    public LagjjaService(){
        this.lagjjaRepository = new LagjjaRepository();
    }

    public Lagjja getById(int id) throws Exception{
        if (id <= 0) {
            throw new Exception("ID nuk është valid!");
        }
        Lagjja lagjja = this.lagjjaRepository.getById(id);
        if (lagjja == null) {
            throw new Exception("Lagjja me ID: " + id + " nuk ekziston.");
        }
        return lagjja;
    }
}
