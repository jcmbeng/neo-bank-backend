package fr.adservio.neobankbackend.services;
import java.util.List;

public interface AbStractService<T, ID>
{
    T findById(ID id);

    void delete(ID id);

    T active(ID id, boolean active);

    List<T> findAll();

    T save(Object request);
}
