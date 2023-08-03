xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/DetalleContratoDesembolso";
(:: import schema at "../XSD/DetalleContratoDesembolso_sp.xsd" ::)

declare variable $response as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($response as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:DetalleContratoDesembolsoResponse) ::) {
    <ns2:DetalleContratoDesembolso>
        {
            if ($response/ns1:P_CODIGO_RES)
            then <ns2:P_CODIGO_RES>{fn:data($response/ns1:P_CODIGO_RES)}</ns2:P_CODIGO_RES>
            else ()
        }
        {
            if ($response/ns1:P_MENSAJE)
            then <ns2:P_MENSAJE>{fn:data($response/ns1:P_MENSAJE)}</ns2:P_MENSAJE>
            else ()
        }
        {
            if ($response/ns1:P_TBLDESEMBOLSOS)
            then <ns2:P_TBLDESEMBOLSOS>
                {
                    for $P_TBLDESEMBOLSOS_ITEM in $response/ns1:P_TBLDESEMBOLSOS/ns1:P_TBLDESEMBOLSOS_ITEM
                    return 
                    <ns2:P_TBLDESEMBOLSOS_ITEM>
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:ID_LINEA_CREDITO)
                            then <ns2:ID_LINEA_CREDITO>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:ID_LINEA_CREDITO)}</ns2:ID_LINEA_CREDITO>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:ID_CONTRATO)
                            then <ns2:ID_CONTRATO>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:ID_CONTRATO)}</ns2:ID_CONTRATO>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:NUMERO_LINEA_CREDITO)
                            then <ns2:NUMERO_LINEA_CREDITO>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:NUMERO_LINEA_CREDITO)}</ns2:NUMERO_LINEA_CREDITO>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:DESCRIPCION_LINEA)
                            then <ns2:DESCRIPCION_LINEA>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:DESCRIPCION_LINEA)}</ns2:DESCRIPCION_LINEA>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:ID_FLEXCUBE)
                            then <ns2:ID_FLEXCUBE>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:ID_FLEXCUBE)}</ns2:ID_FLEXCUBE>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:MONTO_LINEA)
                            then <ns2:MONTO_LINEA>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:MONTO_LINEA)}</ns2:MONTO_LINEA>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:ID_CONTRATO_DESEMBOLSO)
                            then <ns2:ID_CONTRATO_DESEMBOLSO>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:ID_CONTRATO_DESEMBOLSO)}</ns2:ID_CONTRATO_DESEMBOLSO>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:CONTRATO_DESEMBOLSO)
                            then <ns2:CONTRATO_DESEMBOLSO>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:CONTRATO_DESEMBOLSO)}</ns2:CONTRATO_DESEMBOLSO>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:ID_RESOLUCION)
                            then <ns2:ID_RESOLUCION>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:ID_RESOLUCION)}</ns2:ID_RESOLUCION>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:RESOLUCION)
                            then <ns2:RESOLUCION>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:RESOLUCION)}</ns2:RESOLUCION>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:FECHA_EFECTIVA)
                            then <ns2:FECHA_EFECTIVA>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:FECHA_EFECTIVA)}</ns2:FECHA_EFECTIVA>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:VENCIMIENTO)
                            then <ns2:VENCIMIENTO>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:VENCIMIENTO)}</ns2:VENCIMIENTO>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:PROXIMO_PAGO)
                            then <ns2:PROXIMO_PAGO>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:PROXIMO_PAGO)}</ns2:PROXIMO_PAGO>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:MONEDA)
                            then <ns2:MONEDA>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:MONEDA)}</ns2:MONEDA>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:MONTO_PREPAGO)
                            then <ns2:MONTO_PREPAGO>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:MONTO_PREPAGO)}</ns2:MONTO_PREPAGO>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:ES_PAGO_TOTAL)
                            then <ns2:ES_PAGO_TOTAL>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:ES_PAGO_TOTAL)}</ns2:ES_PAGO_TOTAL>
                            else ()
                        }
                        <ns2:FONDO_CONTABLE>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:FONDO_CONTABLE)}</ns2:FONDO_CONTABLE>
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:ESCRITURACION)
                            then <ns2:ESCRITURACION>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:ESCRITURACION)}</ns2:ESCRITURACION>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:CAPITAL_NV_FECHA_PREPA)
                            then <ns2:CAPITAL_NV_FECHA_PREPA>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:CAPITAL_NV_FECHA_PREPA)}</ns2:CAPITAL_NV_FECHA_PREPA>
                            else ()
                        }
                        {
                            if ($P_TBLDESEMBOLSOS_ITEM/ns1:USER_REF_NO)
                            then <ns2:USER_REF_NO>{fn:data($P_TBLDESEMBOLSOS_ITEM/ns1:USER_REF_NO)}</ns2:USER_REF_NO>
                            else ()
                        }</ns2:P_TBLDESEMBOLSOS_ITEM>
                }</ns2:P_TBLDESEMBOLSOS>
            else ()
        } 
    </ns2:DetalleContratoDesembolso>
};

local:func($response)