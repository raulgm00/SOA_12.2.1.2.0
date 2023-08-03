xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/MatrizTCCBO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCBO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $tcc as element() (:: element(*, ns1:TCC) ::) external;

declare function local:func($tcc as element() (:: element(*, ns1:TCC) ::)) as element() (:: schema-element(ns2:ActualizarComisionRequest) ::) {
    <ns2:ActualizarComisionRequest>
        <ns2:Comision>
            <com:idComision>{fn:data($tcc/ns1:id)}</com:idComision>
            <com:estadoTCC>
                <atr:id>{fn:data($tcc/ns1:estado)}</atr:id>
            </com:estadoTCC>
            <com:subEstadoTCC>
                <atr:id>{fn:data($tcc/ns1:subEstado)}</atr:id>
            </com:subEstadoTCC>
        </ns2:Comision>
    </ns2:ActualizarComisionRequest>
};

local:func($tcc)
