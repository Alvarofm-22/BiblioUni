package BiblioUni.Repository;

import BiblioUni.Model.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuponRepository extends JpaRepository<Cupon, Long> {
}

