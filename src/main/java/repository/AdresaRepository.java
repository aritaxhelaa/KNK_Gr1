package repository;

import models.Adresa;
import models.Dto.AdresaDto.AdresaViewDto;
import models.Dto.AdresaDto.CreateAdresaDto;
import models.Dto.AdresaDto.UpdateAdresaDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresaRepository extends BaseRepository<Adresa, CreateAdresaDto, UpdateAdresaDto> {

    public AdresaRepository() {
        super("adresa");
    }

    @Override
    public Adresa fromResultSet(ResultSet result) throws SQLException {
        return Adresa.getInstance(result);
    }

    @Override
    public Adresa create(CreateAdresaDto adresaDto) {
        String query = """
                INSERT INTO adresa (rruga, numri, kodi_postar)
                VALUES (?, ?, ?)
                """;
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, adresaDto.getRruga());
            pstm.setInt(2, adresaDto.getNumri());
            pstm.setInt(3, adresaDto.getKodi_postar());
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
    public Adresa update(UpdateAdresaDto adresaDto) {
        StringBuilder query = new StringBuilder("UPDATE adresa SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (adresaDto.getRruga() != null) {
            query.append("rruga = ?, ");
            params.add(adresaDto.getRruga());
        }
        if (adresaDto.getNumri() != null) {
            query.append("numri = ?, ");
            params.add(adresaDto.getNumri());
        }
        if (adresaDto.getKodi_postar() != null) {
            query.append("kodi_postar = ?, ");
            params.add(adresaDto.getKodi_postar());
        }

        if (params.isEmpty()) return getById(adresaDto.getId());

        query.setLength(query.length() - 2); // remove last comma
        query.append(" WHERE id = ?");
        params.add(adresaDto.getId());

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if (updated == 1) return this.getById(adresaDto.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Adresa gjejAdrese(String rruga, int numri, int kodiPostar) {
        String query = "SELECT * FROM adresa WHERE rruga = ? AND numri = ? AND kodi_postar = ?";

        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setString(1, rruga);
            stmt.setInt(2, numri);
            stmt.setInt(3, kodiPostar);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Adresa.getInstance(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<AdresaViewDto> kerkoAdresa(String komuna, String lloji, String vendbanimi, String rruga) {
        List<AdresaViewDto> rezultatet = new ArrayList<>();

        String tabelaVendbanimit = lloji.equalsIgnoreCase("Fshat") ? "fshati" : "qyteti";
        String kolonaID = lloji.equalsIgnoreCase("Fshat") ? "fshati_id" : "qyteti_id";

        String query = """
        SELECT 
            k.emri AS komuna,
            v.emri AS vendbanimi,
            l.emri AS lagjia,
            a.rruga,
            a.numri,
            kp.kodi AS kodi_postar
        FROM adresa a
        JOIN rruga r ON LOWER(a.rruga) = LOWER(r.emri)
        JOIN lagjja l ON r.lagjja_id = l.id
        JOIN komuna k ON l.komuna_id = k.id
        JOIN kodi_postar kp ON a.kodi_postar::VARCHAR = kp.kodi
        JOIN %s v ON r.%s = v.id
        WHERE LOWER(k.emri) = ?
          AND LOWER(v.emri) = ?
          AND LOWER(a.rruga) LIKE ?
        """.formatted(tabelaVendbanimit, kolonaID);

        try (PreparedStatement ps = this.connection.prepareStatement(query)) {
            ps.setString(1, komuna.toLowerCase());
            ps.setString(2, vendbanimi.toLowerCase());
            ps.setString(3, "%" + rruga.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rezultatet.add(new AdresaViewDto(
                        rs.getString("komuna"),
                        rs.getString("vendbanimi"),
                        rs.getString("lagjia"),
                        rs.getString("rruga"),
                        rs.getInt("numri"),
                        rs.getInt("kodi_postar")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rezultatet;
    }

    public void ruajKerkim(int userId, int adresaId) {
        String query = "INSERT INTO recent_searches (user_id, adresa_id) VALUES (?, ?)";
        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, adresaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Adresa> getRecentSearchesByUser(int userId) {
        List<Adresa> lista = new ArrayList<>();

        String query = """
            SELECT a.*, rs.search_time
            FROM recent_searches rs
            JOIN adresa a ON rs.adresa_id = a.id
            WHERE rs.user_id = ?
            ORDER BY rs.search_time DESC
            LIMIT 10
        """;

        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Adresa adresa = Adresa.getInstance(rs);
                adresa.setDataKerkimit(rs.getTimestamp("search_time"));
                lista.add(adresa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public Adresa gjejNgaDto(AdresaViewDto dto) {
        String query = "SELECT * FROM adresa WHERE LOWER(rruga) = LOWER(?) AND numri = ? AND kodi_postar = ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setString(1, dto.getRruga());
            stmt.setInt(2, dto.getNumri());
            stmt.setInt(3, dto.getKodiPostar());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Adresa.getInstance(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
