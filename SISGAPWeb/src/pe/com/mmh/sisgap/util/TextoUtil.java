package pe.com.mmh.sisgap.util;

import java.util.StringTokenizer;


/** @modelguid {79E76AB7-251B-42D5-A1B9-7C817D0A8D55} */
public class TextoUtil {
	
	
	/**
	 * Metodo retorna un string en MAYUSCULA con el simbolo de "%" al final
	 * @modelguid {FF16AF65-402D-46DB-8D29-0FC51D7E29FC}
	 */
	public static String upperLike(String str) throws Exception {
		
		str = str.trim();
		
		StringBuffer strBuffer = new StringBuffer();		
		strBuffer.append(str.toUpperCase());
		strBuffer.append("%");

		return strBuffer.toString();
		
	}
	
	
	/**Luis Medina 07/08/2006
	 * Metodo retorna un string en MAYUSCULA con el simbolo de "%" al inicio y al final de la palabra a buscar
	 * @modelguid {FF16AF65-402D-46DB-8D29-0FC51D7E29FC}
	 */
	public static String upperLikeAll(String str) throws Exception {
		
		str = str.trim();		
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("%");		
		strBuffer.append(str.toUpperCase());
		strBuffer.append("%");
		
		return strBuffer.toString();
		
	}
	
	
	/**
	 * Este metodo retorna un codigo completado con ceros a la izquierda
	 * @param id
	 * @param tamanoCodigo
	 * @return
	 */
	public static String codifica(Long id, int tamanoCodigo) {
		String codigo = "";
		int tamanoId;
		
		if(id != null) {
			codigo = String.valueOf(id.longValue());
			tamanoId = codigo.length();
			for(int i = 0; i < (tamanoCodigo - tamanoId); i++) {
				codigo = "0" + codigo;
			}
		}
		return codigo;
	}
	
	/**
	 * Este metodo retorna la ruta de la imagen necesaria para el reporte, agregandole el sufijo especificado en las constantes
	 * a la ruta definida para cada empresa.
	 * @param id
	 * @param tamanoCodigo
	 * @return
	 */
	public static String obtenerRutaImagenReporte(String sufijo,String rutaImagen) {
		String rutaReporte = "";
		int indice = 0;
		
		if(rutaImagen != null) {
			indice = rutaImagen.lastIndexOf(".");
			rutaReporte = rutaImagen.substring(0,indice);
			rutaReporte += sufijo + rutaImagen.substring(indice);
		}
		return rutaReporte;
	}
	
	public static String elminarTildes(String cadena){
		
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","a");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","e");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","i");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","o");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","u");
	
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","A");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","E");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","I");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","O");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","U");
	
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","n");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","N");
	
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","A");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","a");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","a");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","E");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","e");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","U");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","u");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","C");
		  cadena=TextoUtil.reemplazarCaracteres(cadena,"�","c");
	
		return cadena;
	}
	
	public static String reemplazarCaracteres(String strCadenaOrigen, String strCadReemplazar, String strCadReemplazo) {
            String strCadenaFinal = "";
			int intInicio = 0;
			if(strCadenaOrigen != null && strCadReemplazar != null && 
			strCadReemplazo != null)
					{
						int intFin = intInicio;
						for(; strCadenaOrigen.indexOf(strCadReemplazar, intInicio) != -1; intInicio = intFin + strCadReemplazar.length())
						{
							intFin = strCadenaOrigen.indexOf(strCadReemplazar, intInicio);
							strCadenaFinal = strCadenaFinal + strCadenaOrigen.substring(intInicio, intFin);
							strCadenaFinal = strCadenaFinal + strCadReemplazo;
						}
		
						if(intInicio <= strCadenaOrigen.length() - 1)
							strCadenaFinal = strCadenaFinal + strCadenaOrigen.substring(intInicio, strCadenaOrigen.length());
					}
					return strCadenaFinal;
		    }
		    

	public static String ASCII2JAVA (String str)
	{
	  StringBuffer retval = new StringBuffer ();
	  char ch;
	  for (int i = 0; i < str.length (); i++) {
		switch (str.charAt (i)) {
	  case 0 :
		continue;
	  case '\b':
		retval.append ("\\b");
		continue;
	  case '\t':
		retval.append ("\\t");
		continue;
	  case '\n':
		retval.append ("\\n");
		continue;
	  case '\f':
		retval.append ("\\f");
		continue;
	  case '\r':
		retval.append ("\\r");
		continue;
	  default:
		if ((ch = str.charAt (i)) < 0x20 || ch > 0x7e) {
		  String s = "0000" + Integer.toString (ch, 16);
		  retval.append ("\\u" + s.substring (s.length () - 4, s.length ()));
		}
		else {
		  retval.append (ch);
		}
		continue;
		  }
	  }
	  return retval.toString ();
	}
	
	/**
	 * LMedina
	 * Elimina caracters duplicados seguidos, inclusive al inicio y al final de la cadena
	 * Ejm:    ,,,hola,,,hola,,    -->  hola,hola
	 * @param cadena
	 * @param caracter
	 * @return
	 */
	
	public static String eliminaCaracteresDuplicadosSeguidos(String cadena,String separador){	
		if(cadena==null) return "";
            	
		 StringTokenizer tokens=new StringTokenizer(cadena, separador);
		 int cant=tokens.countTokens();	  
		 StringBuffer cad=new StringBuffer("");	   
		 while(tokens.hasMoreTokens()){
			 String st=tokens.nextToken();		   		   
			 cad.append(st).append(separador);		   
		 } 	
	   	  	   
		 if(cant>0)
			return cad.substring(0,cad.length()-1);
	          	  
		 return cad.toString();	
	}   
	
	
	public static String formatoCampo(String cadena, char caracter, int tamCampo){
		StringBuffer sb=new StringBuffer(cadena);
		if(cadena!=null && cadena.length()<=tamCampo){
			for(int i=cadena.length();i<tamCampo;i++){
				  sb.append(caracter);			
			}		
		}
		return sb.toString();
	}
	
	public static void main(String []args){
		char espacio =' ';
		System.out.println(TextoUtil.formatoCampo("1570", espacio, 5));
		
	}
	


}

