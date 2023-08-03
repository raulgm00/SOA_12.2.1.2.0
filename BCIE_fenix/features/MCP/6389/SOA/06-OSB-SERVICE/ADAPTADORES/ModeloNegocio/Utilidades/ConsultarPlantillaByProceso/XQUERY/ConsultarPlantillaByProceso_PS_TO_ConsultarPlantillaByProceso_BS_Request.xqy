xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarPlantillaByProceso";
(:: import schema at "../XSD/ConsultarPlantillaByProceso.xsd" ::)

declare variable $ConsultarPlantillaByProcesoRequest as element() (:: schema-element(ns1:ConsultarPlantillaByProcesoRequest) ::) external;

declare function local:func($ConsultarPlantillaByProcesoRequest as element() (:: schema-element(ns1:ConsultarPlantillaByProcesoRequest) ::)) as element() (:: schema-element(ns2:ConsultarPlantillaByProcesoInput) ::) {
    <ns2:ConsultarPlantillaByProcesoInput>
        {
            if ($ConsultarPlantillaByProcesoRequest/ns1:idPlantilla)
            then <ns2:idPlantilla>{fn:data($ConsultarPlantillaByProcesoRequest/ns1:idPlantilla)}</ns2:idPlantilla>
            else ()
        }
        {
            if ($ConsultarPlantillaByProcesoRequest/ns1:id_proceso)
            then <ns2:idProceso>{fn:data($ConsultarPlantillaByProcesoRequest/ns1:id_proceso)}</ns2:idProceso>
            else ()
        }
        {
            if ($ConsultarPlantillaByProcesoRequest/ns1:evento)
            then <ns2:evento>{fn:data($ConsultarPlantillaByProcesoRequest/ns1:evento)}</ns2:evento>
            else ()
        }
        {
            if ($ConsultarPlantillaByProcesoRequest/ns1:idTarea)
            then <ns2:idTarea>{fn:data($ConsultarPlantillaByProcesoRequest/ns1:idTarea)}</ns2:idTarea>
            else ()
        }
        {
            if ($ConsultarPlantillaByProcesoRequest/ns1:pDescripcionTCAPlantilla)
            then <ns2:pDescripcionTCAPlantilla>{fn:data($ConsultarPlantillaByProcesoRequest/ns1:pDescripcionTCAPlantilla)}</ns2:pDescripcionTCAPlantilla>
            else ()
        }
    </ns2:ConsultarPlantillaByProcesoInput>
};

local:func($ConsultarPlantillaByProcesoRequest)
