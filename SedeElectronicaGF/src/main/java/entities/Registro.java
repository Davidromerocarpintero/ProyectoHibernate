package entities;

public class Registro implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idRegistro; 
    private String dniSolicitante;
    private String nombreSolicitante;
    private String apellidosSolicitante;
    private String tramite;
    private String entidad;

    public Registro() { }

    public Registro(String idRegistro, String dniSolicitante, String nombreSolicitante, String apellidosSolicitante,
                    String tramite, String entidad) {
        this.idRegistro = idRegistro;
        this.dniSolicitante = dniSolicitante;
        this.nombreSolicitante = nombreSolicitante;
        this.apellidosSolicitante = apellidosSolicitante;
        this.tramite = tramite;
        this.entidad = entidad;
    }

    public String getIdRegistro() { return idRegistro; }
    public void setIdRegistro(String idRegistro) { this.idRegistro = idRegistro; }

    public String getDniSolicitante() { return dniSolicitante; }
    public void setDniSolicitante(String dniSolicitante) { this.dniSolicitante = dniSolicitante; }

    public String getNombreSolicitante() { return nombreSolicitante; }
    public void setNombreSolicitante(String nombreSolicitante) { this.nombreSolicitante = nombreSolicitante; }

    public String getApellidosSolicitante() { return apellidosSolicitante; }
    public void setApellidosSolicitante(String apellidosSolicitante) { this.apellidosSolicitante = apellidosSolicitante; }

    public String getTramite() { return tramite; }
    public void setTramite(String tramite) { this.tramite = tramite; }

    public String getEntidad() { return entidad; }
    public void setEntidad(String entidad) { this.entidad = entidad; }
}
