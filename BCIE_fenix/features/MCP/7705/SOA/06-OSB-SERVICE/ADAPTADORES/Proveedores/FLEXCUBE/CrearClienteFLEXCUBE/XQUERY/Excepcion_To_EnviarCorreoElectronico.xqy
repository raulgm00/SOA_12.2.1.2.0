xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)


declare namespace ns1="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare variable $MensajeDeError as xs:string external;

declare variable $Accion as xs:string external;

declare function tns:func($MensajeDeError as xs:normalizedString, $Accion as xs:string) 
                          as element() (:: schema-element(ns1:enviarCorreoElectronicoRequest) ::) {
    <ns1:enviarCorreoElectronicoRequest>
        <ns1:CorreoElectronico>
            <cor:id_plantilla>104</cor:id_plantilla>
            <cor:parametros>
                <cor:tag>TIPO_TAREA</cor:tag>
                <cor:valor>{fn:data($Accion)}</cor:valor>
            </cor:parametros>
            <cor:parametros>
                <cor:tag>DESCRIPCION_ERROR</cor:tag> 
                <cor:valor>{fn:replace(fn:substring(string(fn:data($MensajeDeError)),1,2000),"'", " ")}</cor:valor>
            </cor:parametros>
            <cor:parametros>
                <cor:tag>FECHA_ERROR</cor:tag> 
                <cor:valor>{fn:substring(string(fn:current-dateTime()),1,19)}</cor:valor>
            </cor:parametros>
             <!--cor:parametros>
             declare variable $Parametros as xs:string external; esta va en la declaracion
             <cor:tag>LISTA_PARAMETROS</cor:tag>    
             <cor:valor>{fn:data($Parametros)}</cor:valor>
            </cor:parametros-->
        </ns1:CorreoElectronico>
    </ns1:enviarCorreoElectronicoRequest>
};

tns:func($MensajeDeError,$Accion)
