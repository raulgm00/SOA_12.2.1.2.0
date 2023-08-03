xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";


declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarDetalleTransaccionNRequest as element() (:: schema-element(ns1:ConsultarDetalleTransaccionNRequest) ::) external;
declare variable $counter as xs:int external;
declare variable $ConsultarDetalleTransaccionResponse as element() (:: schema-element(ns1:ConsultarDetalleTransaccionResponse) ::) external;

declare function local:func($ConsultarDetalleTransaccionResponse as element() (:: schema-element(ns1:ConsultarDetalleTransaccionResponse) ::),$ConsultarDetalleTransaccionNRequest as element() (:: schema-element(ns1:ConsultarDetalleTransaccionNRequest) ::), $counter as xs:int) as element() (:: schema-element(ns1:ConsultarDetalleTransaccionNResponse) ::) {
    <ns1:ConsultarDetalleTransaccionNResponse>
        <ns1:TransaccionDesembolso>
            <des:idDesembolso>{fn:data($ConsultarDetalleTransaccionNRequest/ns1:idDesembolso[$counter])}</des:idDesembolso>
            {
                for $TransaccionDesembolso in $ConsultarDetalleTransaccionResponse/ns1:TransaccionDesembolso
                return 
                <des:TransaccionDetalle>
                    <des:id>{fn:data($TransaccionDesembolso/des:id)}</des:id>
                    <des:idDesembolso>{fn:data($TransaccionDesembolso/des:idDesembolso)}</des:idDesembolso>
                    <des:plataforma>{fn:data($TransaccionDesembolso/des:plataforma)}</des:plataforma>
                    <des:operacion>{fn:data($TransaccionDesembolso/des:operacion)}</des:operacion>
                    {
                        if ($TransaccionDesembolso/des:fechaRegistro)
                        then <des:fechaRegistro>{fn:data($TransaccionDesembolso/des:fechaRegistro)}</des:fechaRegistro>
                        else ()
                    }
                    <des:estatus>{fn:data($TransaccionDesembolso/des:estatus)}</des:estatus>
                    <des:detalleTransaccion>
                        {
                            for $Parameters in $TransaccionDesembolso/des:detalleTransaccion/des:Parameters
                            return 
                            <des:Parameters>
                                <com:name>{fn:data($Parameters/com:name)}</com:name>
                                <com:value>{fn:data($Parameters/com:value)}</com:value>
                                <com:id>{fn:data($Parameters/com:id)}</com:id>
                                <com:agrupador>{fn:data($Parameters/com:agrupador)}</com:agrupador>
                            </des:Parameters>
                        }
                    </des:detalleTransaccion>
                </des:TransaccionDetalle>
            }
        </ns1:TransaccionDesembolso>
    </ns1:ConsultarDetalleTransaccionNResponse>
};

local:func($ConsultarDetalleTransaccionResponse,$ConsultarDetalleTransaccionNRequest,$counter)
