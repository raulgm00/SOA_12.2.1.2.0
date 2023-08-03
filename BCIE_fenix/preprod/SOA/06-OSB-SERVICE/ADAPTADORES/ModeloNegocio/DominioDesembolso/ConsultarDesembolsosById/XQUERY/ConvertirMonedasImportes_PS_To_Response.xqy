xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConversorMonedasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConversorMonedas/V1/Schema/ConversorMonedasMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/DesembolsoMO";
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

declare namespace con = "http://www.bcie.org/ConversorMonedasBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::) external;
declare variable $ConvertirMonedasImportesResponse as element() (:: schema-element(ns2:ConvertirMonedasImportesResponse) ::) external;

declare function local:func($ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::), 
                            $ConvertirMonedasImportesResponse as element() (:: schema-element(ns2:ConvertirMonedasImportesResponse) ::)) 
                            as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::) {
    <ns1:ConsultarDesembolsosByIdResponse>
    {               
        for $idDesembolso in distinct-values($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:idDesembolso)
        let $desembolso := $ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso[des:idDesembolso = $idDesembolso ][1]
        return
        <ns1:ContratoDesembolso>
            <des:idDesembolso>{fn:data($desembolso/des:idDesembolso)}</des:idDesembolso>
            <des:idFacturador>{fn:data($desembolso/des:idFacturador)}</des:idFacturador>
            <des:producto>
            {$desembolso/des:producto/*}
            </des:producto>
            <des:referencia>{fn:data($desembolso/des:referencia)}</des:referencia>
            {
                for $monto in $desembolso/des:monto
                return 
                <des:monto>
 
                    <com:tipo>
                        <cat:Id>{fn:data($monto/com:tipo/cat:Id)}</cat:Id>
                        <cat:Descripcion>{fn:data($monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        <cat:estatus>{fn:data($monto/com:tipo/cat:estatus)}</cat:estatus>
                        <cat:codigoExterno>{fn:data($monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                    </com:tipo>
                    {
                    let $importe := if( ($ConvertirMonedasImportesResponse/ns2:ConvierteMonedasImporte/con:montoConvertido=0) or (count($ConvertirMonedasImportesResponse/ns2:ConvierteMonedasImporte/con:montoConvertido)=0))
                    then $desembolso/des:monto[1]/com:importe
                    else $ConvertirMonedasImportesResponse/ns2:ConvierteMonedasImporte/con:montoConvertido
                    return
                    if (fn:data($monto/com:tipo/cat:DescripcionCorta) = 'DESEMBOLSO_USD' )
                      then
                      <com:importe>{fn:data($importe)}</com:importe>
                      else
                      <com:importe>{fn:data($monto/com:importe)}</com:importe>
                    }
                    <com:moneda>
                        <cat:Id>{fn:data($monto/com:moneda/cat:Id)}</cat:Id>
                        <cat:Descripcion>{fn:data($monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        <cat:estatus>{fn:data($monto/com:moneda/cat:estatus)}</cat:estatus>
                        <cat:codigoExterno>{fn:data($monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                    </com:moneda>
                </des:monto>
            }
            <des:estado>
            {$desembolso/des:estado/*}
            </des:estado>
            <des:programado>{fn:data($desembolso/des:programado)}</des:programado>
            <des:fechaEstimadaDesembolsar>{fn:data($desembolso/des:fechaEstimadaDesembolsar)}</des:fechaEstimadaDesembolsar>
            <des:fechaEfectiva>{fn:data($desembolso/des:fechaEfectiva)}</des:fechaEfectiva>
            <des:fechaAsignacion>{fn:data($desembolso/des:fechaAsignacion)}</des:fechaAsignacion>
            <des:fechaRegistro>{fn:data($desembolso/des:fechaRegistro)}</des:fechaRegistro>
            <des:fechaVencimiento>{fn:data($desembolso/des:fechaVencimiento)}</des:fechaVencimiento>
            <des:estatus>{fn:data($desembolso/des:estatus)}</des:estatus>
            <des:condicionesFinancieras>
            {$desembolso/des:condicionesFinancieras/*}
            </des:condicionesFinancieras>
            <des:idLinea>{fn:data($desembolso/des:idLinea)}</des:idLinea>
            <des:recursosExternos>{fn:data($desembolso/des:recursosExternos)}</des:recursosExternos>
            <des:cuentaCliente>{fn:data($desembolso/des:cuentaCliente)}</des:cuentaCliente>
            {
                if ($desembolso/des:usuario)
                then <des:usuario>{fn:data($desembolso/des:usuario)}</des:usuario>
                else ()
            }
            <des:fechaDisponibilidadFondos>{fn:data($desembolso/des:fechaDisponibilidadFondos)}</des:fechaDisponibilidadFondos>
            <des:origenTransferenciaCte>{fn:data($desembolso/des:origenTransferenciaCte)}</des:origenTransferenciaCte>
            <des:Programa>
            {$desembolso/des:Programa/*}
            </des:Programa>
            <des:EstimadoDesembolso>
            {$desembolso/des:EstimadoDesembolso/*}
            </des:EstimadoDesembolso>
            <des:fechaInicioProceso>{fn:data($desembolso/des:fechaInicioProceso)}</des:fechaInicioProceso>
            <des:fechaEstimadaDisponibilidad>{fn:data($desembolso/des:fechaEstimadaDisponibilidad)}</des:fechaEstimadaDisponibilidad>
            {
                for $utilizacion in $desembolso/des:Utilizacion
                return 
                  <des:Utilizacion>
                  {$utilizacion/*}
                  </des:Utilizacion>
            }
            {
                for $cargo in $desembolso/des:Cargo
                return 
                  <des:Cargo>
                  {$cargo/*}
                  </des:Cargo>
            }
            {
                for $comision in $desembolso/des:Comision
                return 
                  <des:Comision>
                  {$comision/*}
                  </des:Comision>
            }
            {
                for $transferencia in $desembolso/des:Transferencia
                return 
                  <des:Transferencia>
                  {$transferencia/*}
                  </des:Transferencia>
            }
            <des:TransferenciaFT05>
              {$desembolso/des:TransferenciaFT05/*}
            </des:TransferenciaFT05>
            <des:HerramientaCE>
            {$desembolso/des:HerramientaCE/*}
            </des:HerramientaCE>
            <des:modalidadFinan>
                {
                    if ($desembolso/des:modalidadFinan/cat:Id)
                    then <cat:Id>{fn:data($desembolso/des:modalidadFinan/cat:Id)}</cat:Id>
                    else ()
                }
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </des:modalidadFinan>
        </ns1:ContratoDesembolso>
        }
        <ns1:Resultado>
            <res:result>{fn:data($ConsultarDesembolsosByIdResponse/ns1:Resultado/res:result)}</res:result>
            <res:message>{fn:data($ConsultarDesembolsosByIdResponse/ns1:Resultado/res:message)}</res:message>
            {
                if ($ConsultarDesembolsosByIdResponse/ns1:Resultado/res:error)
                then 
                    <res:error>
                        <err:errorCode>{fn:data($ConsultarDesembolsosByIdResponse/ns1:Resultado/res:error/err:errorCode)}</err:errorCode>
                        <err:errorDescription>{fn:data($ConsultarDesembolsosByIdResponse/ns1:Resultado/res:error/err:errorDescription)}</err:errorDescription>
                        <err:errorType>{fn:data($ConsultarDesembolsosByIdResponse/ns1:Resultado/res:error/err:errorType)}</err:errorType>
                    </res:error>
                else ()
            }
        </ns1:Resultado>
    </ns1:ConsultarDesembolsosByIdResponse>
};

local:func($ConsultarDesembolsosByIdResponse, $ConvertirMonedasImportesResponse)
