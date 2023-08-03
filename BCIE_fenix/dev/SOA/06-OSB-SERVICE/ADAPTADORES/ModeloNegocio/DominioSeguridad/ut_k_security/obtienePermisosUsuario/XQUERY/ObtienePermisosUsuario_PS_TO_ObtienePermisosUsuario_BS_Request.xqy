xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SeguridadMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioSeguridad/Schema/SeguridadMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/obtienePermisosUsuario";
(:: import schema at "../XSD/obtienePermisosUsuario_sp.xsd" ::)

declare variable $request as element() (:: schema-element(ns1:obtienePermisosUsuarioRequest) ::) external;

declare function local:func($request as element() (:: schema-element(ns1:obtienePermisosUsuarioRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            if ($request/ns1:PVUSER)
            then <ns2:PVUSER>{fn:data($request/ns1:PVUSER)}</ns2:PVUSER>
            else ()
        }
        {
            if ($request/ns1:PVAPPLICATION)
            then <ns2:PVAPPLICATION>{fn:data($request/ns1:PVAPPLICATION)}</ns2:PVAPPLICATION>
            else ()
        }
        {
            if ($request/ns1:PVWINDOW)
            then <ns2:PVWINDOW>{fn:data($request/ns1:PVWINDOW)}</ns2:PVWINDOW>
            else ()
        }
        {
            if ($request/ns1:PVCONTROL)
            then <ns2:PVCONTROL>{fn:data($request/ns1:PVCONTROL)}</ns2:PVCONTROL>
            else ()
        }
    </ns2:InputParameters>
};

local:func($request)
