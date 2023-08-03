xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $outConsultarDetalleTransaccion.response as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::) external;

declare function local:funcXqaplicarcompensaciondesembolsorequest($outConsultarDetalleTransaccion.response as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::)) as element() (:: schema-element(des:AplicarCompensacionDesembolsoRequest) ::) {
    <des:AplicarCompensacionDesembolsoRequest>
        <des:idDesembolso>{fn:data($outConsultarDetalleTransaccion.response/des:TransaccionDesembolso/des1:idDesembolso)}</des:idDesembolso>
        <des:plataforma>ARE</des:plataforma>
        <des:DetalleTransaccion>
            {
                for $Parameters in $outConsultarDetalleTransaccion.response/des:TransaccionDesembolso/des1:detalleTransaccion/des1:Parameters
                return 
                <des1:Parameters>
                    <com:name>{fn:data($Parameters/com:name)}</com:name>
                    <com:value>{fn:data($Parameters/com:value)}</com:value>
                </des1:Parameters>
            }
        </des:DetalleTransaccion>
    </des:AplicarCompensacionDesembolsoRequest>
};

local:funcXqaplicarcompensaciondesembolsorequest($outConsultarDetalleTransaccion.response)
