xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare variable $outConsultarTransaccionCondicion as element() (:: schema-element(ns2:ConsultarTransaccionCondicionResponse) ::) external;
declare variable $numeroContrato as xs:string external;

declare function local:func($outConsultarTransaccionCondicion as element() (:: schema-element(ns2:ConsultarTransaccionCondicionResponse) ::)) as element() (:: schema-element(ns1:CrearActualizarDesembolsosRequest) ::) {
    <ns1:CrearActualizarDesembolsosRequest>
        {
        
          if(fn:string-length(xs:string(fn:data($numeroContrato))) > 0) then 
            <ns1:ContratoDesembolso>
              <des:idDesembolso>{fn:data($numeroContrato)}</des:idDesembolso>
              <des:estado>
                  <cat:Id>13</cat:Id>
              </des:estado>
            </ns1:ContratoDesembolso>
          else(
            for $Contrato in $outConsultarTransaccionCondicion/ns2:TransaccionCondicion/con:ContratoDesembolso
              return 
          <ns1:ContratoDesembolso>
              <des:idDesembolso>{fn:data($Contrato/des:idDesembolso)}</des:idDesembolso>
              <des:estado>
                  <cat:Id>13</cat:Id>
              </des:estado>
          </ns1:ContratoDesembolso>
        )            
      }
    </ns1:CrearActualizarDesembolsosRequest>
};

local:func($outConsultarTransaccionCondicion)