xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://tempuri.org/";
(:: import schema at "../../WSDL/OnBase.wsdl" ::)
declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $CargarDocumentoRequest as element() (:: schema-element(ns1:CargarDocumentoOnBaseRequest) ::) external;

declare function local:func($CargarDocumentoRequest as element() (:: schema-element(ns1:CargarDocumentoOnBaseRequest) ::)) as element() (:: schema-element(ns2:Insert) ::) {
    <ns2:Insert>
        {
            if ($CargarDocumentoRequest/ns1:Cliente/cli:pais/cat:DescripcionCorta)
            then <ns2:pais>{fn:upper-case(fn:data($CargarDocumentoRequest/ns1:Cliente/cli:pais/cat:DescripcionCorta))}</ns2:pais>
            else ()
        }
        {
            if ($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:mime_type)
            then <ns2:tipo_archivo>{fn:data($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:mime_type)}</ns2:tipo_archivo>
            else ( <ns2:tipo_archivo></ns2:tipo_archivo>)
        }
        {
            if ($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:idTipoDocumentoExterno)
            then <ns2:documentTypeID>{fn:data($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:idTipoDocumentoExterno)}</ns2:documentTypeID>
            else ()
        }
        {
        if(string ($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:idOperacion)!='') then 
        
        
        <ns2:keys>
            <ns2:Key>
                <ns2:name>Codigo  Intervencion</ns2:name>
                <ns2:value>{fn:data($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:idOperacion)}</ns2:value>
            </ns2:Key>
             <ns2:Key>
                <ns2:name>Codigo Cliente</ns2:name>
                <ns2:value>{fn:data($CargarDocumentoRequest/ns1:Cliente/cli:idFacturador)}</ns2:value>
            </ns2:Key>
             <ns2:Key>
                <ns2:name>Codigo Proyecto</ns2:name>
                <ns2:value>{fn:data($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:idOperacion)}</ns2:value>
            </ns2:Key>
             <ns2:Key>
                <ns2:name>Estado</ns2:name>
                <ns2:value>{fn:data($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:tarea)}</ns2:value>
            </ns2:Key>
             <ns2:Key>
                <ns2:name>Linea de Credito</ns2:name>
                <ns2:value xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:nil="true"/>
            </ns2:Key>
             <ns2:Key>
                <ns2:name>Nombre Cliente</ns2:name>
               <ns2:value>{fn:data($CargarDocumentoRequest/ns1:Cliente/cli:razonSocial)}</ns2:value>
            </ns2:Key>
             <ns2:Key>
                <ns2:name>Nombre Proyecto</ns2:name>
                <ns2:value>{substring(fn:data($CargarDocumentoRequest/ns1:Operacion/ope:nombre),1, 250)}</ns2:value>
            </ns2:Key>
            <ns2:Key>
                <ns2:name>Numero Documento</ns2:name>
                <ns2:value>{fn:data($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:codigoDocumento)}</ns2:value>
            </ns2:Key>
            <ns2:Key>
                <ns2:name>Pais</ns2:name>
                <ns2:value>{fn:upper-case(fn:data($CargarDocumentoRequest/ns1:Cliente/cli:pais/cat:DescripcionCorta))}</ns2:value>
            </ns2:Key>
            <ns2:Key>
                <ns2:name>Periodo</ns2:name>
                <ns2:value>{fn:concat(local:formatMes(string(fn:day-from-dateTime($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:fechaRegistro))),"/",local:formatMes(string(fn:month-from-dateTime($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:fechaRegistro))),"/",fn:year-from-dateTime($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:fechaRegistro))}</ns2:value>
            </ns2:Key>
            <ns2:Key>
                <ns2:name>Resumen</ns2:name>
                <ns2:value>{substring (fn:data($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:comentario), 1, 250)}</ns2:value>
            </ns2:Key>
               <ns2:Key>
                <ns2:name>epdocid</ns2:name>
                <ns2:value>{fn:data($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:idAdjunto)}</ns2:value>
            </ns2:Key>
        </ns2:keys>
        
        else 
        
         
        <ns2:keys>
             <ns2:Key>
                <ns2:name>Codigo Cliente</ns2:name>
                <ns2:value>{fn:data($CargarDocumentoRequest/ns1:Cliente/cli:idFacturador)}</ns2:value>
            </ns2:Key>
            <ns2:Key>
                <ns2:name>Nombre Cliente</ns2:name>
               <ns2:value>{fn:data($CargarDocumentoRequest/ns1:Cliente/cli:razonSocial)}</ns2:value>
            </ns2:Key>
             <ns2:Key>
                <ns2:name>Tipo Documento</ns2:name>
                <ns2:value>{fn:data($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:nombreTipoDocumento)}</ns2:value>
            </ns2:Key>
            <ns2:Key>
                <ns2:name>Numero Documento</ns2:name>
                <ns2:value>{fn:data($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:codigoDocumento)}</ns2:value>
            </ns2:Key>
            <ns2:Key>
                <ns2:name>Pais</ns2:name>
                <ns2:value>{fn:upper-case(fn:data($CargarDocumentoRequest/ns1:Cliente/cli:pais/cat:DescripcionCorta))}</ns2:value>
            </ns2:Key>
            <ns2:Key>
                <ns2:name>Periodo</ns2:name>
                <ns2:value>{fn:concat(local:formatMes(string(fn:day-from-dateTime($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:fechaRegistro))),"/",local:formatMes(string(fn:month-from-dateTime($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:fechaRegistro))),"/",fn:year-from-dateTime($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:fechaRegistro))}</ns2:value>
            </ns2:Key>
            <ns2:Key>
                <ns2:name>Resumen</ns2:name>
                <ns2:value>{substring (fn:data($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:comentario), 1, 250)}</ns2:value>
            </ns2:Key>
               <ns2:Key>
                <ns2:name>epdocid</ns2:name>
                <ns2:value>{fn:data($CargarDocumentoRequest/ns1:Documentos/doc:Documento/doc:idAdjunto)}</ns2:value>
            </ns2:Key>
        </ns2:keys>
        
        
        }
           <ns2:imagen/>
    </ns2:Insert>
};


declare function local:formatMes ($mes as xs:string) as xs:string {
  
  if (fn:string-length($mes)= 1) then 
  concat(0,$mes)
  else
  $mes
};
local:func($CargarDocumentoRequest)