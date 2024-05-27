package py.com.soltech.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "servicio_detalle", catalog = "soltech")
public class ServicioDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String descripcion;
    @Basic(optional = false)
    @Column(nullable = false)
    private double costo;
    @JoinColumn(name = "prestacion_servicio_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PrestacionServicio prestacionServicio;

    public ServicioDetalle() {
    }

    public ServicioDetalle(Integer id) {
        this.id = id;
    }

    public ServicioDetalle(Integer id, String descripcion, double costo) {
        this.id = id;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    public ServicioDetalle(int id, int prestacionServicioId) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public PrestacionServicio getPrestacionServicio() {
        return prestacionServicio;
    }

    public void setPrestacionServicio(PrestacionServicio prestacionServicio) {
        this.prestacionServicio = prestacionServicio;
    }

    @Override
    public String toString() {
        return "ServicioDetalle{" + "id=" + id + ", descripcion=" + descripcion + ", costo=" + costo + ", prestacionServicio=" + prestacionServicio + '}';
    }
    
}
