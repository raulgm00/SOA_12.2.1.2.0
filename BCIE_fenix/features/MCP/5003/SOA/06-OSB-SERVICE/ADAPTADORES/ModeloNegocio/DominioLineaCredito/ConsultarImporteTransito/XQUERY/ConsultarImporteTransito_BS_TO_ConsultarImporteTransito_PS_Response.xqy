xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/Consultar_Importe_Transito_db";
(:: import schema at "../XSD/Consultar_Importe_Transito_db_sp.xsd" ::)

declare namespace ns3="http://www.bcie.org/ConversorMonedasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConversorMonedas/V1/Schema/ConversorMonedasMO.xsd" ::)

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

declare namespace con1 = "http://www.bcie.org/ConversorMonedasBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;
declare variable $ConvertirMonedasImportesResponse as element() (:: schema-element(ns3:ConvertirMonedasImportesResponse) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::),$ConvertirMonedasImportesResponse as element() (:: schema-element(ns3:ConvertirMonedasImportesResponse) ::)) as element() (:: schema-element(ns2:ConsultarImporteTransitoResponse) ::) {
    <ns2:ConsultarImporteTransitoResponse>
    {
    
        if(fn:data($OutputParameters/ns1:P_CODIGO_RES) = 1) then
        <ns2:LineaCredito>
            <lin:idLineaCredito>{fn:data($OutputParameters/ns1:RECORDSET1/ns1:Row[1]/ns1:Column[@name='ID_LINEA_CREDITO'])}</lin:idLineaCredito>
            <lin:NumeroLineaCredito>{fn:data($OutputParameters/ns1:RECORDSET1/ns1:Row[1]/ns1:Column[@name='NUMERO_LINEA_CREDITO'])}</lin:NumeroLineaCredito>
          
            <lin:Monto>
                <com:tipo>
                    <cat:DescripcionCorta>TRANSITO</cat:DescripcionCorta>
                </com:tipo>
                {
                  let $importeTotal := fn:sum($ConvertirMonedasImportesResponse/ns3:ConvierteMonedasImporte/con1:montoConvertido)
                return 
                <com:importe>{local:formatNumber($importeTotal)}</com:importe>
                }
                <com:moneda>
                  <cat:codigoExterno>USD</cat:codigoExterno>
                </com:moneda>
            </lin:Monto>
    
            
            {
              for $contratos in $OutputParameters/ns1:RECORDSET1/ns1:Row
                return
            <lin:ContratoDesembolso> 
                <des:idDesembolso>{fn:data($contratos/ns1:Column[@name='ID_CONTRATO_DESEMBOLSO'])}</des:idDesembolso>
                <des:monto>
                    <com:tipo>
                        <cat:DescripcionCorta>DESEMBOLSO</cat:DescripcionCorta>
                    </com:tipo>
                    <com:importe>{fn:data($contratos/ns1:Column[@name='MONTO_DESEMBOLSAR'])}</com:importe>
                    <com:moneda>
                        <cat:Id>{fn:data($contratos/ns1:Column[@name='ID_TCA_TIPO_MONEDA'])}</cat:Id>
                        <cat:DescripcionCorta>{fn:data($contratos/ns1:Column[@name='DESCRIPCION_CORTA_MONEDA'])}</cat:DescripcionCorta>
                    </com:moneda>
                </des:monto>
                <des:estado>
                    <cat:Id>{fn:data($contratos/ns1:Column[@name='ID_TCA_ESTADO'])}</cat:Id>
                    <cat:DescripcionCorta>{fn:data($contratos/ns1:Column[@name='DESCRIPCION_CORTA_ESTADO'])}</cat:DescripcionCorta>
                </des:estado>
                <des:programado>{fn:data($contratos/ns1:Column[@name='PROGRAMADO'])}</des:programado>
                <des:Programa>
                    <her:DestinoFinanciamiento>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno>{fn:data($contratos/ns1:Column[@name='DESTINO_FINANCIAMIENTO'])}</cat:codigoExterno>
                    </her:DestinoFinanciamiento>
                </des:Programa>
                <des:fechaEstimadaDisponibilidad>{fn:data($contratos/ns1:Column[@name='FECHA_ESTIMADA_DESEMBOLSAR'])}</des:fechaEstimadaDisponibilidad>
                <des:fechaEfectiva>{fn:data($contratos/ns1:Column[@name='FECHA_EFECTIVA'])}</des:fechaEfectiva>
                <des:fechaRegistro>{fn:data($contratos/ns1:Column[@name='FECHA_REGISTRO'])}</des:fechaRegistro>
                <des:estatus>{fn:data($contratos/ns1:Column[@name='BAN_ESTATUS'])}</des:estatus>
            </lin:ContratoDesembolso>
            }
          
        </ns2:LineaCredito>
        else()
        } 
        <ns2:Resultado>
            {
        if(fn:data($OutputParameters/ns1:P_CODIGO_RES) = 1) then
            <res:result>OK</res:result>
        else
             <res:result>ERROR</res:result>
            }
            <res:message>{fn:data($OutputParameters/ns1:P_MENSAJE)}</res:message>
        </ns2:Resultado>
    </ns2:ConsultarImporteTransitoResponse>
};
declare function local:formatNumber($number as xs:double)
as xs:string {
if($number = 0)then '0.00'
else
   fn-bea:format-number(xs:double($number),"######.00")
};
local:func($OutputParameters,$ConvertirMonedasImportesResponse)
