xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarRolByPlantilla";
(:: import schema at "../XSD/ConsultarRolByPlantilla.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarRolByPlantillaOutputCollection as element() (:: schema-element(ns1:ConsultarRolByPlantillaOutputCollection) ::) external;

declare function local:func($ConsultarRolByPlantillaOutputCollection as element() (:: schema-element(ns1:ConsultarRolByPlantillaOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultaRolByPlantillaResponse) ::) {
    <ns2:ConsultaRolByPlantillaResponse>
        <ns2:ListaRoles>
                      {
                    for $listadoRoles in $ConsultarRolByPlantillaOutputCollection/ns1:ConsultarRolByPlantillaOutput
                    return 
                    <cor:Roles>
                        <cat:Id>{fn:data($listadoRoles/ns1:ID)}</cat:Id>
                          <cat:Descripcion>{fn:data($listadoRoles/ns1:DESCRIPCION)}</cat:Descripcion>
                          <cat:DescripcionCorta>{fn:data($listadoRoles/ns1:DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                          <cat:estatus>{if (string($listadoRoles/ns1:BAN_ESTATUS)='') then false()   else 
            if(fn:data($listadoRoles/ns1:BAN_ESTATUS)=1) then true() else false ()}
                          </cat:estatus>
                          <cat:codigoExterno>{fn:data($listadoRoles/ns1:COD_EXTERNO)}</cat:codigoExterno>
                    </cor:Roles>
                }
        </ns2:ListaRoles>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Operacion Exitosa</res:message>
        </ns2:Resultado>
    </ns2:ConsultaRolByPlantillaResponse>
};

local:func($ConsultarRolByPlantillaOutputCollection)
