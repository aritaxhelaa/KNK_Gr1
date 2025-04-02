package repository;

import java.sql.Connection;
import java.util.List;

public abstract class BaseRepository <Model, UpdateDto, CreateDto>{
    protected Connection connection;
    public abstract String tableName();

    public abstract Model getById(int id);

    public abstract List<Model> getAll();
}
