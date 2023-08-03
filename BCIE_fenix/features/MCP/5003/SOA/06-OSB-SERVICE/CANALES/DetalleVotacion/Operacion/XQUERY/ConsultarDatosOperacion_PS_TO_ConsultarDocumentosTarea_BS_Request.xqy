xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarDocumentoTareaDB";
(:: import schema at "../../../../ADAPTADORES/ModeloNegocio/DominioDocumento/ConsultarDocumentoTarea/XSD/ConsultarDocumentoTareaDB.xsd" ::)

declare variable $idTarea as xs:decimal external;
declare variable $puedeConsultar as xs:decimal external;

declare function local:func($idTarea as xs:decimal, 
                            $puedeConsultar as xs:decimal) 
                            as element() (:: schema-element(ns1:ConsultarDocumentoTareaDBInput) ::) {
    <ns1:ConsultarDocumentoTareaDBInput>
        <ns1:idTarea>{fn:data($idTarea)}</ns1:idTarea>
        <ns1:puedeConsultar>{fn:data($puedeConsultar)}</ns1:puedeConsultar>
    </ns1:ConsultarDocumentoTareaDBInput>
};

local:func($idTarea, $puedeConsultar)
