xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ConBO="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

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

declare variable $ConsultarDesembolsoById as element() (:: schema-element(ConBO:ConsultarDesembolsosByIdResponse) ::) external;

declare function local:func($ConsultarDesembolsoById as element() (:: schema-element(ConBO:ConsultarDesembolsosByIdResponse) ::)) as element() (:: schema-element(ConBO:ConsultarSaldoResponse) ::) {

    <ConBO:ConsultarSaldoResponse>
        <ConBO:ContratoDesembolso>
        {
          for $desembolso in  $ConsultarDesembolsoById/ConBO:ContratoDesembolso[1]
          return
            $desembolso/*
        }
        </ConBO:ContratoDesembolso>
        <ConBO:Resultado>
          {
            let $resultado := $ConsultarDesembolsoById/ConBO:Resultado
            return $resultado/*
          }
        </ConBO:Resultado>
        
    </ConBO:ConsultarSaldoResponse>
};

local:func($ConsultarDesembolsoById)
