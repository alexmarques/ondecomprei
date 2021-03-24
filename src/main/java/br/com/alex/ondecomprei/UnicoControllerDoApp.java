package br.com.alex.ondecomprei;

import br.com.alex.ondecomprei.models.Compra;
import br.com.alex.ondecomprei.models.Usuario;
import br.com.alex.ondecomprei.repo.CompraRepository;
import br.com.alex.ondecomprei.repo.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class UnicoControllerDoApp {

    private final UsuarioRepository usuarioRepository;
    private final CompraRepository compraRepository;

    public UnicoControllerDoApp(UsuarioRepository usuarioRepository, CompraRepository compraRepository) {
        this.usuarioRepository = usuarioRepository;
        this.compraRepository = compraRepository;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "index";
    }

    @RequestMapping("/registrar")
    public String registrar(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registrar";
    }

    @PostMapping("/registrar")
    public String confirmarRegistro(@ModelAttribute Usuario usuario) {
        this.usuarioRepository.save(usuario);
        return "redirect:dashboard";
    }

    @PostMapping("/login")
    public String login(Model model, Usuario usuario, HttpServletRequest request) {
        Optional<Usuario> user = this.usuarioRepository.findById(usuario.getEmail());
        if(user.get().getSenha().equals(usuario.getSenha())) {
            request.getSession().setAttribute("usuario", user.get());
            return "redirect:dashboard";
        }
        return index(model);
    }

    @RequestMapping("/dashboard")
    public String dashboard(Model model, HttpServletRequest request) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        List<Compra> compras = this.compraRepository.findAllByEmailUsuario(usuario.getEmail());
        model.addAttribute("compras", compras);
        return "dashboard";
    }

    @RequestMapping("/compras")
    public String compra(Model model) {
        model.addAttribute("compra", new Compra());
        return "compra/cadastrar";
    }

    @PostMapping("/compras")
    public String registrarCompra(@ModelAttribute Compra compra, HttpServletRequest request) {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        compra.setUsuario(usuario);
        this.compraRepository.save(compra);
        return "redirect:dashboard";
    }
}
