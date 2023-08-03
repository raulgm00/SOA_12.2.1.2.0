xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ges1 = "http://www.bcie.org/GestionCobroBO";

declare variable $outInvokeConsultarGestionCobro.response as element() (:: schema-element(ges:ConsultarGestionCobroResponse) ::) external;

declare function local:funcConsultargestioncobro_response($outInvokeConsultarGestionCobro.response as element() (:: schema-element(ges:ConsultarGestionCobroResponse) ::)) as element() (:: schema-element(ges:ConsultarGestionCobroResponse) ::) {
    <ges:ConsultarGestionCobroResponse>
        {
            for $DetalleLote in $outInvokeConsultarGestionCobro.response/ges:DetalleLote
            where $DetalleLote/ges1:tipoLote = 'GESTION'
            return 
            <ges:DetalleLote>
                <ges1:id>{fn:data($DetalleLote/ges1:id)}</ges1:id>
                <ges1:idLote>{fn:data($DetalleLote/ges1:idLote)}</ges1:idLote>
                <ges1:idCliente>{fn:data($DetalleLote/ges1:idCliente)}</ges1:idCliente>
                <ges1:responsableCliente>{fn:data($DetalleLote/ges1:responsableCliente)}</ges1:responsableCliente>
                <ges1:Estado>{fn:data($DetalleLote/ges1:Estado)}</ges1:Estado>
                <ges1:Mensaje_Error>
                    <cat:Id>{fn:data($DetalleLote/ges1:Mensaje_Error/cat:Id)}</cat:Id>
                </ges1:Mensaje_Error>
                <ges1:fechaRegistro>{fn:data($DetalleLote/ges1:fechaRegistro)}</ges1:fechaRegistro>
                <ges1:Estatus>{fn:data($DetalleLote/ges1:Estatus)}</ges1:Estatus>
                <ges1:tipoLote>{fn:data($DetalleLote/ges1:tipoLote)}</ges1:tipoLote>
            </ges:DetalleLote>
        }
    </ges:ConsultarGestionCobroResponse>
};

local:funcConsultargestioncobro_response($outInvokeConsultarGestionCobro.response)
