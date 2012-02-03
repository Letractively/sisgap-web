/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.administracion.ejb;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;



import pe.com.mmh.sisgap.domain.Detallefactura;
import pe.com.mmh.sisgap.domain.Factura;

/**
 *
 * @author ANDREA
 */
@Local
public interface FacturaFacadeLocal {

    void create(Factura factura);

    void edit(Factura factura);

    void remove(Factura factura);

    Factura find(Object id);

    List<Factura> findAll();
    
    void grebarFactura(Long numerodocumento,String totalfac,	String codigoide, String cbtipodoc,Set<Detallefactura> detallefactura);

    BigDecimal generarNrodocumento(String tipodoc);

	void anularFactura(String codigoFactura, String descripanulada);
}