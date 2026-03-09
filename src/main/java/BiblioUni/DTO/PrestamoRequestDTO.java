package BiblioUni.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrestamoRequestDTO {

    private Long usuarioId;

    private String isbn;

    private LocalDate fechaDevolucion;

}