package BiblioUni.Service;

import BiblioUni.Model.Multa;
import BiblioUni.Model.Prestamo;
import java.util.List;

public interface MultaService {

    Multa calcularMulta(Prestamo prestamo);

    List<Multa> listar();

    Multa buscarPorId(Long id);

    Multa actualizar(Multa multa);

    // Nuevo método para registrar una multa manualmente
    Multa guardar(Multa multa);
}