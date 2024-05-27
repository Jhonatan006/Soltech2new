package py.com.soltech.modelo.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orden_servicio", catalog = "soltech")
public class OrdenServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false)
    private LocalDate fecha;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String estado;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String color;
    @Basic(optional = false)
    @Column(nullable = false,unique = true, length = 100)
    private String serial;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String password;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String descripcion;
    
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;
    
    @JoinColumn(name = "equipo_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Equipo equipo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenServicio")
    private List<PrestacionServicio> prestacionServicioList;

    public OrdenServicio() {
    }

    public OrdenServicio(Integer id) {
        this.id = id;
    }

    public OrdenServicio(Integer id, LocalDate fecha, String estado, String color, String serial, String password, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.color = color;
        this.serial = serial;
        this.password = password;
        this.descripcion = descripcion;
    }

    public OrdenServicio(int id, int clienteId, int equipoId) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public List<PrestacionServicio> getPrestacionServicioList() {
        return prestacionServicioList;
    }

    public void setPrestacionServicioList(List<PrestacionServicio> prestacionServicioList) {
        this.prestacionServicioList = prestacionServicioList;
    }

    @Override
    public String toString() {
        return "OrdenServicio{" + "id=" + id + ", fecha=" + fecha + ", estado=" + estado + ", color=" + color + ", serial=" + serial + ", password=" + password + ", descripcion=" + descripcion + ", cliente=" + cliente + ", equipo=" + equipo + ", prestacionServicioList=" + prestacionServicioList + '}';
    }

    
    
}
