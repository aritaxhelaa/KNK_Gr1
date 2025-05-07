package repository;

import models.Lagjja;
import models.Dto.LagjjaDto.CreateLagjjaDto;
import models.Dto.LagjjaDto.UpdateLagjjaDto;

import java.sql.*;
import java.util.ArrayList;

public class LagjjaRepository extends BaseRepository<Lagjja, CreateLagjjaDto, UpdateLagjjaDto> {

    public LagjjaRepository() {
        super("lagjja");
    }

    @Override
    public Lagjja fromResultSet(ResultSet result) throws SQLException {
        return Lagjja.getInstance(result);
    }

    @Override
    public Lagjja create(CreateLagjjaDto lagjjaDto) {
        String query = """
                INSERT INTO lagjja (emri, qyteti_id, komuna_id)
                VALUES (?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, lagjjaDto.getEmri());
            pstm.setInt(2, lagjjaDto.getQytetiId());
            pstm.setInt(3, lagjjaDto.getKomunaId());
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
    public Lagjja update(UpdateLagjjaDto lagjjaDto) {
        StringBuilder query = new StringBuilder("UPDATE lagjja SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (lagjjaDto.getEmri() != null) {
            query.append("emri = ?, ");
            params.add(lagjjaDto.getEmri());
        }
        if (lagjjaDto.getQytetiId() > 0) {
            query.append("qyteti_id = ?, ");
            params.add(lagjjaDto.getQytetiId());
        }
        if (lagjjaDto.getKomunaId() > 0) {
            query.append("komuna_id = ?, ");
            params.add(lagjjaDto.getKomunaId());
        }

        if (params.isEmpty()) {
            return getById(lagjjaDto.getId());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");
        params.add(lagjjaDto.getId());

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(lagjjaDto.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}