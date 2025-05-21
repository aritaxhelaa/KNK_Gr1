package repository;

import models.Fshati;
import models.Dto.FshatiDto.CreateFshatiDto;
import models.Dto.FshatiDto.UpdateFshatiDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FshatiRepository extends BaseRepository<Fshati, CreateFshatiDto, UpdateFshatiDto> {

    public FshatiRepository() {
        super("fshati");
    }

    @Override
    public Fshati fromResultSet(ResultSet resultSet) throws SQLException {
        return Fshati.getInstance(resultSet);
    }

    @Override
    public Fshati create(CreateFshatiDto dto) {
        String query = """
            INSERT INTO fshati (emri, komuna_id)
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
    public Fshati update(UpdateFshatiDto dto) {
        StringBuilder query = new StringBuilder("UPDATE fshati SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (dto.getEmri() != null) {
            query.append("emri = ?, ");
            params.add(dto.getEmri());
        }

        query.append("komuna_id = ?, ");
        params.add(dto.getKomunaId());

        query.setLength(query.length() - 2); // heq presjen e fundit
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

    // Extra: për të marrë të gjithë fshatrat e një komune
    public ArrayList<Fshati> getByKomunaId(int komunaId) {
        ArrayList<Fshati> lista = new ArrayList<>();
        String query = "SELECT * FROM fshati WHERE komuna_id = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(query)) {
            ps.setInt(1, komunaId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(Fshati.getInstance(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Metoda per te marre fshatrat ne baze te emrit te komunes
    public ArrayList<Fshati> getByKomunaName(String komunaName) {
        ArrayList<Fshati> lista = new ArrayList<>();
        String query = """
        SELECT f.* FROM fshati f
        JOIN komuna k ON f.komuna_id = k.id
        WHERE k.emri = ?
    """;

        try (PreparedStatement ps = this.connection.prepareStatement(query)) {
            ps.setString(1, komunaName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(Fshati.getInstance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Fshati getByNameAndKomunaId(String emri, int komunaId) {
        String query = "SELECT * FROM fshati WHERE emri = ? AND komuna_id = ?";
        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setString(1, emri);
            stmt.setInt(2, komunaId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Fshati.getInstance(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
