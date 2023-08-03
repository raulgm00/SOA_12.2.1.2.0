xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarParticipantesByIdNoObjecion";
(:: import schema at "../XSD/ConsultarParticipantesByIdNoObjecion.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ConsultarParticipantesByIdNoObjecionOutputCollection as element() (:: schema-element(ns1:ConsultarParticipantesByIdNoObjecionOutputCollection) ::) external;

declare function local:func($ConsultarParticipantesByIdNoObjecionOutputCollection as element() (:: schema-element(ns1:ConsultarParticipantesByIdNoObjecionOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarParticipantesByIdNoObjecionResponse) ::) {
    <ns2:ConsultarParticipantesByIdNoObjecionResponse>
    {
    for $rol in $ConsultarParticipantesByIdNoObjecionOutputCollection/ns1:ConsultarParticipantesByIdNoObjecionOutput
    return
        <ns2:rol>
            <cat:Id>{fn:data($rol/ns1:ID_TCA_ROL_BPM)}</cat:Id>
            <cat:Descripcion>{fn:data($rol/ns1:DESCRIPCION)}</cat:Descripcion>
            <cat:DescripcionCorta>{fn:data($rol/ns1:DESCRIPCION_CORTA)}</cat:DescripcionCorta>
            <cat:estatus>{fn:data($rol/ns1:ESTATUS)}</cat:estatus>
            <cat:codigoExterno>{fn:data($rol/ns1:ES_PARTICIPACION_OBLIGATORIA)}</cat:codigoExterno>
        </ns2:rol>
    }
    </ns2:ConsultarParticipantesByIdNoObjecionResponse>
};

local:func($ConsultarParticipantesByIdNoObjecionOutputCollection)
