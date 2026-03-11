package BiblioUni.ControllerView;

import BiblioUni.Service.CuponService;
import BiblioUni.Service.LibroService;
import BiblioUni.Service.PrestamoService;
import BiblioUni.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/prestamos")
@RequiredArgsConstructor
public class PrestamoViewController {

    private final PrestamoService prestamoService;
    private final LibroService libroService;
    private final UsuarioService usuarioService;
    private final CuponService cuponService; // NUEVO

    @GetMapping
    public String listar(Model model){
        model.addAttribute("prestamos", prestamoService.listarPrestamos());
        return "Prestamos/index";
    }

    @GetMapping("/registrar")
    public String formulario(Model model){

        model.addAttribute("fechaHoy", LocalDate.now());
        model.addAttribute("libros", libroService.listarTodo());
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("cupones", cuponService.listarTodo()); // NUEVO

        return "Prestamos/registrar";
    }

    @PostMapping("/registrar")
    public String registrar(
            @RequestParam Long usuarioId,
            @RequestParam String isbn,
            @RequestParam LocalDate fechaDevolucion,
            @RequestParam(required = false) Long cuponId, // NUEVO
            Model model
    ){

        try {

            prestamoService.registrarPrestamo(usuarioId,isbn,fechaDevolucion,cuponId);

            model.addAttribute("mensaje","Préstamo registrado correctamente");

        }catch (Exception e){

            model.addAttribute("error", e.getMessage());
        }

        model.addAttribute("libros", libroService.listarTodo());
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("cupones", cuponService.listarTodo()); // NUEVO

        return "Prestamos/prestamos";
    }
}