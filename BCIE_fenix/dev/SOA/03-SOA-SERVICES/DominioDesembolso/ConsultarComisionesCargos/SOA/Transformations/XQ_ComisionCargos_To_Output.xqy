xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $inputVariable.request as element() (:: schema-element(des:ConsultarComisionesCargosRequest) ::) external;
declare variable $outConsultarCargoByProducto.response as element() (:: schema-element(des:ConsultarCargoByProductoResponse) ::) external;
declare variable $outConsultarComisiones.response as element() (:: schema-element(des:ConsultarComisionesResponse) ::) external;

declare function local:funcXq_comisioncargos_to_output($inputVariable.request as element() (:: schema-element(des:ConsultarComisionesCargosRequest) ::), 
                                                       $outConsultarCargoByProducto.response as element() (:: schema-element(des:ConsultarCargoByProductoResponse) ::), 
                                                       $outConsultarComisiones.response as element() (:: schema-element(des:ConsultarComisionesResponse) ::)) 
                                                       as element() (:: schema-element(des:ConsultarComisionesCargosResponse) ::) {
    <des:ConsultarComisionesCargosResponse>
        <des:Desembolso>
            <des1:idDesembolso>{fn:data($inputVariable.request/des:Desembolso/des1:idDesembolso)}</des1:idDesembolso>
            {
                for $Cargo in $outConsultarCargoByProducto.response/des:Cargo
                return 
                <des1:Cargo>
                    {$Cargo/*}
                </des1:Cargo>
            }
            {
                for $Comision in $outConsultarComisiones.response/des:Desembolso/des1:Comision
                return 
                <des1:Comision>
                    {$Comision/*}
                </des1:Comision>
            }
        </des:Desembolso>
        <des:Resultado>
            <res:result></res:result>
            <res:message></res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </des:Resultado>
    </des:ConsultarComisionesCargosResponse>
};

local:funcXq_comisioncargos_to_output($inputVariable.request, $outConsultarCargoByProducto.response, $outConsultarComisiones.response)
