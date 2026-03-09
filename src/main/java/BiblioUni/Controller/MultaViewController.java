package BiblioUni.Controller;

import BiblioUni.Model.Multa;
import BiblioUni.Service.MultaService;
import BiblioUni.Service.PrestamoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/multas")
@RequiredArgsConstructor
public class MultaViewController {

    private final MultaService multaService;
    private final PrestamoService prestamoService;

    @GetMapping
    public String listar(Model model){
        model.addAttribute("multas", multaService.listar());
        return "Multas/index";
    }

    @GetMapping("/registrar")
    public String registrar(Model model){
        model.addAttribute("multa", new Multa());
        model.addAttribute("prestamos", prestamoService.listarPrestamos());
        return "Multas/registrar";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Multa multa){

        try{
            multaService.guardar(multa); // el service calcula el valor
        }catch(RuntimeException e){
            return "redirect:/multas/registrar?error="+e.getMessage();
        }

        return "redirect:/multas";
    }
}