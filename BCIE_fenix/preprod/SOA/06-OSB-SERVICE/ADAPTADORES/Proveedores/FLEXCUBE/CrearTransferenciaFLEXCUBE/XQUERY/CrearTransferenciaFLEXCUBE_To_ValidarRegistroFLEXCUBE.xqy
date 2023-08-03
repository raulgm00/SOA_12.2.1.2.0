xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)
declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns3="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearTransferencia";
(:: import schema at "../XSD/CrearTransferencia_sp.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";
declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $CrearTransferenciaFLEXCUBERequest as element() (:: schema-element(ns1:CrearTransferenciaFLEXCUBERequest) ::) external;
declare variable $crearTransferenciaResponse as element() (:: schema-element(ns3:OutputParameters) ::) external;

declare function local:func($crearTransferenciaResponse as element() (:: schema-element(ns3:OutputParameters) ::),$CrearTransferenciaFLEXCUBERequest as element() (:: schema-element(ns1:CrearTransferenciaFLEXCUBERequest) ::)) as element() (:: schema-element(ns2:ValidarRegistroFLEXCUBERequest) ::) {
     <ns2:ValidarRegistroFLEXCUBERequest>
         <ns2:codigoCliente>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:Parametros[com:name='CODIGO_CLIENTE']/com:value)}</ns2:codigoCliente>
        <ns2:LineaCredito>
            <lin:Descripcion>LOAN</lin:Descripcion>
            <lin:idContrato>{data($crearTransferenciaResponse/ns3:CODIGO_CONTRATO)}</lin:idContrato>
            <lin:MontoLinea>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:Transferencia/des:Monto[com:tipo/cat:DescripcionCorta= 'TRANSFERENCIA_BANCARIA']/com:importe)}</lin:MontoLinea>
            <lin:FechaValor>{fn:data($CrearTransferenciaFLEXCUBERequest/ns1:ContratoDesembolso/des:fechaDisponibilidadFondos)}</lin:FechaValor>
        </ns2:LineaCredito>
    </ns2:ValidarRegistroFLEXCUBERequest>
};

local:func($crearTransferenciaResponse,$CrearTransferenciaFLEXCUBERequest)