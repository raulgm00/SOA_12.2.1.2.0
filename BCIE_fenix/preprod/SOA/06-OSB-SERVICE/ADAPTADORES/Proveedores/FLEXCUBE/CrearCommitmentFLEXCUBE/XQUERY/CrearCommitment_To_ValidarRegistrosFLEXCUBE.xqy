xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace m="http://org/bcie/ws/middle/FLEXCUBE.wsdl";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/ContratoBO";



declare variable $CrearCommitmentFLEXCUBERequest as element() (:: schema-element(ns1:CrearCommitmentFLEXCUBERequest) ::) external;
declare variable $crearCommitmentResponse as element(m:crearCommitmentResponse) external;

declare function local:func($crearCommitmentResponse as element(m:crearCommitmentResponse),$CrearCommitmentFLEXCUBERequest as element() (:: schema-element(ns1:CrearCommitmentFLEXCUBERequest) ::)) as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBERequest) ::) {
    <ns1:ValidarRegistroFLEXCUBERequest>
        <ns1:codigoCliente>{$CrearCommitmentFLEXCUBERequest/ns1:Operacion/ope:cliente/cli:idFacturador/text()}</ns1:codigoCliente>
        <ns1:LineaCredito>
            <lin:Descripcion>COMMITMENT</lin:Descripcion>
            <lin:idContrato>{data($crearCommitmentResponse/codigoContrato_out)}</lin:idContrato>
            <lin:MontoLinea>{data($CrearCommitmentFLEXCUBERequest/ns1:montoAprobacion)}</lin:MontoLinea>
            <lin:FechaValor>{fn:data($CrearCommitmentFLEXCUBERequest/ns1:Contrato/con1:LineaCredito/lin:FechaValor)}</lin:FechaValor>
        </ns1:LineaCredito>
    </ns1:ValidarRegistroFLEXCUBERequest>
};

local:func($crearCommitmentResponse,$CrearCommitmentFLEXCUBERequest)
