package repository;

import models.Qyteti;
import models.Dto.QytetiDto.CreateQytetiDto;
import models.Dto.QytetiDto.UpdateQytetiDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QytetiRepository  extends BaseRepository<Qyteti, CreateQytetiDto, UpdateQytetiDto>{
    public QytetiRepository() {
        super("qyteti");
    }

    @Override
    public Qyteti fromResultSet(ResultSet resultSet) throws SQLException {
        return Qyteti.getInstance(resultSet);
    }

    @Override
    public Qyteti create(CreateQytetiDto dto) {
        String query = """
        INSERT INTO qyteti (emri, komuna_id)
        VALUES (?, ?)
        RETURNING id
    """;

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, dto.getEmri());
            pstm.setInt(2, dto.getKomunaId());

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                return getById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            if (e.getMessage().contains("duplicate key")) {
                System.err.println("Qyteti me këto të dhëna ekziston tashmë!");

                return getByNameAndKomunaId(dto.getEmri(), dto.getKomunaId());
            }
        }
        return null;
    }

    @Override
    public Qyteti update(UpdateQytetiDto dto) {
        StringBuilder query = new StringBuilder("UPDATE qyteti SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (dto.getEmri() != null) {
            query.append("emri = ?, ");
            params.add(dto.getEmri());
        }

        query.append("komuna_id = ?, ");
        params.add(dto.getKomunaId());

        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");
        params.add(dto.getId());

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }

            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return getById(dto.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Qyteti getByNameAndKomunaId(String emri, int komunaId) {
        String query = "SELECT * FROM qyteti WHERE emri = ? AND komuna_id = ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setString(1, emri);
            stmt.setInt(2, komunaId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Qyteti.getInstance(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
