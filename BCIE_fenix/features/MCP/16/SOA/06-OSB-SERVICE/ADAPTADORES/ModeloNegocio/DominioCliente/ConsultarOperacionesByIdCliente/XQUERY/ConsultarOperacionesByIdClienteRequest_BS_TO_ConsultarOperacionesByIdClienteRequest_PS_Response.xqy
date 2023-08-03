xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarOperacionesByIdCliente";
(:: import schema at "../XSD/ConsultarOperacionesByIdCliente.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ConsultarOperacionesByIdClienteOutputCollection as element() (:: schema-element(ns1:ConsultarOperacionesByIdClienteOutputCollection) ::) external;

declare function local:func($ConsultarOperacionesByIdClienteOutputCollection as element() (:: schema-element(ns1:ConsultarOperacionesByIdClienteOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarOperacionesByIdClienteResponse) ::) {
    <ns2:ConsultarOperacionesByIdClienteResponse>
    {
    for $Operacion in $ConsultarOperacionesByIdClienteOutputCollection/ns1:ConsultarOperacionesByIdClienteOutput
    return
        <ns2:operaciones>
            <cli:idOperacion>{fn:data($Operacion/ns1:ID_OPERACION)}</cli:idOperacion>
            <cli:nombre>{fn:data($Operacion/ns1:NOMBRE)}</cli:nombre>
            <cli:cliente>{fn:data($Operacion/ns1:RAZON_SOCIAL)}</cli:cliente>
            <cli:pais>{fn:data($Operacion/ns1:PAIS)}</cli:pais>
            <cli:producto>{fn:data($Operacion/ns1:PRODUCTO)}</cli:producto>
            <cli:responsable>{fn:data($Operacion/ns1:RESPONSABLE)}</cli:responsable>
            <cli:etapa>{fn:data($Operacion/ns1:ETAPA)}</cli:etapa>
            <cli:estado>{fn:data($Operacion/ns1:ESTADO)}</cli:estado>
            <cli:SCR>
                <cat:Id>{fn:data($Operacion/ns1:SCR)}</cat:Id>
                <cat:Descripcion>{fn:data($Operacion/ns1:DESCRIPCION_SCR)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($Operacion/ns1:DESCRIPCION_CORTA_SCR)}</cat:DescripcionCorta>
                  <cat:estatus>{
                       if ($Operacion/ns1:SCR[1])
                      then (fn:true())
                      else(fn:false())
                 }
                  </cat:estatus>              
                <cat:codigoExterno>{fn:data($Operacion/ns1:COD_EXTERNO_SCR)}</cat:codigoExterno>
            </cli:SCR>
            <cli:perspectiva>
                <cat:Id>{fn:data($Operacion/ns1:PERSPECTIVA)}</cat:Id>
                <cat:Descripcion>{fn:data($Operacion/ns1:DESCRIPCION_PERSPECTIVA)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($Operacion/ns1:DESCRIPCION_CORTA_PERSPECTIVA)}</cat:DescripcionCorta>
                 <cat:estatus>{
                     if ($Operacion/ns1:PERSPECTIVA[1])
                     then (fn:true())
                     else(fn:false())
                      }
                  </cat:estatus>  
                <cat:codigoExterno>{fn:data($Operacion/ns1:COD_EXTERNO_PERSPECTIVA)}</cat:codigoExterno>
            </cli:perspectiva>
        </ns2:operaciones>
    }
    </ns2:ConsultarOperacionesByIdClienteResponse>
};

local:func($ConsultarOperacionesByIdClienteOutputCollection)
