xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarPlazoGracia";
(:: import schema at "../XSD/ConsultarPlazoGracia.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare variable $ConsultarPlazoGraciaOutput as element() (:: schema-element(ns1:ConsultarPlazoGraciaOutputCollection) ::) external;

declare function local:func($ConsultarPlazoGraciaOutput as element() (:: schema-element(ns1:ConsultarPlazoGraciaOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarTreTerminoResponse) ::) {
    <ns2:ConsultarTreTerminoResponse>
        <ns2:Termino>
            <ter:idTermino>{fn:data($ConsultarPlazoGraciaOutput/ns1:ConsultarPlazoGraciaOutput/ns1:ID)}</ter:idTermino>
            <ter:operacion>{fn:data($ConsultarPlazoGraciaOutput/ns1:ConsultarPlazoGraciaOutput/ns1:ID_OPERACION)}</ter:operacion>
            <ter:tipoTermino>
                <ter:idCatTermino>{fn:data($ConsultarPlazoGraciaOutput/ns1:ConsultarPlazoGraciaOutput/ns1:ID_TCA_TERMINO)}</ter:idCatTermino>                
            </ter:tipoTermino>            
        </ns2:Termino>
    </ns2:ConsultarTreTerminoResponse>
};

local:func($ConsultarPlazoGraciaOutput)
