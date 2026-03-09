package BiblioUni.Repository;

import BiblioUni.Model.Multa;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MultaRepository extends JpaRepository<Multa, Long> {

    Optional<Multa> findByPrestamoId(Long prestamoId);

    @EntityGraph(attributePaths = {"prestamo","prestamo.usuario","prestamo.libro"})
    Optional<Multa> findById(Long id);
}