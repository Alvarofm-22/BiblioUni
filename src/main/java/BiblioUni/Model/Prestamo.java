package BiblioUni.Model;

import BiblioUni.Enum.EstadoPrestamo;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaPrestamo;

    private LocalDate fechaDevolucion;

    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;

    private BigDecimal multa;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "libro_isbn")
    private Libro libro;

    // NUEVA RELACIÓN CON CUPÓN
    @ManyToOne
    @JoinColumn(name = "cupon_id")
    private Cupon cupon;
}