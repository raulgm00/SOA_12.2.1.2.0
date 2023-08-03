xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTreTermino_DB";
(:: import schema at "../XSD/ConsultarTreTermino_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarTreTerminoOutput as element() (:: schema-element(ns1:ConsultarTreTermino_DBOutputCollection) ::) external;

declare function local:func($ConsultarTreTerminoOutput as element() (:: schema-element(ns1:ConsultarTreTermino_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarTreTerminoResponse) ::) {
    <ns2:ConsultarTreTerminoResponse>

           {
           for $termino in $ConsultarTreTerminoOutput/ns1:ConsultarTreTermino_DBOutput
         return
        <ns2:Termino>
            <ter:idTermino>{fn:data($termino/ns1:ID)}</ter:idTermino>
            <ter:operacion>{fn:data($termino/ns1:ID_OPERACION)}</ter:operacion>
            <ter:tipoTermino>
                <ter:idCatTermino>{fn:data($termino/ns1:ID_TCA_TERMINO)}</ter:idCatTermino>
            </ter:tipoTermino>
        </ns2:Termino>
        }
        <ns2:Respuesta>
        <res:result>OK</res:result>
        {
          if(fn:empty(fn:data($ConsultarTreTerminoOutput/ns1:ConsultarTreTermino_DBOutput[1]/ns1:ID)))
            then(<res:message>No se encontraron registros</res:message>)
              else(<res:message>Registro Encontrado</res:message>)
        }</ns2:Respuesta>
    </ns2:ConsultarTreTerminoResponse>
};

local:func($ConsultarTreTerminoOutput)
