package repository;

import models.KodiPostar;
import models.Dto.KodiPostarDto.CreateKodiPostarDto;
import models.Dto.KodiPostarDto.UpdateKodiPostarDto;

import java.sql.*;

public class KodiPostarRepository extends BaseRepository<KodiPostar, CreateKodiPostarDto, UpdateKodiPostarDto> {

    public KodiPostarRepository() {
        super("kodi_postar");
    }

    @Override
    protected KodiPostar fromResultSet(ResultSet rs) throws SQLException {
        return KodiPostar.getInstance(rs);
    }

    @Override
    public KodiPostar create(CreateKodiPostarDto dto) {
        String query = """
            INSERT INTO kodi_postar (kodi, nenregjioni, regjioni, komuna_id)
            VALUES (?, ?, ?, ?)
        """;
        try {
            PreparedStatement ps = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getKodi());
            ps.setString(2, dto.getNenregjioni());
            ps.setString(3, dto.getRegjioni());
            ps.setInt(4, dto.getKomunaId());
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
    public KodiPostar update(UpdateKodiPostarDto dto) {
        String query = """
            UPDATE kodi_postar
            SET kodi = ?, nenregjioni = ?, regjioni = ?, komuna_id = ?
            WHERE id = ?
        """;
        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setString(1, dto.getKodi());
            ps.setString(2, dto.getNenregjioni());
            ps.setString(3, dto.getRegjioni());
            ps.setInt(4, dto.getKomunaId());
            ps.setInt(5, dto.getId());

            int updated = ps.executeUpdate();
            if (updated == 1) {
                return this.getById(dto.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
