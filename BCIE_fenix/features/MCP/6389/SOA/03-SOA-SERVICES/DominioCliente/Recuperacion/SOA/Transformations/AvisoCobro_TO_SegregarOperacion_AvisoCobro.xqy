xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ges1 = "http://www.bcie.org/GestionCobroBO";

declare variable $AvisoCobro as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::) external;

declare function local:funcAvisocobro_to_segregaroperacion_avisocobro($AvisoCobro as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::)) as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::) {
    <ges:ObtenerAvisoCobroResponse>
        <ges:AvisoCobro>
          {
          for $idOperacion at $pos in fn:distinct-values($AvisoCobro/ges:AvisoCobro/ges1:aviso/ges1:avisoOperacion/ope:idOperacion)
          let $aviso:= $AvisoCobro/ges:AvisoCobro/ges1:aviso
          return
            <ges1:aviso>
                <cli:idCliente>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:idCliente)}</cli:idCliente>
                {
                    if ($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:idFacturador)
                    then <cli:idFacturador>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:idFacturador)}</cli:idFacturador>
                    else ()
                }
                {
                    if ($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:razonSocial)
                    then <cli:razonSocial>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:razonSocial)}</cli:razonSocial>
                    else ()
                }
                {
                    if ($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:abreviatura)
                    then <cli:abreviatura>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:abreviatura)}</cli:abreviatura>
                    else ()
                }
                <cli:pais>
                    <cat:Id></cat:Id>
                    {
                        if ($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:pais/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:pais/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:pais/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                </cli:pais>
                {
                    if ($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:responsableCliente)
                    then <cli:responsableCliente>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:responsableCliente)}</cli:responsableCliente>
                    else ()
                }
                <ges1:idEjecucionLote>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/ges1:idEjecucionLote)}</ges1:idEjecucionLote>
                <ges1:emision>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/ges1:emision)}</ges1:emision>
                {
                for $avisoOperacion in $aviso/ges1:avisoOperacion[ope:idOperacion = $idOperacion]
                return
                <ges1:avisoOperacion>
                    <ope:idOperacion>{fn:data($avisoOperacion/ope:idOperacion)}</ope:idOperacion>
                    <ope:nombre>{fn:data($avisoOperacion/ope:nombre)}</ope:nombre>
                    <ges1:Moneda>
                        <cat:Descripcion>{fn:data($avisoOperacion/ges1:Moneda/cat:Descripcion)}</cat:Descripcion>
                    </ges1:Moneda>
                    {for $infoPago in $avisoOperacion/ges1:informacionPago
                    return
                    <ges1:informacionPago>
                        <ges1:fechaPago>{fn:data($infoPago/ges1:fechaPago)}</ges1:fechaPago>
                        {for $detallePago in $infoPago/ges1:detallePago
                        return
                        <ges1:detallePago>
                        {$detallePago/*}
                        </ges1:detallePago>
                        }
                    </ges1:informacionPago>
                    }
                </ges1:avisoOperacion>
                }
            </ges1:aviso>
          }   
        </ges:AvisoCobro>
    </ges:ObtenerAvisoCobroResponse>
};

local:funcAvisocobro_to_segregaroperacion_avisocobro($AvisoCobro)
