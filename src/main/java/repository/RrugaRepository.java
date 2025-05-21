package repository;

import models.Rruga;
import models.Dto.RrugaDto.CreateRrugaDto;
import models.Dto.RrugaDto.UpdateRrugaDto;
import models.Dto.RrugaDto.RrugaViewDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RrugaRepository extends BaseRepository<Rruga, CreateRrugaDto, UpdateRrugaDto> {

    public RrugaRepository() {
        super("rruga");
    }

    @Override
    public Rruga fromResultSet(ResultSet result) throws SQLException {
        return Rruga.getInstance(result);
    }

    @Override
    public Rruga create(CreateRrugaDto rrugaDto) {
        String query = """
                INSERT INTO rruga (emri, komuna_id, qyteti_id, fshati_id, lagjja_id)
                VALUES (?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, rrugaDto.getEmri());
            pstm.setInt(2, rrugaDto.getKomunaId());
            pstm.setInt(3, rrugaDto.getQytetiId());
            pstm.setInt(4, rrugaDto.getFshatiId());
            pstm.setInt(5, rrugaDto.getLagjjaId());
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
    public Rruga update(UpdateRrugaDto rrugaDto) {
        StringBuilder query = new StringBuilder("UPDATE rruga SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (rrugaDto.getEmri() != null) {
            query.append("emri = ?, ");
            params.add(rrugaDto.getEmri());
        }
        if (rrugaDto.getKomunaId() > 0) {
            query.append("komuna_id = ?, ");
            params.add(rrugaDto.getKomunaId());
        }
        if (rrugaDto.getQytetiId() > 0) {
            query.append("qyteti_id = ?, ");
            params.add(rrugaDto.getQytetiId());
        }
        if (rrugaDto.getFshatiId() > 0) {
            query.append("fshati_id = ?, ");
            params.add(rrugaDto.getFshatiId());
        }
        if (rrugaDto.getLagjjaId() > 0) {
            query.append("lagjja_id = ?, ");
            params.add(rrugaDto.getLagjjaId());
        }

        if (params.isEmpty()) {
            return getById(rrugaDto.getId());
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");
        params.add(rrugaDto.getId());

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(rrugaDto.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Rruga> getByKomunaId(int komunaId) {
        List<Rruga> lista = new ArrayList<>();
        String query = "SELECT * FROM rruga WHERE komuna_id = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(query)) {
            ps.setInt(1, komunaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(Rruga.getInstance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public List<RrugaViewDto> getByKomunaAndVendbanimi(int komunaId, Integer qytetiId, Integer fshatiId) {
        List<RrugaViewDto> results = new ArrayList<>();
        String query;

        if (qytetiId != null) {
            query = """
        SELECT r.id, r.emri, k.emri as komuna, q.emri as qyteti, 
               l.emri as lagjia
        FROM rruga r
        JOIN lagjja l ON r.lagjja_id = l.id
        JOIN komuna k ON l.komuna_id = k.id
        JOIN qyteti q ON r.qyteti_id = q.id
        WHERE k.id = ? AND q.id = ?
        """;
        } else if (fshatiId != null) {
            query = """
        SELECT r.id, r.emri, k.emri as komuna, f.emri as fshati, 
               l.emri as lagjia
        FROM rruga r
        JOIN lagjja l ON r.lagjja_id = l.id
        JOIN komuna k ON l.komuna_id = k.id
        JOIN fshati f ON r.fshati_id = f.id
        WHERE k.id = ? AND f.id = ?
        """;
        } else {
            return results;
        }

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, komunaId);
            ps.setInt(2, qytetiId != null ? qytetiId : fshatiId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results.add(new RrugaViewDto(
                        rs.getString("komuna"),
                        qytetiId != null ? rs.getString("qyteti") : rs.getString("fshati"),
                        rs.getString("lagjia"),
                        rs.getString("emri"),
                        null
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }}
