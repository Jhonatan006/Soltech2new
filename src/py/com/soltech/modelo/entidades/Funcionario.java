package py.com.soltech.modelo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(catalog = "soltech")
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String apellido;
    @Basic(optional = false)
    @Column(nullable = false,unique = true, length = 45)
    private String documento;
    @Basic(optional = false)
    @Column(nullable = false, length = 255)
    private String email;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String telefono;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String cargo;
    @Basic(optional = false)
    @Column(nullable = false)
    private Boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionario")
    private List<PrestacionServicio> prestacionServicioList;

    public Funcionario() {
    }

    public Funcionario(Integer id) {
        this.id = id;
    }

    public Funcionario(Integer id, String nombre, String apellido, String documento, String email, String telefono, String cargo, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.email = email;
        this.telefono = telefono;
        this.cargo = cargo;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<PrestacionServicio> getPrestacionServicioList() {
        return prestacionServicioList;
    }

    public void setPrestacionServicioList(List<PrestacionServicio> prestacionServicioList) {
        this.prestacionServicioList = prestacionServicioList;
    }

    @Override
    public String toString() {
        return "soltech.Funcionario[ id=" + id + " ]";
    }
    
}
