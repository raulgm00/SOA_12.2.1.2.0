xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarSaldoCartera_DB";
(:: import schema at "../XSD/ConsultarSaldoCartera_DB_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConsultarSaldoCarteraResponse) ::) {
    <ns2:ConsultarSaldoCarteraResponse>
        <ns2:Monto>
          <com:tipo>
              <cat:DescripcionCorta>SALDO_CARTERA</cat:DescripcionCorta>
          </com:tipo>
          <com:importe>{fn:data($OutputParameters/ns1:P_SALDO)}</com:importe>
        </ns2:Monto>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{
            if (string-length(fn:data($OutputParameters/ns1:P_SALDO/text())) > 0)
            then 'Consulta Exitosa'
            else 'No existen registros'
            }</res:message>
        </ns2:Resultado>
    </ns2:ConsultarSaldoCarteraResponse>
};

local:func($OutputParameters)
