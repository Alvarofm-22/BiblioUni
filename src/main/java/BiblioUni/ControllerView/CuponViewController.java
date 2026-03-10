package BiblioUni.ControllerView;

import BiblioUni.Model.Cupon;
import BiblioUni.Service.CuponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cupones")
@RequiredArgsConstructor
public class CuponViewController {

    private final CuponService cuponService;

    @GetMapping
    public String listar(Model model){
        model.addAttribute("cupones", cuponService.listarTodo());
        return "Cupon/index";
    }

    @GetMapping("/registrar")
    public String registrar(Model model){
        model.addAttribute("cupon", new Cupon());
        return "Cupon/registrar";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cupon cupon){
        cuponService.insertar(cupon);
        return "redirect:/cupones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        cuponService.eliminar(id);
        return "redirect:/cupones";
    }

}