import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatriculaService {
    private static final Logger logger = LoggerFactory.getLogger(MatriculaService.class);

    public void registrarMatricula(MatriculaDTO dto) {
        logger.info("Registrando matrícula: {}", dto);
        try {
            // lógica
        } catch (Exception e) {
            logger.error("Error registrando matrícula", e);
            throw e;
        }
    }
}