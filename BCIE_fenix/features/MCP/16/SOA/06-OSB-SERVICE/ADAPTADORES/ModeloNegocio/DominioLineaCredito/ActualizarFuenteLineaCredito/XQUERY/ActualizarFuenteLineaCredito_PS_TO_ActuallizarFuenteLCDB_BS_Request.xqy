xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarFuenteLC_DB";
(:: import schema at "../XSD/ActualizarFuenteLC_DB_table.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ActualizarFuenteLCRequest as element() (:: schema-element(ns1:ActualizarFuenteLCRequest) ::) external;

declare function local:func($ActualizarFuenteLCRequest as element() (:: schema-element(ns1:ActualizarFuenteLCRequest) ::)) as element() (:: schema-element(ns2:FuenteCollection) ::) {
    <ns2:FuenteCollection>
        <ns2:Fuente>
            <ns2:id>{fn:data($ActualizarFuenteLCRequest/ns1:Fuente/lin:idFuente)}</ns2:id>
            <ns2:idLineaCredito>{fn:data($ActualizarFuenteLCRequest/ns1:Fuente/lin:idLineaCredito)}</ns2:idLineaCredito>
            <ns2:idLineaPasiva>{fn:data($ActualizarFuenteLCRequest/ns1:Fuente/lin:idLineaPasiva)}</ns2:idLineaPasiva>
            <ns2:descripcion>{fn:data($ActualizarFuenteLCRequest/ns1:Fuente/lin:Descripcion)}</ns2:descripcion>
            <ns2:porcentaje></ns2:porcentaje>
            <ns2:fechaObtenido>{fn:data($ActualizarFuenteLCRequest/ns1:Fuente/lin:FechaObtenido)}</ns2:fechaObtenido>
            <ns2:montoAsignado>{fn:data($ActualizarFuenteLCRequest/ns1:Fuente/lin:MontoAsignado)}</ns2:montoAsignado>
            <ns2:numeroAsignacion>{fn:data($ActualizarFuenteLCRequest/ns1:Fuente/lin:NumeroAsignacion)}</ns2:numeroAsignacion>
            <ns2:comentario>{fn:data($ActualizarFuenteLCRequest/ns1:Fuente/lin:Comentario)}</ns2:comentario>
            <ns2:idContrato>{fn:data($ActualizarFuenteLCRequest/ns1:Fuente/lin:idContrato)}</ns2:idContrato>
        </ns2:Fuente>
    </ns2:FuenteCollection>
};

local:func($ActualizarFuenteLCRequest)
