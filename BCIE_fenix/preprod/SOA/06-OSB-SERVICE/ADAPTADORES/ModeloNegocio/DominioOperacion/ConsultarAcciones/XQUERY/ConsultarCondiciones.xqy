xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarAcciones";
(:: import schema at "../XSD/ConsultarAcciones.xsd" ::)


declare function local:func() as element() (:: schema-element(ns1:ConsultarAccionesOutputCollection) ::) {
    <ns1:ConsultarAccionesOutputCollection>
        
    </ns1:ConsultarAccionesOutputCollection>
};

local:func()
