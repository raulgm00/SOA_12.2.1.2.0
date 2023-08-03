xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/FLEXCUBE14/ClienteMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/Flexcube14/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/consultarCodigoCliente_DB";
(:: import schema at "../XSD/consultarCodigoCliente_DB.xsd" ::)

declare namespace res = "http://www.bcie.org/FLEXCUBE14/ResultBO";

declare namespace err = "http://www.bcie.org/FLEXCUBE14/ErrorBO";

declare variable $consultarCodigoCliente_DBOutputCollection as element() (:: schema-element(ns1:consultarCodigoCliente_DBOutputCollection) ::) external;

declare function local:func($consultarCodigoCliente_DBOutputCollection as element() (:: schema-element(ns1:consultarCodigoCliente_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarCodigoClienteResponse) ::) {
    <ns2:ConsultarCodigoClienteResponse>
        <ns2:Response>
            <ns2:Codigo_Cliente>{fn:data($consultarCodigoCliente_DBOutputCollection/ns1:consultarCodigoCliente_DBOutput[1]/ns1:CUSTOMER_NO)}</ns2:Codigo_Cliente>
            <ns2:Resultado>
                <res:result>OK</res:result>
                <res:message>Consulta Realizada Exitosamente</res:message>
            </ns2:Resultado>
        </ns2:Response>
    </ns2:ConsultarCodigoClienteResponse>
};

local:func($consultarCodigoCliente_DBOutputCollection)
