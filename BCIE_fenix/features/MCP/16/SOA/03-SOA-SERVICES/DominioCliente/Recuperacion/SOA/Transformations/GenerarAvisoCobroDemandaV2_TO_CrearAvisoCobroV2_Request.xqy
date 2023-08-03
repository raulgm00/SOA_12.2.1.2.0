xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace ges1 = "http://www.bcie.org/GestionCobroBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $inputVariable.GenerarAvisoCobroDemandaV2Request as element() (:: schema-element(ges:GenerarAvisoCobroDemandaV2Request) ::) external;

declare function local:funcGeneraravisocobrodemandav2_to_crearavisocobrov2_request($inputVariable.GenerarAvisoCobroDemandaV2Request as element() (:: schema-element(ges:GenerarAvisoCobroDemandaV2Request) ::)) as element() (:: schema-element(ges:CrearAvisoCobroV2Request) ::) {
    <ges:CrearAvisoCobroV2Request>
        <ges:fechaInicio>{fn:data($inputVariable.GenerarAvisoCobroDemandaV2Request/ges:parametrosAvisoCobro/ges1:fechaInicial)}</ges:fechaInicio>
        <ges:fechaFin>{fn:data($inputVariable.GenerarAvisoCobroDemandaV2Request/ges:parametrosAvisoCobro/ges1:fechaFinal)}</ges:fechaFin>
        {
            if($inputVariable.GenerarAvisoCobroDemandaV2Request/ges:parametrosAvisoCobro/ges1:cliente/cli:sector/cat:Descripcion != '')then
                if(fn:lower-case($inputVariable.GenerarAvisoCobroDemandaV2Request/ges:parametrosAvisoCobro/ges1:cliente/cli:sector/cat:Descripcion) = 'sector público')
                then 
                <ges:sectorInstitucional>1</ges:sectorInstitucional>
                else <ges:sectorInstitucional>2</ges:sectorInstitucional>
              else()
        }
        {
        if(fn:string($inputVariable.GenerarAvisoCobroDemandaV2Request/ges:parametrosAvisoCobro/ges1:fondo[1]/cat:Id) != '')then
            <ges:codigoFondo>
            {
              fn:string-join(for $fondos in $inputVariable.GenerarAvisoCobroDemandaV2Request/ges:parametrosAvisoCobro/ges1:fondo
              return
                fn:data(fn:string($fondos/cat:Id)),",")
            }
            </ges:codigoFondo>
        else()  
        }
        <ges:codigoCliente>{fn:data($inputVariable.GenerarAvisoCobroDemandaV2Request/ges:parametrosAvisoCobro/ges1:cliente/cli:idFacturador)}</ges:codigoCliente>
        {
            if(count($inputVariable.GenerarAvisoCobroDemandaV2Request/ges:parametrosAvisoCobro/ges1:operacion) > 0 )
            then
                <ges:operacion>
                {
                    fn:string-join($inputVariable.GenerarAvisoCobroDemandaV2Request/ges:parametrosAvisoCobro/ges1:operacion/ope:idOperacion/text(),",")
                }
                </ges:operacion>   
            else ()
        }        
        {
            if( count($inputVariable.GenerarAvisoCobroDemandaV2Request/ges:parametrosAvisoCobro/ges1:lineaCredito) > 0)
            then
                <ges:lineaCredito>
                {
                    fn:string-join($inputVariable.GenerarAvisoCobroDemandaV2Request/ges:parametrosAvisoCobro/ges1:lineaCredito/lin:NumeroLineaCredito/text(),",")
                }
                </ges:lineaCredito>
            else ()
        }
        {
            if($inputVariable.GenerarAvisoCobroDemandaV2Request/ges:parametrosAvisoCobro/ges1:tipoSaldos != 'TODOS')then
                <ges:vencido>
                {
                    if(fn:upper-case($inputVariable.GenerarAvisoCobroDemandaV2Request/ges:parametrosAvisoCobro/ges1:tipoSaldos) = 'VENCIDO')then 'S'
                    else('N')
                }
              </ges:vencido>
            else()
        }
    </ges:CrearAvisoCobroV2Request>
};

local:funcGeneraravisocobrodemandav2_to_crearavisocobrov2_request($inputVariable.GenerarAvisoCobroDemandaV2Request)
