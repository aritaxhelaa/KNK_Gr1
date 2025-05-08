package repository;

import models.Objekti;
import models.TipiObjektit;
import models.Dto.ObjektiDto.CreateObjektiDto;
import models.Dto.ObjektiDto.UpdateObjektiDto;

import java.sql.*;
import java.util.ArrayList;

public class ObjektiRepository extends BaseRepository<Objekti, CreateObjektiDto, UpdateObjektiDto> {

    public ObjektiRepository() {
        super("objekti");
    }

    @Override
    public Objekti fromResultSet(ResultSet result) throws SQLException {
        return Objekti.getInstance(result);
    }

    @Override
    public Objekti create(CreateObjektiDto objektiDto) {
        String query = """
                INSERT INTO objekti (tipi_objektit_id, emri, rruga_id, numri_nderteses, kodi_postar_id)
                VALUES (?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, objektiDto.getTipiObjektitId());
            pstm.setString(2, objektiDto.getEmri());
            pstm.setInt(3, objektiDto.getRrugaId());
            pstm.setString(4, objektiDto.getNumriNderteses());
            pstm.setInt(5, objektiDto.getKodiPostarId());
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
    public Objekti update(UpdateObjektiDto objektiDto) {
        StringBuilder query = new StringBuilder("UPDATE objekti SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (objektiDto.getTipiObjektitId() != 0) {
            query.append("tipi_objektit_id = ?, ");
            params.add(objektiDto.getTipiObjektitId());
        }
        if (objektiDto.getEmri() != null) {
            query.append("emri = ?, ");
            params.add(objektiDto.getEmri());
        }
        if (objektiDto.getRrugaId() != 0) {
            query.append("rruga_id = ?, ");
            params.add(objektiDto.getRrugaId());
        }
        if (objektiDto.getNumriNderteses() != null) {
            query.append("numri_nderteses = ?, ");
            params.add(objektiDto.getNumriNderteses());
        }
        if (objektiDto.getKodiPostarId() != 0) {
            query.append("kodi_postar_id = ?, ");
            params.add(objektiDto.getKodiPostarId());
        }

        if (params.isEmpty()) {
            return getById(objektiDto.getId());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");
        params.add(objektiDto.getId());

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(objektiDto.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}