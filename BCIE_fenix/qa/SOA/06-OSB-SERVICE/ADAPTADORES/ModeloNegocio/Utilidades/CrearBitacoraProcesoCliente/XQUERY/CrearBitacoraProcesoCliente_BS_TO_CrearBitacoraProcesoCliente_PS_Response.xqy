xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacoraProcesoCliente/V1/Schema/CrearBitacoraProcesoClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearBitacoraProcesoCliente_DB";
(:: import schema at "../XSD/CrearBitacoraProcesoCliente_DB_table.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $TbiProcesoClienteCollection as element() (:: schema-element(ns1:TbiProcesoClienteCollection) ::) external;

declare function local:func($TbiProcesoClienteCollection as element() (:: schema-element(ns1:TbiProcesoClienteCollection) ::)) as element() (:: schema-element(ns2:responseCrearBitacoraProcesoClienteMessage) ::) {
    <ns2:responseCrearBitacoraProcesoClienteMessage>
        <ns2:Result>
            <res:result>OK</res:result>
            <res:message>{fn:concat("Registro insertado correctamente, Id: ",fn:data($TbiProcesoClienteCollection/ns1:TbiProcesoCliente/ns1:id))}</res:message>
            <res:error>
                <err:errorCode></err:errorCode> </res:error>
        </ns2:Result>
    </ns2:responseCrearBitacoraProcesoClienteMessage>
};

local:func($TbiProcesoClienteCollection)
