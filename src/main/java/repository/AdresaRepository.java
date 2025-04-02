package repository;

import models.Adresa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AdresaRepository {
    private Connection connection {
        this.connection = connection;
    }

    public ArrayList<Adresa> getAll(){
        ArrayList<Adresa> adresat = new ArrayList<>();
        String query = "SELECT * FROM ADRESA";

        try{
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                adresat.add(
                        Adresa.getInstance(resultSet)
                );
            }

        }catch (SQLException ex){
            ex.printStackTrace();

        }return adresat;
    }

   //ktu me duhet me vazhdu me i kriju edhe pjeset e tjera te repositoryt po
   //ide ma e mire eshte perderisa keto metoda jane te njejta per shumicen e tabelva me kriju ni BaseRepository
   //qe me qene te gatshme keshtu qe po e le hapsiren per me e punu ne ate menyre

}
