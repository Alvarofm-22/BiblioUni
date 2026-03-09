package BiblioUni.Controller;

import BiblioUni.DTO.PrestamoRequestDTO;
import BiblioUni.Model.Prestamo;
import BiblioUni.Service.PrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public Prestamo registrarPrestamo(@RequestBody PrestamoRequestDTO request) {

        return prestamoService.registrarPrestamo(
                request.getUsuarioId(),
                request.getIsbn(),
                request.getFechaDevolucion()
        );
    }

    @GetMapping
    public Collection<Prestamo> listarPrestamos() {
        return prestamoService.listarPrestamos();
    }

    @GetMapping("/{id}")
    public Prestamo buscarPrestamo(@PathVariable Long id) {
        return prestamoService.buscarPrestamo(id);
    }
}