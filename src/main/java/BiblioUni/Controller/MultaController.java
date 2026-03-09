package BiblioUni.Controller;

import BiblioUni.Model.Multa;
import BiblioUni.Service.MultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/multas")
@RequiredArgsConstructor
public class MultaController {

    private final MultaService multaService;

    @GetMapping
    public List<Multa> listar(){
        return multaService.listar();
    }

    @PostMapping
    public Multa guardar(@RequestBody Multa multa){
        return multaService.guardar(multa);
    }
}