package BiblioUni.Service;

import BiblioUni.Model.Cupon;
import BiblioUni.Repository.CuponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CuponServiceImpl implements CuponService {

    private final CuponRepository cuponRepository;

    @Override
    public Collection<Cupon> listarTodo() {
        return (Collection<Cupon>) cuponRepository.findAll();
    }

    @Override
    public Cupon buscarPorId(Long id) {
        return cuponRepository.findById(id).orElse(null);
    }

    @Override
    public Cupon insertar(Cupon cupon) {
        return cuponRepository.save(cupon);
    }

    @Override
    public Cupon actualizar(Cupon cupon) {
        return cuponRepository.save(cupon);
    }

    @Override
    public void eliminar(Long id) {
        cuponRepository.deleteById(id);
    }
}