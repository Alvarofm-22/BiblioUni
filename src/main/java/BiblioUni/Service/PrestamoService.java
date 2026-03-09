package BiblioUni.Service;

import BiblioUni.Model.Prestamo;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface PrestamoService {

    Prestamo registrarPrestamo(Long usuarioId, String isbn, LocalDate fechaDevolucion);

    Collection<Prestamo> listarPrestamos();

    Prestamo buscarPrestamo(Long id);
}