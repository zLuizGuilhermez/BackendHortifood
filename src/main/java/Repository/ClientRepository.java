package Repository;

import DTO.Inside.ClientDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ClientRepository implements MongoRepository<ClientDTO, String> {

    @Override
    public <S extends ClientDTO> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends ClientDTO> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends ClientDTO> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ClientDTO> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends ClientDTO> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends ClientDTO> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ClientDTO> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ClientDTO> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ClientDTO, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends ClientDTO> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ClientDTO> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<ClientDTO> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<ClientDTO> findAll() {
        return List.of();
    }

    @Override
    public List<ClientDTO> findAllById(Iterable<String> strings) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(ClientDTO entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends ClientDTO> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<ClientDTO> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<ClientDTO> findAll(Pageable pageable) {
        return null;
    }
}
