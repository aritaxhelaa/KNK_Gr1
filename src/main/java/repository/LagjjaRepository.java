package repository;

import models.Lagjja;
import models.Dto.LagjjaDto.CreateLagjjaDto;
import models.Dto.LagjjaDto.UpdateLagjjaDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LagjjaRepository extends BaseRepository<Lagjja, CreateLagjjaDto, UpdateLagjjaDto> {

    public LagjjaRepository() {
        super("lagjja");
    }

    @Override
    public Lagjja fromResultSet(ResultSet resultSet) throws SQLException {
        return Lagjja.getInstance(resultSet);
    }

    @Override
    public Lagjja create(CreateLagjjaDto dto) {
        String query = """
                INSERT INTO lagjja (emri, komuna_id, siperfaqja, pershkrimi, statusi_zyrtar)
                VALUES (?, ?, ?, ?, ?)
                """;
        try (PreparedStatement ps = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getEmri());
            ps.setInt(2, dto.getKomunaId());
            ps.setDouble(3, dto.getSiperfaqja());
            ps.setString(4, dto.getPershkrimi());
            ps.setBoolean(5, dto.isStatusiZyrtar());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return this.getById(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Lagjja update(UpdateLagjjaDto dto) {
        StringBuilder query = new StringBuilder("UPDATE lagjja SET ");
        List<Object> params = new ArrayList<>();

        if (dto.getEmri() != null) {
            query.append("emri = ?, ");
            params.add(dto.getEmri());
        }
        if (dto.getKomunaId() > 0) {
            query.append("komuna_id = ?, ");
            params.add(dto.getKomunaId());
        }
        if (dto.getSiperfaqja() != null) {
            query.append("siperfaqja = ?, ");
            params.add(dto.getSiperfaqja());
        }
        if (dto.getPershkrimi() != null) {
            query.append("pershkrimi = ?, ");
            params.add(dto.getPershkrimi());
        }
        query.append("statusi_zyrtar = ?, ");
        params.add(dto.isStatusiZyrtar());

        query.setLength(query.length() - 2); // remove last comma
        query.append(" WHERE id = ?");
        params.add(dto.getId());

        try (PreparedStatement ps = this.connection.prepareStatement(query.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            int updated = ps.executeUpdate();
            if (updated == 1) {
                return this.getById(dto.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Lagjja> getByKomunaId(int komunaId) {
        List<Lagjja> lista = new ArrayList<>();
        String query = "SELECT * FROM lagjja WHERE komuna_id = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(query)) {
            ps.setInt(1, komunaId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(Lagjja.getInstance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
