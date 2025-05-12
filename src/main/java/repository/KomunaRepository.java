package repository;

import models.Komuna;
import models.Dto.KomunaDto.CreateKomunaDto;
import models.Dto.KomunaDto.UpdateKomunaDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KomunaRepository extends BaseRepository<Komuna, CreateKomunaDto, UpdateKomunaDto> {

    public KomunaRepository() {
        super("komuna");
    }

    @Override
    protected Komuna fromResultSet(ResultSet rs) throws SQLException {
        return Komuna.getInstance(rs);
    }

    @Override
    public Komuna create(CreateKomunaDto dto) {
        String query = "INSERT INTO komuna (emri) VALUES (?)";
        try {
            PreparedStatement ps = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getEmri());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                return this.getById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Komuna update(UpdateKomunaDto dto) {
        String query = "UPDATE komuna SET emri = ? WHERE id = ?";
       try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setString(1, dto.getEmri());
            ps.setInt(2, dto.getId());

            int updated = ps.executeUpdate();
            if (updated == 1) {
                return this.getById(dto.getId());           }
        } catch (SQLException e) {
            e.printStackTrace();
       } return null;
    }

    // ✅ Metodë e re për t’i marrë të gjitha komunat
    public ArrayList<Komuna> getAll() {
        ArrayList<Komuna> komunaList = new ArrayList<>();
        String query = "SELECT * FROM komuna";

        try (Statement stmt = this.connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                komunaList.add(Komuna.getInstance(rs));
            }
       } catch (SQLException e) {
            e.printStackTrace();
       }

        return komunaList;
    }
}
