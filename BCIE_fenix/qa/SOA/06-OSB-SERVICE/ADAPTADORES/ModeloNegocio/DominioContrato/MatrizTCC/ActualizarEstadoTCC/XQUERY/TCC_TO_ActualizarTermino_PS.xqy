xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MatrizTCCBO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCBO.xsd" ::)
declare namespace ns2="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare variable $tcc as element() (:: element(*, ns1:TCC) ::) external;

declare function local:func($tcc as element() (:: element(*, ns1:TCC) ::)) as element() (:: schema-element(ns2:ActualizarTerminoRequest) ::) {
    <ns2:ActualizarTerminoRequest>
        <ns2:Termino>
            <ter:idTermino>{fn:data($tcc/ns1:id)}</ter:idTermino>
            <ter:estadoTCC>
                <atr:id>{fn:data($tcc/ns1:estado)}</atr:id>
            </ter:estadoTCC>
            <ter:subEstadoTCC>
                <atr:id>{fn:data($tcc/ns1:subEstado)}</atr:id>
            </ter:subEstadoTCC>
        </ns2:Termino>
    </ns2:ActualizarTerminoRequest>
};

local:func($tcc)
