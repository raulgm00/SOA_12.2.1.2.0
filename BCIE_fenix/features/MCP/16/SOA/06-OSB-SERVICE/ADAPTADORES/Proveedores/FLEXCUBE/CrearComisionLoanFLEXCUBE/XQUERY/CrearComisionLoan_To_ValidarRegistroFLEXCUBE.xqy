xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns3="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearCommitment";
(:: import schema at "../XSD/CrearCommitment_sp.xsd" ::)
declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare variable $CrearComisionFLEXCUBERequest as element() (:: schema-element(ns2:CrearPrestamoFLEXCUBERequest) ::) external;

declare variable $crearLoanResponse as element() (:: element(ns3:OutputParameters) ::) external;

declare function local:func($CrearComisionFLEXCUBERequest as element() (:: schema-element(ns2:CrearPrestamoFLEXCUBERequest) ::), 
                            $crearLoanResponse as element(ns3:OutputParameters) (:: element(ns3:OutputParameters) ::)) 
                            as element() (:: schema-element(ns2:ValidarRegistroFLEXCUBERequest) ::) {
    <ns2:ValidarRegistroFLEXCUBERequest>
        <ns2:codigoCliente>{fn:data($CrearComisionFLEXCUBERequest/ns2:Prestamo/lin:Operacion/ope:cliente/cli:idFacturador)}</ns2:codigoCliente>
        <ns2:LineaCredito>
            <lin:idContrato>{fn:data($crearLoanResponse/ns3:CODIGO_CONTRATO)}</lin:idContrato>
            <lin:Descripcion>LOAN</lin:Descripcion>
            <lin:MontoLinea>{fn:data($CrearComisionFLEXCUBERequest/ns2:Prestamo/lin:Comision/com1:montoComision)}</lin:MontoLinea>
            <lin:FechaValor>{fn:data($CrearComisionFLEXCUBERequest/ns2:Prestamo/lin:Comision/com1:fechaValor)}</lin:FechaValor>
        </ns2:LineaCredito>
    </ns2:ValidarRegistroFLEXCUBERequest>
};

local:func($CrearComisionFLEXCUBERequest, $crearLoanResponse)