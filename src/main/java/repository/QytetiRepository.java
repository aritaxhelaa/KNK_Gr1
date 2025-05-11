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
        """;

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, dto.getEmri());
            pstm.setInt(2, dto.getKomunaId());
            pstm.execute();

            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                return getById(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
}
