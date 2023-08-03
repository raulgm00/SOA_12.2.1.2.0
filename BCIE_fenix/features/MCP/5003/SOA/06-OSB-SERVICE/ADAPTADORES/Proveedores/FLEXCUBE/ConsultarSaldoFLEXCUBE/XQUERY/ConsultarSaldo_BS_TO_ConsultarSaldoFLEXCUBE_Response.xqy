xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarSaldo";
(:: import schema at "../XSD/ConsultarSaldo_sp.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarSaldoFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarSaldoFLEXCUBEResponse) ::) external;
declare variable $consultarSaldoResponse as element() (:: schema-element(ns2:OutputParameters) ::) external;

declare function local:func($ConsultarSaldoFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarSaldoFLEXCUBEResponse) ::), 
                            $consultarSaldoResponse as element() (:: schema-element(ns2:OutputParameters) ::)) 
                            as element() (:: schema-element(ns1:ConsultarSaldoFLEXCUBEResponse) ::) {
    <ns1:ConsultarSaldoFLEXCUBEResponse>
        <ns1:LineaCredito>
           
            <lin:Flexcube>
               <lin:id>{fn:data($ConsultarSaldoFLEXCUBERequest/ns1:LineaCredito/lin:Flexcube/lin:id)}</lin:id>
            </lin:Flexcube>
            {
              for $saldo in $consultarSaldoResponse/ns2:LISTA_BALANCE/ns2:LISTA_BALANCE_ITEM
              return
            <lin:Monto>
                <com:tipo>
                    <cat:DescripcionCorta>{fn:data($saldo/ns2:COMPONENTE)}</cat:DescripcionCorta>
                </com:tipo>
                <com:importe>{fn:data($saldo/ns2:BALANCE)}</com:importe>
            </lin:Monto>
            }
        </ns1:LineaCredito>
        <ns1:Resultado>
           <res:result>{if (fn:data($consultarSaldoResponse/ns2:CODIGO_RESULTADO = 0))then 'OK'
           else 'ERROR'}</res:result>
          <res:message>{if (fn:string-length(fn:data($consultarSaldoResponse/ns2:MENSAJE))> 0)
            then(fn:data($consultarSaldoResponse/ns2:MENSAJE))
            else 'Consulta Exitosa'}</res:message>
        </ns1:Resultado>
    </ns1:ConsultarSaldoFLEXCUBEResponse>
};

local:func($ConsultarSaldoFLEXCUBERequest, $consultarSaldoResponse)