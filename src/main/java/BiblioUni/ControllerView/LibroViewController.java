package BiblioUni.ControllerView;

import BiblioUni.Model.Libro;
import BiblioUni.Service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libros")
@RequiredArgsConstructor
public class LibroViewController {

    private final LibroService libroService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("libros", libroService.listarTodo());
        return "Libros/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("libro", new Libro());
        return "Libros/registrar";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Libro libro) {

        if(libroService.buscarPorId(libro.getIsbn()) != null){
            libroService.actualizar(libro);
        }else{
            libroService.insertar(libro);
        }

        return "redirect:/libros";
    }

    @GetMapping("/editar/{isbn}")
    public String editar(@PathVariable String isbn, Model model) {

        model.addAttribute("libro", libroService.buscarPorId(isbn));

        return "Libros/registrar";
    }

    @GetMapping("/eliminar/{isbn}")
    public String eliminar(@PathVariable String isbn) {

        libroService.eliminar(isbn);

        return "redirect:/libros";
    }
}