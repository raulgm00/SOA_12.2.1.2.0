xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConstruirCorreoElectronico";
(:: import schema at "../XSD/ConstruirCorreoElectronico_sp.xsd" ::)

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare variable $ConstruirCorreoElectronicoRequest as element() (:: schema-element(ns1:ConstruirCorreoElectronicoRequest) ::) external;

declare function local:func($ConstruirCorreoElectronicoRequest as element() (:: schema-element(ns1:ConstruirCorreoElectronicoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_ID_PLATILLA>{fn:data($ConstruirCorreoElectronicoRequest/ns1:CorreoElectronico/cor:id_plantilla)}</ns2:P_ID_PLATILLA>
        <ns2:P_ID_OPERACION>{fn:data($ConstruirCorreoElectronicoRequest/ns1:CorreoElectronico/cor:idOperacion)}</ns2:P_ID_OPERACION>
        <ns2:P_ID_CLIENTE>{fn:data($ConstruirCorreoElectronicoRequest/ns1:CorreoElectronico/cor:idClienteFenix)}</ns2:P_ID_CLIENTE>
        <ns2:P_TAGS>
        {
        for $parametros in $ConstruirCorreoElectronicoRequest/ns1:CorreoElectronico/cor:parametros
        return
            <ns2:P_TAGS_ITEM>
               <ns2:TAG>{fn:data($parametros/cor:tag)}</ns2:TAG>
               <ns2:VALOR>{fn:data($parametros/cor:valor)}</ns2:VALOR>
            </ns2:P_TAGS_ITEM>
        }
       </ns2:P_TAGS>
       <ns2:P_ASUNTO>{fn:data($ConstruirCorreoElectronicoRequest/ns1:CorreoElectronico/cor:subject)}</ns2:P_ASUNTO>
       <ns2:P_MENSAJE>{fn:data($ConstruirCorreoElectronicoRequest/ns1:CorreoElectronico/cor:mensaje)}</ns2:P_MENSAJE>
       <ns2:P_DESTINATARIOS>{fn:data($ConstruirCorreoElectronicoRequest/ns1:CorreoElectronico/cor:to/cor:usuario)}</ns2:P_DESTINATARIOS>
       <ns2:P_CC>{fn:data($ConstruirCorreoElectronicoRequest/ns1:CorreoElectronico/cor:cc/cor:usuario)}</ns2:P_CC>
    </ns2:InputParameters>
};

local:func($ConstruirCorreoElectronicoRequest)
