xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace m="http://org/bcie/ws/middle/FLEXCUBE.wsdl";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare variable $CrearDesembolsoComplejoFLEXCUBERequest as element() (:: schema-element(ns1:CrearDesembolsoComplejoFLEXCUBERequest) ::) external;
declare variable $crearCommitmentResponse as element(m:crearCommitmentResponse) external;

declare function local:func($crearCommitmentResponse as element(m:crearCommitmentResponse),$CrearDesembolsoComplejoFLEXCUBERequest as element() (:: schema-element(ns1:CrearDesembolsoComplejoFLEXCUBERequest) ::)) as element() (:: schema-element(ns2:ValidarRegistroFLEXCUBERequest) ::) {
    <ns2:ValidarRegistroFLEXCUBERequest>
        <ns2:codigoCliente>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:Cliente/cli:idFacturador)}</ns2:codigoCliente>
        <ns2:LineaCredito>
            <lin:Descripcion>LOAN</lin:Descripcion>
            <lin:idContrato>{data($crearCommitmentResponse/codigoContrato_out)}</lin:idContrato>
            <lin:MontoLinea>{fn:data($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta='DESEMBOLSO']/com:importe)}</lin:MontoLinea>
            <lin:FechaValor>{fn:substring-before($CrearDesembolsoComplejoFLEXCUBERequest/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaDisponibilidadFondos/text(),'T')}</lin:FechaValor>
        </ns2:LineaCredito>
    </ns2:ValidarRegistroFLEXCUBERequest>
};

local:func($crearCommitmentResponse,$CrearDesembolsoComplejoFLEXCUBERequest)
