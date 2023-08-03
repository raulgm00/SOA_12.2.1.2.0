xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConversorMonedasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConversorMonedas/V1/Schema/ConversorMonedasMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace con = "http://www.bcie.org/ConversorMonedasBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarSaldoResponse as element() (:: schema-element(ns1:ConsultarSaldoResponse) ::) external;
declare variable $ConvertirMonedasImportesResponse as element() (:: schema-element(ns2:ConvertirMonedasImportesResponse) ::) external;
declare variable $ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::) external;

declare function local:func($ConsultarSaldoResponse as element() (:: schema-element(ns1:ConsultarSaldoResponse) ::), 
                            $ConvertirMonedasImportesResponse as element() (:: schema-element(ns2:ConvertirMonedasImportesResponse) ::), 
                            $ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::)) 
                            as element() (:: schema-element(ns1:ConsultarSaldoResponse) ::) {
    <ns1:ConsultarSaldoResponse>
      <ns1:ContratoDesembolso>
          <des:idDesembolso>{fn:data($ConsultarSaldoResponse/ns1:ContratoDesembolso/des:idDesembolso)}</des:idDesembolso>
          <des:idFacturador>{fn:data($ConsultarSaldoResponse/ns1:ContratoDesembolso/des:idFacturador)}</des:idFacturador>
           <des:condicionesFinancieras>{$ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:condicionesFinancieras/*}</des:condicionesFinancieras>
            {
                for $monto in $ConsultarSaldoResponse/ns1:ContratoDesembolso/des:monto
                return 
                <des:monto>
                    {
                        $monto/*
                    }
                </des:monto>
            }
            <des:monto>
                <com:tipo>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta>SALDO_USD</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:tipo>
                <com:importe>{fn:data($ConvertirMonedasImportesResponse/ns2:ConvierteMonedasImporte/con:montoConvertido)}</com:importe>
                <com:moneda>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($ConvertirMonedasImportesResponse/ns2:ConvierteMonedasImporte/con:monedaA/cat:codigoExterno)}</cat:codigoExterno>
                </com:moneda>
            </des:monto>
            {if ((xs:string($ConsultarSaldoResponse/ns1:ContratoDesembolso/des:monto[com:tipo/cat:DescripcionCorta = 'SALDO']/com:importe) = '0') and (xs:string($ConsultarSaldoResponse/ns1:Resultado/res:result) = 'OK')) then
              <des:estado>
                  <cat:Id>22</cat:Id>
                  <cat:Descripcion>Liquidado</cat:Descripcion>
                  <cat:DescripcionCorta>Liquidado</cat:DescripcionCorta>
              </des:estado>
            else
              <des:estado>
                {
                    if ($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:estado/cat:Id)
                    then <cat:Id>{fn:data($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:estado/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:estado/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:estado/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:estado/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:estado/cat:estatus)
                    then <cat:estatus>{fn:data($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:estado/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:estado/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:estado/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </des:estado>
          }
          <des:programado>{fn:data($ConsultarSaldoResponse/ns1:Resultado/res:result)}</des:programado>
      </ns1:ContratoDesembolso>
      <ns1:Resultado>
          <res:result>OK</res:result>
          <res:message>Operación exitosa</res:message>
      </ns1:Resultado>
    </ns1:ConsultarSaldoResponse>
};

local:func($ConsultarSaldoResponse, $ConvertirMonedasImportesResponse, $ConsultarDesembolsosByIdResponse)
