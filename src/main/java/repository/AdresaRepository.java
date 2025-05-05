package repository;

import models.Adresa;
import models.Dto.AdresaDto.CreateAdresaDto;
import models.Dto.AdresaDto.UpdateAdresaDto;

import java.sql.*;
import java.util.ArrayList;

public class AdresaRepository extends BaseRepository<Adresa, CreateAdresaDto, UpdateAdresaDto> {

    public AdresaRepository() {
        super("adresa");
    }

    @Override
    public Adresa fromResultSet(ResultSet result) throws SQLException {
        return Adresa.getInstance(result);
    }

    @Override
    public Adresa create(CreateAdresaDto adresaDto) {
        String query = """
                INSERT INTO adresa (rruga, numri, kodiPostar)
                VALUES (?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, adresaDto.getRruga());
            pstm.setInt(2, adresaDto.getNumri());
            pstm.setInt(3, adresaDto.getKodi_postar());
            pstm.execute();

            ResultSet resultSet = pstm.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                return this.getById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Adresa update(UpdateAdresaDto adresaDto) {
        StringBuilder query = new StringBuilder("UPDATE adresa SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (adresaDto.getRruga() != null) {
            query.append("rruga = ?, ");
            params.add(adresaDto.getRruga());
        }
        if (adresaDto.getNumri() != null) {
            query.append("numri = ?, ");
            params.add(adresaDto.getNumri());
        }
        if (adresaDto.getKodi_postar() != null) {
            query.append("kodiPostar = ?, ");
            params.add(adresaDto.getKodi_postar());
        }

        if (params.isEmpty()) {
            return getById(adresaDto.getId());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");
        params.add(adresaDto.getId());

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(adresaDto.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
