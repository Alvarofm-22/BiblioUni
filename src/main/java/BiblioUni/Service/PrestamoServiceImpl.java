package BiblioUni.Service;

import BiblioUni.Enum.EstadoPrestamo;
import BiblioUni.Model.*;
import BiblioUni.Repository.CuponRepository;
import BiblioUni.Repository.LibroRepository;
import BiblioUni.Repository.PrestamoRepository;
import BiblioUni.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LibroRepository libroRepository;
    private final CuponRepository cuponRepository; // NUEVO

    @Override
    @Transactional
    public Prestamo registrarPrestamo(Long usuarioId,
                                      String isbn,
                                      LocalDate fechaDevolucion,
                                      Long cuponId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Libro libro = libroRepository.findById(isbn)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        if(libro.getCantDisponible() <= 0){
            throw new RuntimeException("No hay libros disponibles");
        }

        Prestamo prestamo = new Prestamo();

        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);

        // FECHA AUTOMÁTICA
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucion(fechaDevolucion);

        prestamo.setEstado(EstadoPrestamo.ACTIVO);

        // ASIGNAR CUPÓN SI EXISTE
        if(cuponId != null){

            Cupon cupon = cuponRepository.findById(cuponId)
                    .orElseThrow(() -> new RuntimeException("Cupón no encontrado"));

            if(Boolean.FALSE.equals(cupon.getActivo())){
                throw new RuntimeException("El cupón no está activo");
            }

            prestamo.setCupon(cupon);
        }

        // DESCONTAR LIBRO
        libro.setCantDisponible(libro.getCantDisponible() - 1);
        libroRepository.save(libro);

        return prestamoRepository.save(prestamo);
    }

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo buscarPrestamo(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prestamo no encontrado"));
    }

    // Método para obtener límite de préstamos
    private int obtenerMaxPrestamos(Usuario usuario) {

        if (usuario instanceof Estudiante estudiante) {
            return estudiante.getMaxPrestamos();
        }

        if (usuario instanceof Docente docente) {
            return docente.getMaxPrestamos();
        }

        return 3;
    }
}