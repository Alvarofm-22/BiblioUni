package BiblioUni.ControllerView;

import BiblioUni.Model.Usuario;
import BiblioUni.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioViewController {

    private final UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model){

        model.addAttribute("usuarios", usuarioService.listar());

        return "Usuario/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){

        model.addAttribute("usuario", new Usuario());

        return "Usuario/registrar";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario){

        if(usuario.getId() == null){
            usuarioService.registrar(usuario);
        }else{
            usuarioService.actualizar(usuario);
        }

        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model){

        model.addAttribute("usuario", usuarioService.buscarPorId(id));

        return "Usuario/registrar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){

        usuarioService.eliminar(id);

        return "redirect:/usuarios";
    }
}