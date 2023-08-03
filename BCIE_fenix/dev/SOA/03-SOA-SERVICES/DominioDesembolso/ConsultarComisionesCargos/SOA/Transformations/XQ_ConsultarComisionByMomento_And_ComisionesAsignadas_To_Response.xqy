xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace com="http://www.bcie.org/ComisionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace com2 = "http://www.bcie.org/ComisionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outConsultarComisionesAsignadas.response as element() (:: schema-element(des:ConsultarComisionesAsignadasResponse) ::) external;
declare variable $outComisionServiceTotal.response as element() (:: schema-element(com:ConsultarComisionResponse) ::) external;
declare variable $inputVariable.request as element() (:: schema-element(des:ConsultarComisionesRequest) ::) external;

declare function local:funcXq_consultarcomisionbymomento_and_comisionesasignadas_to_response($outConsultarComisionesAsignadas.response as element() (:: schema-element(des:ConsultarComisionesAsignadasResponse) ::), 
                                                                                             $outComisionServiceTotal.response as element() (:: schema-element(com:ConsultarComisionResponse) ::), 
                                                                                             $inputVariable.request as element() (:: schema-element(des:ConsultarComisionesRequest) ::)) 
                                                                                             as element() (:: schema-element(des:ConsultarComisionesResponse) ::) {
    <des:ConsultarComisionesResponse>
      {
        for $desembolsoBueno in $outConsultarComisionesAsignadas.response/des:Desembolso[des1:idDesembolso = $inputVariable.request/des:Desembolso/des1:idDesembolso]
        return
            <des:Desembolso>
            <des1:idDesembolso>{fn:data($desembolsoBueno/des1:idDesembolso)}</des1:idDesembolso>
            <des1:idFacturador>{fn:data($desembolsoBueno/des1:idFacturador)}</des1:idFacturador>
            <des1:producto>{$desembolsoBueno/des1:producto/*}</des1:producto>
            <des1:referencia>{fn:data($desembolsoBueno/des1:referencia)}</des1:referencia>
            <des1:monto>{$desembolsoBueno/des1:monto/*}</des1:monto>
            <des1:estado>{$desembolsoBueno/des1:estado/*}</des1:estado>
            <des1:idLinea>{fn:data($desembolsoBueno/des1:idLinea)}</des1:idLinea>
            {
            for $comision in $outComisionServiceTotal.response/com:Comision
            where $comision/com2:estadoTCC/atr:id != 6 and $comision/com2:estadoTCC/atr:id != 5 and string-length($comision/com2:codigoContrato/text()) > 0
            return
            if (not(count($outConsultarComisionesAsignadas.response/des:Desembolso[des1:idDesembolso != $inputVariable.request/des:Desembolso/des1:idDesembolso]/des1:Comision[com2:idComision = $comision/com2:idComision]) > 0))
              then
                <des1:Comision>
                  {
                    $comision/*
                  }
                </des1:Comision>
              else ()
            }
            </des:Desembolso>
        }
        <des:Resultado>
            <res:result></res:result>
            <res:message></res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </des:Resultado>
    </des:ConsultarComisionesResponse>
};

local:funcXq_consultarcomisionbymomento_and_comisionesasignadas_to_response($outConsultarComisionesAsignadas.response, $outComisionServiceTotal.response, $inputVariable.request)
