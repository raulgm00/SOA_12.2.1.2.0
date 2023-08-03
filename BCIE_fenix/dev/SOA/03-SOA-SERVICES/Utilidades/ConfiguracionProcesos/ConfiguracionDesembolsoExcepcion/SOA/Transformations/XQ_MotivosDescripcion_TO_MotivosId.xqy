xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $inputVariable.request as element() (:: schema-element(con:ConfiguracionDesembolsoExcepcionRequest) ::) external;

declare function local:funcXq_motivosdescripcion_to_motivosid($inputVariable.request as element() (:: schema-element(con:ConfiguracionDesembolsoExcepcionRequest) ::)) as element() (:: schema-element(con:ConfiguracionDesembolsoExcepcionRequest) ::) {
    <con:ConfiguracionDesembolsoExcepcionRequest>
        <con:idSolicitud>{fn:data($inputVariable.request/con:idSolicitud)}</con:idSolicitud>
        <con:idOperacion>{fn:data($inputVariable.request/con:idOperacion)}</con:idOperacion>
        <con:instanciaProceso>{fn:data($inputVariable.request/con:instanciaProceso)}</con:instanciaProceso>
        {
        for $param in $inputVariable.request/con:Parameters
        return
        if ($param/com:name = 'MOTIVO') then
        
          if ($param/com:value = 'MORA') then
         <con:Parameters>
              <com:name>{fn:data($param/com:name)}</com:name>
              <com:value>1</com:value>
          </con:Parameters>
        
        else if ($param/com:value = 'SCR') then   
        <con:Parameters>
              <com:name>{fn:data($param/com:name)}</com:name>
              <com:value>2</com:value>
          </con:Parameters>
        
          else if ($param/com:value = 'LIMITES') then   
        <con:Parameters>
              <com:name>{fn:data($param/com:name)}</com:name>
              <com:value>6</com:value>
          </con:Parameters>
        else
        ()
      else 
        <con:Parameters>
          <com:name>{fn:data($param/com:name)}</com:name>
          <com:value>{fn:data($param/com:value)}</com:value>
        </con:Parameters>
        }
    </con:ConfiguracionDesembolsoExcepcionRequest>
};

local:funcXq_motivosdescripcion_to_motivosid($inputVariable.request)
