xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SeguridadMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioSeguridad/Schema/SeguridadMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/obtienePermisosUsuario";
(:: import schema at "../XSD/obtienePermisosUsuario_sp.xsd" ::)

declare variable $response as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($response as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:obtienePermisosUsuarioResponse) ::) {
    <ns2:obtienePermisosUsuarioResponse>
        {
            if ($response/ns1:PVCODIGO)
            then <ns2:PVCODIGO>{fn:data($response/ns1:PVCODIGO)}</ns2:PVCODIGO>
            else ()
        }
        {
            if ($response/ns1:PVMENSAJE)
            then <ns2:PVMENSAJE>{fn:data($response/ns1:PVMENSAJE)}</ns2:PVMENSAJE>
            else ()
        }
        <ns2:PLPERMISOS>
            {
                for $PLPERMISOS_ITEM in $response/ns1:PLPERMISOS/ns1:PLPERMISOS_ITEM
                return 
                <ns2:PLPERMISOS_ITEM>
                    <ns2:APLICACION>{fn:data($PLPERMISOS_ITEM/ns1:APLICACION)}</ns2:APLICACION>
                    <ns2:ROL>{fn:data($PLPERMISOS_ITEM/ns1:ROL)}</ns2:ROL>
                    <ns2:VENTANA>{fn:data($PLPERMISOS_ITEM/ns1:VENTANA)}</ns2:VENTANA>
                    <ns2:CONTROL>{fn:data($PLPERMISOS_ITEM/ns1:CONTROL)}</ns2:CONTROL>
                    <ns2:DESCRIPCION>{fn:data($PLPERMISOS_ITEM/ns1:DESCRIPCION)}</ns2:DESCRIPCION>
                    <ns2:TIPO>{fn:data($PLPERMISOS_ITEM/ns1:TIPO)}</ns2:TIPO></ns2:PLPERMISOS_ITEM>
            }</ns2:PLPERMISOS>
    </ns2:obtienePermisosUsuarioResponse>
};

local:func($response)
