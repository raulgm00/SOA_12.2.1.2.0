xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarCondiciones";
(:: import schema at "../XSD/ValidarCondiciones_sp.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ValidarCondicionRequest as element() (:: schema-element(ns1:ValidarCondicionRequest) ::) external;

declare function local:func($ValidarCondicionRequest as element() (:: schema-element(ns1:ValidarCondicionRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_CONDICION>{fn:data($ValidarCondicionRequest/ns1:ValidacionCondicion/con:idCondicion)}</ns2:P_CONDICION>
       
             <ns2:P_OBSERVACION>{fn:data($ValidarCondicionRequest/ns1:ValidacionCondicion/con:observacion/con:observacion)}</ns2:P_OBSERVACION>
             <ns2:P_LOGIN_USUARIO>{fn:lower-case(fn:data($ValidarCondicionRequest/ns1:ValidacionCondicion/con:loginUsuario))}</ns2:P_LOGIN_USUARIO>
            <ns2:P_NOMBRE_USUARIO>{fn:data($ValidarCondicionRequest/ns1:ValidacionCondicion/con:nombreUsuario)}</ns2:P_NOMBRE_USUARIO>
            <ns2:P_FECHA_REGISTRO>{fn:current-dateTime()}</ns2:P_FECHA_REGISTRO>
           <ns2:P_BAN_ESTATUS>{if (fn:data($ValidarCondicionRequest/ns1:ValidacionCondicion/con:observacion/con:estatus)=true()) then 1
           else 0
           }</ns2:P_BAN_ESTATUS>
           <ns2:P_ID_TCA_TAREA_BPM>{fn:data($ValidarCondicionRequest/ns1:ValidacionCondicion/con:observacion/con:tareaBPM/cat:Id)}</ns2:P_ID_TCA_TAREA_BPM>
         <ns2:P_ES_PRINCIPAL>{ if (fn:data($ValidarCondicionRequest/ns1:ValidacionCondicion/con:observacion/con:esPrincipal)=true()) then 1 
         else 0         
         }</ns2:P_ES_PRINCIPAL>
           <ns2:P_ROL>{if(       string($ValidarCondicionRequest/ns1:ValidacionCondicion/con:rolBPM/cat:Id)='0')
           then () else
           fn:data($ValidarCondicionRequest/ns1:ValidacionCondicion/con:rolBPM/cat:Id)
           }</ns2:P_ROL>
            <ns2:P_ESTADO>{ if (fn:data($ValidarCondicionRequest/ns1:ValidacionCondicion/con:estado) =true() )then 1 else 0
            }</ns2:P_ESTADO>
             <ns2:P_ES_VALIDADOR>{ if(             fn:data($ValidarCondicionRequest/ns1:ValidacionCondicion/con:esValidador)=true()) then 1
             else 0
             }</ns2:P_ES_VALIDADOR>
        <ns2:P_AGRUPADOR>{fn:data($ValidarCondicionRequest/ns1:Agrupador)}</ns2:P_AGRUPADOR>
       </ns2:InputParameters>
};

local:func($ValidarCondicionRequest)
