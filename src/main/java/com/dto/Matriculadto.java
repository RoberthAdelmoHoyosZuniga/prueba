

public class MatriculaDTO {
	private Integer idMatricula;
	private int idAlumno;
	private Date fechaMatr;
	private String periodo;
	private String grado;
	private String seccion;
	private double costoTotal;
	private String estado;
	private List<DetalleMatriculaModel> detallefactura = new ArrayList<>();
}	