xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CalculoInteresPrepago";
(:: import schema at "../XSD/CalculoInteresPrepago_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $response as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($response as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CalculoInteresPrepagoResponse) ::) {
    <ns2:CalculoInteresPrepagoResponse>
        {
            if ($response/ns1:P_ESTADO)
            then <ns2:P_ESTADO>{fn:data($response/ns1:P_ESTADO)}</ns2:P_ESTADO>
            else ()
        }
        {
            if ($response/ns1:P_CODIGO)
            then <ns2:P_CODIGO>{fn:data($response/ns1:P_CODIGO)}</ns2:P_CODIGO>
            else ()
        }
        {
            if ($response/ns1:P_MENSAJE)
            then <ns2:P_MENSAJE>{fn:data($response/ns1:P_MENSAJE)}</ns2:P_MENSAJE>
            else ()
        }
        <ns2:P_TBLINTERESES>
            {
                for $P_TBLINTERESES_ITEM in $response/ns1:P_TBLINTERESES/ns1:P_TBLINTERESES_ITEM
                return 
                <ns2:P_TBLINTERESES_ITEM>
                    {
                        if ($P_TBLINTERESES_ITEM/ns1:CONTRACT_DESEMBOLSO)
                        then <ns2:CONTRACT_DESEMBOLSO>{fn:data($P_TBLINTERESES_ITEM/ns1:CONTRACT_DESEMBOLSO)}</ns2:CONTRACT_DESEMBOLSO>
                        else ()
                    }
                    {
                        if ($P_TBLINTERESES_ITEM/ns1:BASE)
                        then <ns2:BASE>{fn:data($P_TBLINTERESES_ITEM/ns1:BASE)}</ns2:BASE>
                        else ()
                    }                   
                    {
                        if ($P_TBLINTERESES_ITEM/ns1:TASA)
                        then <ns2:TASA>{fn:data($P_TBLINTERESES_ITEM/ns1:TASA)}</ns2:TASA>
                        else ()
                    }
                    {
                        if ($P_TBLINTERESES_ITEM/ns1:MONTO_PREPAGAR)
                        then <ns2:MONTO_PREPAGAR>{fn:data($P_TBLINTERESES_ITEM/ns1:MONTO_PREPAGAR)}</ns2:MONTO_PREPAGAR>
                        else ()
                    }
                    {
                        if ($P_TBLINTERESES_ITEM/ns1:FECHA_DESDE)
                        then <ns2:FECHA_DESDE>{fn:data($P_TBLINTERESES_ITEM/ns1:FECHA_DESDE)}</ns2:FECHA_DESDE>
                        else ()
                    }
                    {
                        if ($P_TBLINTERESES_ITEM/ns1:FECHA_HASTA)
                        then <ns2:FECHA_HASTA>{fn:data($P_TBLINTERESES_ITEM/ns1:FECHA_HASTA)}</ns2:FECHA_HASTA>
                        else ()
                    }
                    {
                        if ($P_TBLINTERESES_ITEM/ns1:DIAS_CALCULADOS)
                        then <ns2:DIAS_CALCULADOS>{fn:data($P_TBLINTERESES_ITEM/ns1:DIAS_CALCULADOS)}</ns2:DIAS_CALCULADOS>
                        else ()
                    }
                    {
                        if ($P_TBLINTERESES_ITEM/ns1:MONEDA)
                        then <ns2:MONEDA>{fn:data($P_TBLINTERESES_ITEM/ns1:MONEDA)}</ns2:MONEDA>
                        else ()
                    }
                    {
                        if ($P_TBLINTERESES_ITEM/ns1:INTERESES)
                        then <ns2:INTERESES>{fn:data($P_TBLINTERESES_ITEM/ns1:INTERESES)}</ns2:INTERESES>
                        else ()
                    }
                    {
                        if ($P_TBLINTERESES_ITEM/ns1:ID_TRE_PRE_CONT)
                        then <ns2:ID_TRE_PRE_CONT>{fn:data($P_TBLINTERESES_ITEM/ns1:ID_TRE_PRE_CONT)}</ns2:ID_TRE_PRE_CONT>
                        else ()
                    }
                    {
                        if ($P_TBLINTERESES_ITEM/ns1:ES_PAGO_TOTAL)
                        then <ns2:ES_PAGO_TOTAL>{fn:data($P_TBLINTERESES_ITEM/ns1:ES_PAGO_TOTAL)}</ns2:ES_PAGO_TOTAL>
                        else ()
                    }
                    {
                        if ($P_TBLINTERESES_ITEM/ns1:FECHA_PROXIMO_PAGO)
                        then <ns2:FECHA_PROXIMO_PAGO>{fn:data($P_TBLINTERESES_ITEM/ns1:FECHA_PROXIMO_PAGO)}</ns2:FECHA_PROXIMO_PAGO>
                        else ()
                    }
                </ns2:P_TBLINTERESES_ITEM>
            }
        </ns2:P_TBLINTERESES>
        <ns2:Resultado>
            <res:result></res:result>
            <res:message></res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:CalculoInteresPrepagoResponse>
};

local:func($response)