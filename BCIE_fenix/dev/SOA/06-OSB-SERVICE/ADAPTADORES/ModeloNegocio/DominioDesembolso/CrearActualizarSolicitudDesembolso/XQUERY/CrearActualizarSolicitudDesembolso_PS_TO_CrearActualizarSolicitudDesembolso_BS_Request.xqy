xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarSolicitudDesembolso_DB";
(:: import schema at "../XSD/CrearActualizarSolicitudDesembolso_DB_table.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $CrearActualizarSolicitudDesembolsoRequest as element() (:: schema-element(ns1:CrearActualizarSolicitudDesembolsoRequest) ::) external;

declare function local:func($CrearActualizarSolicitudDesembolsoRequest as element() (:: schema-element(ns1:CrearActualizarSolicitudDesembolsoRequest) ::)) as element() (:: schema-element(ns2:SolicitudDesembolsoCollection) ::) {
    <ns2:SolicitudDesembolsoCollection>
        <ns2:SolicitudDesembolso>
            <ns2:id>{fn:data($CrearActualizarSolicitudDesembolsoRequest/ns1:SolicitudDesembolso/des:idDesembolso)}</ns2:id>
            <ns2:idOperacion>{fn:data($CrearActualizarSolicitudDesembolsoRequest/ns1:IdOperacion)}</ns2:idOperacion>
            <ns2:fechaCreacion>{fn:data($CrearActualizarSolicitudDesembolsoRequest/ns1:SolicitudDesembolso/des:fechaCreacion)}</ns2:fechaCreacion>
            <ns2:fechaSolicitud>{fn:data($CrearActualizarSolicitudDesembolsoRequest/ns1:SolicitudDesembolso/des:fechaSolicitud)}</ns2:fechaSolicitud>
            {
                if ($CrearActualizarSolicitudDesembolsoRequest/ns1:SolicitudDesembolso/des:monto/com:importe)
                then <ns2:montoSolicitud>{fn:data($CrearActualizarSolicitudDesembolsoRequest/ns1:SolicitudDesembolso/des:monto/com:importe)}</ns2:montoSolicitud>
                else ()
            }
            {
                if ($CrearActualizarSolicitudDesembolsoRequest/ns1:SolicitudDesembolso/des:tipoMoneda/cat:Id)
                then <ns2:idTcaTipoMoneda>{fn:data($CrearActualizarSolicitudDesembolsoRequest/ns1:SolicitudDesembolso/des:tipoMoneda/cat:Id)}</ns2:idTcaTipoMoneda>
                else ()
            }
            {
                if ($CrearActualizarSolicitudDesembolsoRequest/ns1:SolicitudDesembolso/des:estado/cat:Id)
                then <ns2:idTcaEstado>{fn:data($CrearActualizarSolicitudDesembolsoRequest/ns1:SolicitudDesembolso/des:estado/cat:Id)}</ns2:idTcaEstado>
                else ()
            }
            {
            if ($CrearActualizarSolicitudDesembolsoRequest/ns1:SolicitudDesembolso/des:estatus)then
            <ns2:banEstatus>{
            if($CrearActualizarSolicitudDesembolsoRequest/ns1:SolicitudDesembolso/des:estatus = true())then 1
            else 0}</ns2:banEstatus>
            else()}
            <ns2:requiereValidacionRecExt>{if(fn:string( fn:data($CrearActualizarSolicitudDesembolsoRequest/ns1:SolicitudDesembolso/des:ValidacionAsignacion))='true')then 1 else if(fn:string( fn:data($CrearActualizarSolicitudDesembolsoRequest/ns1:SolicitudDesembolso/des:ValidacionAsignacion))='false')then '0'else ('')}</ns2:requiereValidacionRecExt>
        </ns2:SolicitudDesembolso>
    </ns2:SolicitudDesembolsoCollection>
};

local:func($CrearActualizarSolicitudDesembolsoRequest)
