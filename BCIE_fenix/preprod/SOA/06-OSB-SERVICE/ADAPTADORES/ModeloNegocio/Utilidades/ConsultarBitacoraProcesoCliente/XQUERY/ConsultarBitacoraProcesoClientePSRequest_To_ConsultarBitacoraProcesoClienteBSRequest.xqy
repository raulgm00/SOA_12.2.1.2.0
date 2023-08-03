xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarBitacoraProcesoClienteDB";
(:: import schema at "../XSD/ConsultarBitacoraProcesoClienteDB.xsd" ::)

declare variable $ConsultarBitacoraProcesoClienteRequest as element() (:: schema-element(ns1:ConsultarBitacoraProcesoClienteRequest) ::) external;

declare function local:func($ConsultarBitacoraProcesoClienteRequest as element() (:: schema-element(ns1:ConsultarBitacoraProcesoClienteRequest) ::)) as element() (:: schema-element(ns2:ConsultarBitacoraProcesoClienteDBInput) ::) {
    <ns2:ConsultarBitacoraProcesoClienteDBInput>
        {
            if ($ConsultarBitacoraProcesoClienteRequest/ns1:idProceso) then
                <ns2:idProceso>{fn:data($ConsultarBitacoraProcesoClienteRequest/ns1:idProceso)}</ns2:idProceso>
            else ()
                
        }
        {
            if ($ConsultarBitacoraProcesoClienteRequest/ns1:idCliente) then
                <ns2:idCliente>{fn:data($ConsultarBitacoraProcesoClienteRequest/ns1:idCliente)}</ns2:idCliente>
            else ()
        }
    </ns2:ConsultarBitacoraProcesoClienteDBInput>
};

local:func($ConsultarBitacoraProcesoClienteRequest)
