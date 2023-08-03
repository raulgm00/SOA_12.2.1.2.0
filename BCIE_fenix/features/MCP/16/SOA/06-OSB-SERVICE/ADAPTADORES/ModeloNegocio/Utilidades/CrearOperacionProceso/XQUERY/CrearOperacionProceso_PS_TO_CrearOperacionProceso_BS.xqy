xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearOperacionProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearOperacionProceso/V1/Schema/CrearOperacionProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/OperacionProcesoDB";
(:: import schema at "../XSD/OperacionProcesoDB_table.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearOperacionProcesoBO";

declare variable $requestCrearOperacionProcesoMessage as element() (:: schema-element(ns1:requestCrearOperacionProcesoMessage) ::) external;

declare function local:func($requestCrearOperacionProcesoMessage as element() (:: schema-element(ns1:requestCrearOperacionProcesoMessage) ::)) as element() (:: schema-element(ns2:TreOperacionProcesoBpmCollection) ::) {
    <ns2:TreOperacionProcesoBpmCollection>
        <ns2:TreOperacionProcesoBpm>
            <ns2:id></ns2:id>
            <ns2:idOperacion>{fn:data($requestCrearOperacionProcesoMessage/ns1:OperacionProcesoInput/cre:idOperacion)}</ns2:idOperacion>
            <ns2:idProceso>{fn:data($requestCrearOperacionProcesoMessage/ns1:OperacionProcesoInput/cre:idProceso)}</ns2:idProceso>
            <ns2:banEstatus>{xs:int(1)}</ns2:banEstatus>
        </ns2:TreOperacionProcesoBpm>
    </ns2:TreOperacionProcesoBpmCollection>
};

local:func($requestCrearOperacionProcesoMessage)