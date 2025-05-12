package repository;

import models.KodiPostar;
import models.Dto.KodiPostarDto.CreateKodiPostarDto;
import models.Dto.KodiPostarDto.UpdateKodiPostarDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    // 🆕 Shto kjo metodë për kërkim sipas emrit të Komunës
    public KodiPostar getByKomunaName(String komunaName) {
        String query = """
            SELECT kp.* FROM kodi_postar kp
            JOIN komuna k ON kp.komuna_id = k.id
            WHERE k.emri = ?
        """;

        try (PreparedStatement ps = this.connection.prepareStatement(query)) {
            ps.setString(1, komunaName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return KodiPostar.getInstance(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    // Vazhdohet nga kodi yt ekzistues
    public List<KodiPostar> getByKomunaId(int komunaId) {
        List<KodiPostar> lista = new ArrayList<>();
        String query = "SELECT * FROM kodi_postar WHERE komuna_id = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(query)) {
            ps.setInt(1, komunaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(KodiPostar.getInstance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
