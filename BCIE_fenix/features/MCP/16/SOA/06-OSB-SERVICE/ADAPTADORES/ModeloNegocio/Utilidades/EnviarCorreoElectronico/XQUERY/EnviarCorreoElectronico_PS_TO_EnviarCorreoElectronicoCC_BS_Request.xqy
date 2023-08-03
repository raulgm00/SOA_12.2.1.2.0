xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare variable $enviarCorreoElectronicoRequest as element() (:: schema-element(ns1:enviarCorreoElectronicoRequest) ::) external;

declare function local:func($enviarCorreoElectronicoRequest as element() (:: schema-element(ns1:enviarCorreoElectronicoRequest) ::)) as element() (:: schema-element(ns1:enviarCorreoElectronicoRequest) ::) {
    <ns1:enviarCorreoElectronicoRequest>
        <ns1:CorreoElectronico>
            <cor:cc>
            <cor:usuario>
           {
           for $mail at $pos in $enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:cc/cor:usuario
           return
             if ($pos = count($enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:cc/cor:usuario))
             then
              string($mail)
            else
                 concat($mail, ',')
           }
            </cor:usuario>
          </cor:cc>
        </ns1:CorreoElectronico>
    </ns1:enviarCorreoElectronicoRequest>
};

local:func($enviarCorreoElectronicoRequest)
