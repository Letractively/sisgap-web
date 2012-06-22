package pe.com.mmh.sisgap.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SisgapServiciodetalle generated by hbm2java
 */
@Entity
@Table(name="SISGAP_SERVICIODETALLE"
    ,schema="SISGAP"
)
public class ServicioDetalle  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="COD_SERVICIODETALLE", unique=true, nullable=false, precision=22, scale=0)
     private BigDecimal codServiciodetalle;
     @Column(name="COD_SERVICIOITEM", precision=22, scale=0)
     private BigDecimal codServicioitem;
    @Column(name="COD_SERVICIO", precision=22, scale=0)
     private BigDecimal codServicio;
    @Column(name="NUM_COSTO", precision=11)
     private BigDecimal numCosto;
    @Column(name="NUM_CANTIDAD", precision=11)
     private BigDecimal numCantidad;
    @Column(name="NUM_ESTADO", precision=1, scale=0)
     private Boolean numEstado;
    @Column(name="STR_MONEDA", length=20)
     private String strMoneda;

    public ServicioDetalle() {
    }

	
    public ServicioDetalle(BigDecimal codServiciodetalle) {
        this.codServiciodetalle = codServiciodetalle;
    }
    public ServicioDetalle(BigDecimal codServiciodetalle, BigDecimal codServicioitem, BigDecimal codServicio, BigDecimal numCosto, BigDecimal numCantidad, Boolean numEstado, String strMoneda) {
       this.codServiciodetalle = codServiciodetalle;
       this.codServicioitem = codServicioitem;
       this.codServicio = codServicio;
       this.numCosto = numCosto;
       this.numCantidad = numCantidad;
       this.numEstado = numEstado;
       this.strMoneda = strMoneda;
    }
   
         public BigDecimal getCodServiciodetalle() {
        return this.codServiciodetalle;
    }
    
    public void setCodServiciodetalle(BigDecimal codServiciodetalle) {
        this.codServiciodetalle = codServiciodetalle;
    }
    
    
    public BigDecimal getCodServicioitem() {
        return this.codServicioitem;
    }
    
    public void setCodServicioitem(BigDecimal codServicioitem) {
        this.codServicioitem = codServicioitem;
    }
    
        public BigDecimal getCodServicio() {
        return this.codServicio;
    }
    
    public void setCodServicio(BigDecimal codServicio) {
        this.codServicio = codServicio;
    }
    
        public BigDecimal getNumCosto() {
        return this.numCosto;
    }
    
    public void setNumCosto(BigDecimal numCosto) {
        this.numCosto = numCosto;
    }
    
        public BigDecimal getNumCantidad() {
        return this.numCantidad;
    }
    
    public void setNumCantidad(BigDecimal numCantidad) {
        this.numCantidad = numCantidad;
    }
    
        public Boolean getNumEstado() {
        return this.numEstado;
    }
    
    public void setNumEstado(Boolean numEstado) {
        this.numEstado = numEstado;
    }
    
        public String getStrMoneda() {
        return this.strMoneda;
    }
    
    public void setStrMoneda(String strMoneda) {
        this.strMoneda = strMoneda;
    }




}