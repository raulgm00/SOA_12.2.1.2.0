xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare variable $inContrato as element() (:: schema-element(ns1:ActualizarContratoRequest) ::) external;

declare function local:func($inContrato as element() (:: schema-element(ns1:ActualizarContratoRequest) ::)) as element() (:: schema-element(ns1:ActualizarContratoRequest) ::) {
    <ns1:ActualizarContratoRequest>
        <ns1:Contrato>
            <con:idContrato>{fn:data($inContrato/ns1:Contrato/con:idContrato)}</con:idContrato>
            <con:idOperacion>{fn:data($inContrato/ns1:Contrato/con:idOperacion)}</con:idOperacion>
            <con:fechaFirma>{fn:data($inContrato/ns1:Contrato/con:fechaFirma)}</con:fechaFirma>
            <con:fechaVigencia>{fn:data($inContrato/ns1:Contrato/con:fechaVigencia)}</con:fechaVigencia>
            <con:MontoEscriturado>{fn:data($inContrato/ns1:Contrato/con:MontoEscriturado)}</con:MontoEscriturado>
            <con:cuentaCliente>
                {
                    for $cuentaCliente in $inContrato/ns1:Contrato/con:cuentaCliente/con:cuentaCliente
                    return 
                    <con:cuentaCliente>{fn:data($inContrato/ns1:Contrato/con:cuentaCliente/con:cuentaCliente)}</con:cuentaCliente>
                }
            </con:cuentaCliente>
            <con:instanciaProceso>{fn:data($inContrato/ns1:Contrato/con:instanciaProceso)}</con:instanciaProceso>
        </ns1:Contrato>
    </ns1:ActualizarContratoRequest>
};

local:func($inContrato)
