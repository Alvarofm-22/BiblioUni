package BiblioUni.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "multa")
public class Multa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int diasRetraso;

    private double valor;

    private boolean pagado;

    @OneToOne
    @JoinColumn(name = "prestamo_id")
    private Prestamo prestamo;
}