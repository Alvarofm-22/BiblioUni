package BiblioUni.Service;

import BiblioUni.Enum.EstadoPrestamo;
import BiblioUni.Model.Libro;
import BiblioUni.Model.Multa;
import BiblioUni.Model.Prestamo;
import BiblioUni.Repository.LibroRepository;
import BiblioUni.Repository.MultaRepository;
import BiblioUni.Repository.PrestamoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MultaServiceImpl implements MultaService {

    private final MultaRepository multaRepository;
    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;

    @Override
    public Multa calcularMulta(Prestamo prestamo) {

        // evitar multas duplicadas
        if(multaRepository.findByPrestamoId(prestamo.getId()).isPresent()){
            return null;
        }

        LocalDate hoy = LocalDate.now();

        if(hoy.isAfter(prestamo.getFechaDevolucion())){

            long dias = ChronoUnit.DAYS.between(
                    prestamo.getFechaDevolucion(),
                    hoy
            );

            double valor = dias * 500;

            Multa multa = Multa.builder()
                    .diasRetraso((int) dias)
                    .valor(valor)
                    .pagado(false)
                    .prestamo(prestamo)
                    .build();

            return multaRepository.save(multa);
        }

        return null;
    }

    @Transactional
    public void devolverLibro(Long prestamoId){

        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow();

        // calcular multa
        calcularMulta(prestamo);

        prestamo.setEstado(EstadoPrestamo.DEVUELTO);

        Libro libro = prestamo.getLibro();

        libro.setCantDisponible(libro.getCantDisponible()+1);

        libroRepository.save(libro);

        prestamoRepository.save(prestamo);
    }

    @Override
    public List<Multa> listar() {
        return multaRepository.findAll();
    }

    @Override
    public Multa buscarPorId(Long id) {
        return multaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Multa no encontrada"));
    }

    @Override
    public Multa actualizar(Multa multa) {
        return multaRepository.save(multa);
    }

    @Override
    public Multa guardar(Multa multa) {

        if (multa.getPrestamo() == null) {
            throw new RuntimeException("Debe seleccionar un préstamo");
        }

        // evitar multas duplicadas
        if (multaRepository.findByPrestamoId(multa.getPrestamo().getId()).isPresent()) {
            throw new RuntimeException("Ya existe una multa para este préstamo");
        }

        // calcular valor automáticamente
        int dias = multa.getDiasRetraso();
        double valor = dias * 500;

        multa.setValor(valor);
        multa.setPagado(false);

        return multaRepository.save(multa);
    }

}