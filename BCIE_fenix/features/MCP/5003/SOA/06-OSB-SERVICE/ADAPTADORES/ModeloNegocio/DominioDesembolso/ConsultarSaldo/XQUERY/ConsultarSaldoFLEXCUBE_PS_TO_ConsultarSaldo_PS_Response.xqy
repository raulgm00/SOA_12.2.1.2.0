xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

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

declare variable $ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::) external;
declare variable $ConsultarSaldoFLEXCUBEResponse as element() (:: schema-element(ns2:ConsultarSaldoFLEXCUBEResponse) ::) external;

declare function local:func($ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::), 
                            $ConsultarSaldoFLEXCUBEResponse as element() (:: schema-element(ns2:ConsultarSaldoFLEXCUBEResponse) ::)) 
                            as element() (:: schema-element(ns1:ConsultarSaldoResponse) ::) {
    <ns1:ConsultarSaldoResponse>
        <ns1:ContratoDesembolso>
            <des:idDesembolso>{fn:data($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:idDesembolso)}</des:idDesembolso>
            {
                if ($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:idFacturador)
                then <des:idFacturador>{fn:data($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:idFacturador)}</des:idFacturador>
                else ()
            }
            {
                for $Monto in $ConsultarSaldoFLEXCUBEResponse/ns2:LineaCredito/lin:Monto
                return 
                <des:monto>
                    <com:tipo>
                        <cat:Id>{fn:data($Monto/com:tipo/cat:Id)}</cat:Id>
                        <cat:Descripcion>{fn:data($Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                        <cat:DescripcionCorta>
                        {if (xs:string(fn:data($Monto/com:tipo/cat:DescripcionCorta)) = 'PRINCIPAL')
                          then 'CAPITAL'
                          else 'INTERES'
                        }
                        </cat:DescripcionCorta>
                        <cat:estatus>{fn:data($Monto/com:tipo/cat:estatus)}</cat:estatus>
                        <cat:codigoExterno>{fn:data($Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                    </com:tipo>
                    <com:importe>{fn:data($Monto/com:importe)}</com:importe>
                    <com:moneda>
                        <cat:Id>{fn:data($Monto/com:moneda/cat:Id)}</cat:Id>
                        <cat:Descripcion>{fn:data($Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        <cat:estatus>{fn:data($Monto/com:moneda/cat:estatus)}</cat:estatus>
                        <cat:codigoExterno>{fn:data($Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                   </com:moneda>
                </des:monto>
            }
            <des:monto>
                <com:tipo>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta>SALDO</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:tipo>
                <com:importe>{sum($ConsultarSaldoFLEXCUBEResponse/ns2:LineaCredito/lin:Monto/com:importe)}</com:importe>
                <com:moneda>
                    <cat:codigoExterno>{fn:data($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta = 'DESEMBOLSO']/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                </com:moneda>
            </des:monto>
        </ns1:ContratoDesembolso>
        <ns1:Resultado>
            <res:result>{
            if ($ConsultarSaldoFLEXCUBEResponse/ns2:Resultado/res:result = 'OK')then 'OK' else 'ERROR'}</res:result>
            <res:message>{
            if ($ConsultarSaldoFLEXCUBEResponse/ns2:Resultado/res:result = 'OK') then 'Consulta Exitosa' else (fn:data($ConsultarSaldoFLEXCUBEResponse/ns2:Resultado/res:message))
            }</res:message>
        </ns1:Resultado>
    </ns1:ConsultarSaldoResponse>
};

local:func($ConsultarDesembolsosByIdResponse, $ConsultarSaldoFLEXCUBEResponse)
