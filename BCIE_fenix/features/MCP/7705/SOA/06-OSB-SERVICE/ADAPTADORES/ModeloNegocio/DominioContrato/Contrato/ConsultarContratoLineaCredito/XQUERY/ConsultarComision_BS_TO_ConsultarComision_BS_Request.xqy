xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarContrato";
(:: import schema at "../XSD/ConsultarContrato.xsd" ::)

declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTcaComision";
(:: import schema at "../../../../DominioLineaCredito/ConsultarComisionByContrato/XSD/ConsultarTcaComision.xsd" ::)

declare variable $requestcomision as element() (:: schema-element(ns1:ConsultarContratoOutputCollection) ::) external;
declare variable $index as xs:int external;

declare function local:func($requestcomision as element() (:: schema-element(ns1:ConsultarContratoOutputCollection) ::),
                            $index as xs:int) as element() (:: schema-element(ns2:ConsultarTcaComisionInput) ::) {
    <ns2:ConsultarTcaComisionInput>
        <ns2:P_IDCONTRATO>{fn:data($requestcomision/ns1:ConsultarContratoOutput[$index]/ns1:ID)}</ns2:P_IDCONTRATO>
      
     
    </ns2:ConsultarTcaComisionInput>
};

local:func($requestcomision, $index)
