package pe.com.mmh.sisgap.comun.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Transient;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pe.com.mmh.sisgap.administracion.ejb.DetallefacturaFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.FacturaFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.ItemcobranzaFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.SocioFacadeLocal;
import pe.com.mmh.sisgap.comun.constantes.Constantes;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.Detallefactura;
import pe.com.mmh.sisgap.domain.DetallefacturaPK;
import pe.com.mmh.sisgap.domain.Factura;
import pe.com.mmh.sisgap.domain.Itemcobranza;
import pe.com.mmh.sisgap.domain.Socio;

/**
 * Servlet implementation class AjaxServlet
 */
@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Context context = null;
		ServletOutputStream out = response.getOutputStream();
		HttpSession session = request.getSession(true);
		Set<Detallefactura> listDetallefactura =  null;
		
		try {
			
			context = new InitialContext();
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String action = request.getParameter("action");
		
		if (action != null) {
			if(action.equals("BUSCAR_SOCIO")){

				String nombre = request.getParameter("nombre");
				try {
					SocioFacadeLocal facadeLocal = (SocioFacadeLocal) context.lookup(ConstantesJNDI.SOCIOFACADE);
					List<Socio> lstSocio = facadeLocal.buscarxNombre(nombre);
					out.print("<table id='users' class='ui-widget ui-widget-content'   width='100%'>");
					out.print("<thead>");
					out.print("<tr class='ui-widget-header'>");
					out.print("	<th>Codigo</th>");
					out.print("	<th>Nombres</th>");
					out.print("	<th>Puesto</th>");
					out.print("	<th>Action</th>");
					out.print("</tr>");
					out.print("</thead>");
					out.print("<tbody>");
					for (Socio socio : lstSocio) {
						out.print("<tr>");
						out.print("<td>"+socio.getTranCodigo()+"</td>");
						out.print("<td>"+socio.getTranRazonSocial().trim()+"</td>");
						out.print("<td>"+socio.getTranPuesto()+"</td>");
						String function="agregarSocio('"+socio.getTranCodigo()+ "','" + socio.getTranRazonSocial()
								+ "'," + socio.getTranPuesto() +","+socio.getTranIde()+" )";
						out.print("<td><a href='#' onclick=\""+function+"\">Agregar</a></td>");
						out.print("</tr>");
					}
					out.print("</tbody>");
					out.print("</table>");
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(action.equals("BUSCAR_PRODUCTO")){

				String nombre = request.getParameter("nombre");
				try {
					ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) context.lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
					List<Itemcobranza> lstItemcobranza = facadeLocal.buscarxNombre(nombre);
					out.print("<table id='users' class='ui-widget ui-widget-content' width='100%'>");
					out.print("<thead>");
					out.print("<tr class='ui-widget-header'>");
					out.print("	<th>C&oacutedigo</th>");
					out.print("	<th>Descripci&oacuten</th>");
					out.print("	<th>Tipo de Cobranza</th>");
					out.print("	<th>Moneda</th>");
					out.print("	<th>Costo</th>");
					out.print("	<th>Action</th>");
					out.print("</tr>");
					out.print("</thead>");
					out.print("<tbody>");
					for (Itemcobranza item : lstItemcobranza) {
						out.print("<tr>");
						out.print("<td>"+item.getCodItemcobranza()+"</td>");
						out.print("<td>"+item.getStrDescripcion()+"</td>");
						out.print("<td>"+item.getTipocobdes()+"</td>");
						out.print("<td>"+item.getTipomondes()+"</td>");
						out.print("<td>"+item.getNumCosto()+"</td>");
						String functionAdd="agregarItem("+item.getCodItemcobranza()+ ",'" + item.getStrDescripcion()
								+ "','" + item.getStrTipocobranza() + "','" + item.getStrMoneda() + "',"+ item.getNumCosto() 
								+ ",'" + item.getTipocobdes() + "','" + item.getTipomondes() +"' )";
						out.print("<td><a href='#' onclick=\""+functionAdd+"\">Agregar</a></td>");
					}
					out.print("</tbody>");
					out.print("</table>");
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if(action.equals("AGREGAR_ITEM")){
			
				Detallefactura detallefactura = null;				
				
				listDetallefactura = (Set<Detallefactura>) session.getAttribute("listDetallefactura");
				
				String codigoItem = request.getParameter("codigo");
				String var2 = request.getParameter("descrip");
				String var3 = request.getParameter("codTipCob");
				String var4 = request.getParameter("codMon");
				String var5 = request.getParameter("costo");
				String var6 = request.getParameter("cantidad");		
				String var7 = request.getParameter("acuenta");
				String var8 = request.getParameter("codTipCob");
				String var9 = request.getParameter("codMon");
				String var10 = request.getParameter("total");
				
				try {
					
					//Agregar Detalle 
					
					detallefactura =  new Detallefactura();
					
					DetallefacturaPK pk=new DetallefacturaPK();
					
					pk.setCodItemcobranza(new Long(codigoItem));
					
					detallefactura.setId(pk);
					detallefactura.setStrDescripcion(var2);
					detallefactura.setStrTipocobranza(var3);
					detallefactura.setStrMoneda(var4);
					detallefactura.setNumCosto(new BigDecimal(var5));
					detallefactura.setNumCantidad(new BigDecimal(var6));
					detallefactura.setNumAcuenta(new BigDecimal(var7));										
					detallefactura.setStrTipocobranzaDescrip(var8);
					detallefactura.setStrMonedaDescrip(var9);
					detallefactura.setNumTotal(new BigDecimal(var10));
					
					validarProducto(listDetallefactura, detallefactura);
										
					session.setAttribute("listDetallefactura",listDetallefactura);
					
					out.print("<table id='detalle-f' class='ui-widget ui-widget-content' width='100%'>");
					out.print("<thead>");
					out.print("<tr class='ui-widget-header'>");
					out.print("	<th>Action</th>");
					out.print("	<th>C&oacutedigo</th>");
					out.print("	<th>Descripci&oacuten</th>");
					out.print("	<th>Tipo de Cobranza</th>");
					out.print("	<th>Moneda</th>");
					out.print("	<th>Costo</th>");
					out.print("	<th>Cantidad</th>");
					out.print("	<th>Total</th>");
					out.print("	<th>A Cuenta</th>");
					out.print("</tr>");
					out.print("</thead>");
					out.print("<tbody>");
					
					double total = 0;
					
					for (Detallefactura det : listDetallefactura) {
						out.print("<tr>");
						String function="eliminarDetalle("+det.getId().getCodItemcobranza() + ")";						
						out.print("<td>");
						out.print("<a href='#' onclick=\""+function+"\"><img src='"+request.getContextPath()+"/imagenes/manto/eliminar.png' alt='Eliminar...' border='0' width='16' height='16'/></a>");
						out.print("<a href='#' onclick=\""+function+"\"><img src='"+request.getContextPath()+"/imagenes/manto/editar.png' alt='Editar...' border='0' width='16' height='16'/></a>");
						out.print("</td>");
						out.print("<td>"+det.getId().getCodItemcobranza()+"</td>");
						out.print("<td>"+det.getStrDescripcion()+"</td>");
						out.print("<td>"+det.getStrTipocobranzaDescrip()+"</td>");
						out.print("<td>"+det.getStrMonedaDescrip()+"</td>");
						out.print("<td>"+det.getNumCosto()+"</td>");
						out.print("<td>"+det.getNumCantidad()+"</td>");
						out.print("<td>"+det.getNumTotal()+"</td>");
						out.print("<td>"+det.getNumAcuenta()+"</td>");
						out.print("</tr>");
						
						total += det.getNumTotal().doubleValue();
					}
					
					out.print("<tr>");
					out.print("<td></td>");
					out.print("<td></td>");
					out.print("<td></td>");
					out.print("<td></td>");
					out.print("<td></td>");
					out.print("<td></td>");
					out.print("<td>Total</td>");
					out.print("<td>"+total+"</td>");
					out.print("<td></td>");
					out.print("</tr>");
					
					out.print("</tbody>");
					out.print("</table>");
					out.print("<input type='hidden' name='txttotal' id='txttotal' value='"+total+"'/>");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}else if(action.equals("REGISTRAR_FACTURA")){
				
				Factura factura=null;
				Detallefactura detallefactura=null;
				BigDecimal total = new BigDecimal(0);
				
				try {
					listDetallefactura = (Set<Detallefactura>) session.getAttribute("listDetallefactura");
					FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal) context.lookup(ConstantesJNDI.FACTURAFACADE);
					
					factura = new Factura();
					
					factura.setNroFactura("");					
					factura.setNumEstado(new BigDecimal(1));
					factura.setStrTipodoc("");
					factura.setNumNrodoc(new BigDecimal(1));
					
					for (Detallefactura det : listDetallefactura) {
						total = det.getNumTotal();
					}
					
					factura.setNumTotal(total);
					
					factura.setSisgapDetallefacturas(listDetallefactura);
					
					
					facadeLocal.create(factura);
					out.print("1");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			
				
			}else if(action.equals("ELIMINAR_ITEM")){
				
				try {
					
					listDetallefactura = (Set<Detallefactura>) session.getAttribute("listDetallefactura");
					
					String codigoItem = request.getParameter("codigoItem");
					
					for (Detallefactura detallefactura : listDetallefactura) {
						
						if ( String.valueOf( detallefactura.getId().getCodItemcobranza()).equals(codigoItem) ) {
							
							listDetallefactura.remove(detallefactura);
							break;
						}
						
					}
					
					out.print("<table id='detalle-f' class='ui-widget ui-widget-content' width='100%'>");
					out.print("<thead>");
					out.print("<tr class='ui-widget-header'>");
					out.print("	<th>Action</th>");
					out.print("	<th>C&oacutedigo</th>");
					out.print("	<th>Descripci&oacuten</th>");
					out.print("	<th>Tipo de Cobranza</th>");
					out.print("	<th>Moneda</th>");
					out.print("	<th>Costo</th>");
					out.print("	<th>Cantidad</th>");
					out.print("	<th>Total</th>");
					out.print("	<th>A Cuenta</th>");
					out.print("</tr>");
					out.print("</thead>");
					out.print("<tbody>");
					
					double total = 0;
					
					for (Detallefactura det : listDetallefactura) {
						out.print("<tr>");
						String function="eliminarDetalle("+det.getId().getCodItemcobranza() + ")";						
						out.print("<td>");
						out.print("<a href='#' onclick=\""+function+"\"><img src='"+request.getContextPath()+"/imagenes/manto/eliminar.png' alt='Eliminar...' border='0' width='16' height='16'/></a>");
						out.print("<a href='#' onclick=\""+function+"\"><img src='"+request.getContextPath()+"/imagenes/manto/editar.png' alt='Editar...' border='0' width='16' height='16'/></a>");
						out.print("</td>");
						out.print("<td>"+det.getId().getCodItemcobranza()+"</td>");
						out.print("<td>"+det.getStrDescripcion()+"</td>");
						out.print("<td>"+det.getStrTipocobranzaDescrip()+"</td>");
						out.print("<td>"+det.getStrMonedaDescrip()+"</td>");
						out.print("<td>"+det.getNumCosto()+"</td>");
						out.print("<td>"+det.getNumCantidad()+"</td>");
						out.print("<td>"+det.getNumTotal()+"</td>");
						out.print("<td>"+det.getNumAcuenta()+"</td>");
						out.print("</tr>");
						
						total += det.getNumTotal().doubleValue();
					}
					
					out.print("<tr>");
					out.print("<td></td>");
					out.print("<td></td>");
					out.print("<td></td>");
					out.print("<td></td>");
					out.print("<td></td>");
					out.print("<td></td>");
					out.print("<td>Total</td>");
					out.print("<td>"+total+"</td>");
					out.print("<td></td>");
					out.print("</tr>");
					out.print("</tbody>");
					out.print("</table>");
					out.print("<input type='hidden' name='txttotal' id='txttotal' value='"+total+"'/>");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
	}
	
	public void validarProducto(Set<Detallefactura> listDetallefactura, Detallefactura det) {
		Integer nuevaCantidad = 0;
		Double nuevoTotal = 0.0;
		Boolean flag = false;
		try {
			
			for (Detallefactura detallefactura : listDetallefactura) {
				
				if (detallefactura.getId().getCodItemcobranza()==det.getId().getCodItemcobranza() ){
					nuevaCantidad = detallefactura.getNumCantidad().intValue() + det.getNumCantidad().intValue();
					nuevoTotal =  detallefactura.getNumCosto().doubleValue() * nuevaCantidad.doubleValue();
					detallefactura.setNumCantidad( new BigDecimal(nuevaCantidad) );
					detallefactura.setNumTotal(new BigDecimal(nuevoTotal));
					flag = true;
					break;
				}
				
			}
			
			if (!flag){
				
				listDetallefactura.add(det);
				
			}
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
}