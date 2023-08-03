xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarConfiguracion";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarConfiguracion/V1/Schema/ConsultarConfiguracionMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/ObtenerURLMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ObtenerURL/V1/Schema/ObtenerURLMO.xsd" ::)
declare namespace ns3="http://www.bcie.org/CorreoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarConfiguracionResponse as element() (:: schema-element(ns1:ConsultarConfiguracionResponse) ::) external;
declare variable $ObtenerURLRequest as element() (:: schema-element(ns2:ObtenerURLRequest) ::) external;

declare variable $url := "";
declare variable $valores := "";
declare variable $parametros := "";

declare function local:func($ConsultarConfiguracionResponse as element() (:: schema-element(ns1:ConsultarConfiguracionResponse) ::), 
                            $ObtenerURLRequest as element() (:: schema-element(ns2:ObtenerURLRequest) ::)) 
                            as element() (:: schema-element(ns2:ObtenerURLResponse) ::) {
    <ns2:ObtenerURLResponse>
      <ns2:URL>
        {let $url := fn:concat($ConsultarConfiguracionResponse/ns1:Parametro/com:value,"iDocID=",dvmtr:lookup("MDS/Resources/ComponentesComunes/Common/V1/DVM/ReportesCodes","NombreReporte",$ObtenerURLRequest/ns2:URL,"IdReporte",""),"&amp;")
        return
          let $parametros := fn:string-join(
            for $tag in fn:distinct-values($ObtenerURLRequest/ns2:Tags/ns3:tag)
            return
              if(fn:count($ObtenerURLRequest/ns2:Tags[ns3:tag = $tag]) > 1)then
                fn:concat("lsM",fn:replace($tag,' ', '+'),"=",fn:string-join(
                for $valor in $ObtenerURLRequest/ns2:Tags[ns3:tag = $tag]/ns3:valor
                let $valores := fn:concat($valores,"[",$valor,"]")
                return
                  $valores,","))(:Retorna la URL lsM  con parametros que tienen mas de un valor encerrados en corchetes y separados por una coma:)
              else
                fn:concat("lsS",fn:replace($ObtenerURLRequest/ns2:Tags[ns3:tag = $tag]/ns3:tag,' ','+'),"=",$ObtenerURLRequest/ns2:Tags[ns3:tag = $tag]/ns3:valor)(:Retorna la URL lsS con un parametros de un solo valor:)
          ,"&amp;")
          return
          if($ObtenerURLRequest/ns2:URL = 'PLAN_AMORTIZACION')then
            fn:concat($url,$parametros,'&amp;lsS%3FAgrupar+como+Comision%3F=Si') (:Retorna la URL de PLAN_AMORTIZACION:)
          else
            fn:concat($url,$parametros)(:Retorna la URL de REPORTE_ADEUDOS:)  
      }</ns2:URL>
      <ns2:Resultado>
          <res:result>OK</res:result>
          {if(fn:count($ConsultarConfiguracionResponse/ns1:Parametro) > 1)then
            <res:message>No se ha podido generar la URL</res:message>
          else
            <res:message>URL generada exitosamente</res:message>
          }
      </ns2:Resultado>
    </ns2:ObtenerURLResponse>
};

local:func($ConsultarConfiguracionResponse, $ObtenerURLRequest)
