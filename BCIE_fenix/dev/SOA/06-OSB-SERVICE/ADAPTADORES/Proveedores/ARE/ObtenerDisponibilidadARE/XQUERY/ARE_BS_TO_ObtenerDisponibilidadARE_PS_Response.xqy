xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)
declare namespace m="http://org/bcie/ws/middle/ARE/ARE.wsdl";
declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ObtenerDisponibilidadARERequest as element() (:: schema-element(ns1:ObtenerDisponibilidadARERequest) ::) external;
declare variable $obtenerDisponibilidadResponse as element(m:obtenerDisponibilidadResponse) external;

declare function local:func($ObtenerDisponibilidadARERequest as element() (:: schema-element(ns1:ObtenerDisponibilidadARERequest) ::),$obtenerDisponibilidadResponse as element(m:obtenerDisponibilidadResponse)) as element() (:: schema-element(ns1:ObtenerDisponibilidadAREResponse) ::) {
    <ns1:ObtenerDisponibilidadAREResponse>
        <ns1:Fuente>
            <lin:idFuente>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:idFuente)}</lin:idFuente>
            <lin:idLineaCredito>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:idLineaCredito)}</lin:idLineaCredito>
            <lin:idLineaPasiva>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:idLineaPasiva)}</lin:idLineaPasiva>
            <lin:codigoLineaPasiva>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:codigoLineaPasiva)}</lin:codigoLineaPasiva>
            <lin:Descripcion>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:Descripcion)}</lin:Descripcion>
            <lin:FechaObtenido>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:FechaObtenido)}</lin:FechaObtenido>
            <lin:MontoAsignado>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:MontoAsignado)}</lin:MontoAsignado>
            <lin:montoDisponible>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:montoDisponible)}</lin:montoDisponible>
            <lin:Monto>
                <com:tipo>
                   <cat:Id>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:Monto/com:tipo/cat:Id)}</cat:Id>
                   <cat:Descripcion>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                    <cat:DescripcionCorta>DISPONIBLE</cat:DescripcionCorta>
                    <cat:estatus>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:Monto/com:tipo/cat:estatus)}</cat:estatus>
                    <cat:codigoExterno>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                </com:tipo>
                <com:importe>{fn:data($obtenerDisponibilidadResponse/disponible_out)}</com:importe>
                 
                <com:moneda>
                    <cat:Id>1</cat:Id>
                    <cat:Descripcion>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    <cat:estatus>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:Monto/com:moneda/cat:estatus)}</cat:estatus>
                    <cat:codigoExterno>USD</cat:codigoExterno>     
                </com:moneda>
            </lin:Monto>
           
            <lin:NumeroAsignacion>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:NumeroAsignacion)}</lin:NumeroAsignacion>
            <lin:Comentario>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:Comentario)}</lin:Comentario>
            <lin:idContrato>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:idContrato)}</lin:idContrato>
            <lin:FechaRegistro>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:FechaRegistro)}</lin:FechaRegistro>
            <lin:Estado>{fn:data($ObtenerDisponibilidadARERequest/ns1:Fuente/lin:Estado)}</lin:Estado>
        </ns1:Fuente>
        <ns1:Resultado>
            {
                if (fn:data($obtenerDisponibilidadResponse/tiporesultado_out)= 0) then
            <res:result>OK</res:result>
             else  <res:result>ERROR</res:result>
        }
         {
                if (fn:data($obtenerDisponibilidadResponse/tiporesultado_out)= 0) then
            <res:message>Operación realizada correctamente</res:message>
             else  <res:message>{fn:data($obtenerDisponibilidadResponse/mensajeerror_out)}</res:message>
        }
        </ns1:Resultado>
    </ns1:ObtenerDisponibilidadAREResponse>
};

local:func($ObtenerDisponibilidadARERequest,$obtenerDisponibilidadResponse)
