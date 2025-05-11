package repository;

import models.Dto.TipiObjektitDto.CreateTipiObjektitDto;
import models.Dto.TipiObjektitDto.UpdateTipiObjektitDto;
import models.TipiObjektit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TipiObjektitRepository extends BaseRepository<TipiObjektit, CreateTipiObjektitDto, UpdateTipiObjektitDto> {
    public TipiObjektitRepository() {
        super("tipi_objektit");
    }

    @Override
    public TipiObjektit fromResultSet(ResultSet resultSet) throws SQLException {
        return TipiObjektit.getInstance(resultSet);
    }

    @Override
    public TipiObjektit create(CreateTipiObjektitDto dto) {
        String query = """
            INSERT INTO tipi_objektit (emri)
            VALUES (?)
        """;

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, dto.getEmri());
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
    public TipiObjektit update(UpdateTipiObjektitDto dto) {
        if (dto.getEmri() == null) {
            return getById(dto.getId());
        }

        String query = """
            UPDATE tipi_objektit
            SET emri = ?
            WHERE id = ?
        """;

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, dto.getEmri());
            pstm.setInt(2, dto.getId());

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
