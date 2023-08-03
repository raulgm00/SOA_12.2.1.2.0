xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearActualizarBitacoraDocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearActualizarBitacoraDocumento/V1/Schema/CrearActualizarBitacoraDocumentoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearActualizarBitacoraDocumento";
(:: import schema at "../XSD/CrearActualizarBitacoraDocumento_sp.xsd" ::)

declare variable $request as element() (:: schema-element(ns1:requestCrearActualizarBitacoraDocumentoMessage) ::) external;

declare function local:func($request as element() (:: schema-element(ns1:requestCrearActualizarBitacoraDocumentoMessage) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
  <ns2:InputParameters>
      <ns2:T_TBI_DOCUMENTO>
           {
            if(fn:string(fn:data($request/ns1:BitacoraDocumentoInput/id))!='')then
                  <ns2:ID>{fn:data($request/ns1:BitacoraDocumentoInput/id)}</ns2:ID>
              else()
          }
          <ns2:ID_DOCUMENTO>{fn:data($request/ns1:BitacoraDocumentoInput/idDocumento)}</ns2:ID_DOCUMENTO>
          <ns2:ID_ADJUNTO></ns2:ID_ADJUNTO>
          <ns2:ESTADO>{fn:data($request/ns1:BitacoraDocumentoInput/estado)}</ns2:ESTADO>
          <ns2:NUM_INSTANCIA>{fn:data($request/ns1:BitacoraDocumentoInput/instanciaProceso)}</ns2:NUM_INSTANCIA>
          <ns2:SE_HA_NOTIFICADO>{
           if(fn:string(fn:data($request/ns1:BitacoraDocumentoInput/seNotifico))='true')then '1'
              else if (fn:string(fn:data($request/ns1:BitacoraDocumentoInput/seNotifico))='true')then'0'
              else('0')
          }</ns2:SE_HA_NOTIFICADO>
          <ns2:LOGIN_USUARIO>{fn:data($request/ns1:BitacoraDocumentoInput/usuario)}</ns2:LOGIN_USUARIO>
          <ns2:NOMBRE_USUARIO></ns2:NOMBRE_USUARIO>
          <ns2:ID_TCA_TAREA_BPM>{fn:data($request/ns1:BitacoraDocumentoInput/idTarea)}</ns2:ID_TCA_TAREA_BPM>
          <ns2:BAN_ESTATUS>{
          if(fn:string(fn:data($request/ns1:BitacoraDocumentoInput/estatus))='true')then '1'
              else if (fn:string(fn:data($request/ns1:BitacoraDocumentoInput/estatus))='true')then'0'
              else('0')
          }</ns2:BAN_ESTATUS>
          <ns2:FECHA_REGISTRO>{fn:data($request/ns1:BitacoraDocumentoInput/fechaRegistro)}</ns2:FECHA_REGISTRO>
          <ns2:DESCRIPCION>{fn:data($request/ns1:BitacoraDocumentoInput/descripcion)}</ns2:DESCRIPCION>
          <ns2:ES_REG_ACTIVO>{
          if(fn:string(fn:data($request/ns1:BitacoraDocumentoInput/registroActivo))='true')
          then '1'
          else '0'
          }</ns2:ES_REG_ACTIVO>
      </ns2:T_TBI_DOCUMENTO>
  </ns2:InputParameters> 
};

local:func($request)
