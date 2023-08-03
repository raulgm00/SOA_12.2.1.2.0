xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)
declare namespace m="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace typ="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)
declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

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

declare variable $crearLoanResponse as element() (:: element(m:crearLoanResponse) ::) external;
declare variable $CrearDesembolsoLoanFLEXCUBERequest as element() (:: schema-element(ns1:CrearDesembolsoLoanFLEXCUBERequest) ::) external;

declare function local:func($crearLoanResponse as element() (:: element(m:crearLoanResponse) ::),$CrearDesembolsoLoanFLEXCUBERequest as element() (:: schema-element(ns1:CrearDesembolsoLoanFLEXCUBERequest) ::)) as element() (:: schema-element(ns2:ValidarRegistroFLEXCUBERequest) ::) {
    <ns2:ValidarRegistroFLEXCUBERequest>
        <ns2:codigoCliente>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:Cliente/cli:idFacturador)}</ns2:codigoCliente>
        <ns2:LineaCredito>
            <lin:Descripcion>LOAN</lin:Descripcion>
            <lin:idContrato>{data($crearLoanResponse/codigoContrato_out)}</lin:idContrato>
            <lin:MontoLinea>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta='DESEMBOLSO']/com:importe)}</lin:MontoLinea>
            <lin:FechaValor>{fn:data($CrearDesembolsoLoanFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaDisponibilidadFondos)}</lin:FechaValor>
        </ns2:LineaCredito>
    </ns2:ValidarRegistroFLEXCUBERequest>
};

local:func($crearLoanResponse,$CrearDesembolsoLoanFLEXCUBERequest)
