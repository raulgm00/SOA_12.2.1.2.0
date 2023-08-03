xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ObtenerRolesUsuarioByPlantilla";
(:: import schema at "../XSD/ObtenerRolesUsuariosByPlantilla_sp.xsd" ::)

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare variable $ObtenerRolesUsuariosByPlantillaRequest as element() (:: schema-element(ns1:ObtenerRolesUsuariosByPlantillaRequest) ::) external;

declare function local:func($ObtenerRolesUsuariosByPlantillaRequest as element() (:: schema-element(ns1:ObtenerRolesUsuariosByPlantillaRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_DESCRIPCION_PLANTILLA>{fn:data($ObtenerRolesUsuariosByPlantillaRequest/ns1:descripcionPlantilla)}</ns2:P_DESCRIPCION_PLANTILLA>
        {
            if ($ObtenerRolesUsuariosByPlantillaRequest/ns1:idOperacion)
            then <ns2:P_ID_OPERACION>{fn:data($ObtenerRolesUsuariosByPlantillaRequest/ns1:idOperacion)}</ns2:P_ID_OPERACION>
            else ()
        }
        {
            if ($ObtenerRolesUsuariosByPlantillaRequest/ns1:idCliente)
            then <ns2:P_ID_CLIENTE>{fn:data($ObtenerRolesUsuariosByPlantillaRequest/ns1:idCliente)}</ns2:P_ID_CLIENTE>
            else ()
        }
        {
            if ($ObtenerRolesUsuariosByPlantillaRequest/ns1:idProceso)
            then <ns2:P_ID_PROCESO>{fn:data($ObtenerRolesUsuariosByPlantillaRequest/ns1:idProceso)}</ns2:P_ID_PROCESO>
            else ()
        }
        <ns2:P_BANDERAS>
        {
        for $bandera in $ObtenerRolesUsuariosByPlantillaRequest/ns1:banderas
        return
            <ns2:P_BANDERAS_ITEM>
                {
                    if ($bandera/cor:tag)
                    then <ns2:TAG>{fn:data($bandera/cor:tag)}</ns2:TAG>
                    else ()
                }
                {
                    if ($bandera/cor:valor)
                    then <ns2:VALOR>{fn:data($bandera/cor:valor)}</ns2:VALOR>
                    else ()
                }
            </ns2:P_BANDERAS_ITEM>
            }
        </ns2:P_BANDERAS>
        <ns2:P_ACCIONES>
        {
        for $accion in $ObtenerRolesUsuariosByPlantillaRequest/ns1:accciones
        return
            <ns2:P_BANDERAS_ITEM>
                {
                    if ($accion/cor:tag)
                    then <ns2:TAG>{fn:data($accion/cor:tag)}</ns2:TAG>
                    else ()
                }
                {
                    if ($accion/cor:valor)
                    then <ns2:VALOR>{fn:data($accion/cor:valor)}</ns2:VALOR>
                    else ()
                }
            </ns2:P_BANDERAS_ITEM>
        }
        </ns2:P_ACCIONES>

    </ns2:InputParameters>
};

local:func($ObtenerRolesUsuariosByPlantillaRequest)
