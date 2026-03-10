package BiblioUni.Service;

import BiblioUni.Model.Prestamo;

import java.time.LocalDate;
import java.util.Collection;

public interface PrestamoService {

    Prestamo registrarPrestamo(Long usuarioId, String isbn, LocalDate fechaDevolucion, Long cuponId);

    Collection<Prestamo> listarPrestamos();

    Prestamo buscarPrestamo(Long id);
}