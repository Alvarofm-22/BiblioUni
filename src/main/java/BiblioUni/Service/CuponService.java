package BiblioUni.Service;

import BiblioUni.Model.Cupon;

import java.util.Collection;

public interface CuponService {

    public abstract Collection<Cupon> listarTodo();

    public abstract Cupon buscarPorId(Long id);

    public abstract Cupon insertar(Cupon cupon);

    public abstract Cupon actualizar(Cupon cupon);

    public abstract void eliminar(Long id);

}