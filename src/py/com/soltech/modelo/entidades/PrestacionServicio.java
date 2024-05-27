package py.com.soltech.modelo.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prestacion_servicio", catalog = "soltech")
public class PrestacionServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;
    @Basic(optional = false)
    @Column(name = "fecha_finalizacion", nullable = false)
    private LocalDate fechaFinalizacion;
    @Basic(optional = false)
    @Column(name = "costo_total", nullable = false)
    private Double costoTotal;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String estado;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prestacionServicio", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ServicioDetalle> servicioDetalleList;
    
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Funcionario funcionario;
    
    @JoinColumn(name = "orden_servicio_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private OrdenServicio ordenServicio;

    public PrestacionServicio() {
    }

    public PrestacionServicio(Integer id) {
        this.id = id;
    }

    public PrestacionServicio(Integer id, LocalDate fechaInicio, LocalDate fechaFinalizacion, Double costoTotal, String estado) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.costoTotal = costoTotal;
        this.estado = estado;
    }

    public PrestacionServicio(int id, int ordenServicioId, int funcionarioId) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ServicioDetalle> getServicioDetalleList() {
        return servicioDetalleList;
    }

    public void setServicioDetalleList(List<ServicioDetalle> servicioDetalleList) {
        this.servicioDetalleList = servicioDetalleList;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public OrdenServicio getOrdenServicio() {
        return ordenServicio;
    }

    public void setOrdenServicio(OrdenServicio ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

    @Override
    public String toString() {
        return "PrestacionServicio{" + "id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFinalizacion=" + fechaFinalizacion + ", costoTotal=" + costoTotal + ", estado=" + estado + ", servicioDetalleList=" + servicioDetalleList + ", funcionario=" + funcionario + ", ordenServicio=" + ordenServicio + '}';
    }

    
    
}
