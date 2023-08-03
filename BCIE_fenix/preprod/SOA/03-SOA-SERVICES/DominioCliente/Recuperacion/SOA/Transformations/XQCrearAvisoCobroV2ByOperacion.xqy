xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace ges1 = "http://www.bcie.org/GestionCobroBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $outInvokeCrearAvisoCobroV2.CrearAvisoCobroV2Response as element() (:: schema-element(ges:CrearAvisoCobroV2Response) ::) external;
declare variable $inputVariable.GenerarAvisoCobroDemandaV2Request as element() (:: schema-element(ges:GenerarAvisoCobroDemandaV2Request) ::) external;
declare variable $avisoCounter as xs:unsignedInt external;
declare variable $operacionCounter as xs:unsignedInt external;

declare function local:funcXqcrearavisocobrov2byoperacion($outInvokeCrearAvisoCobroV2.CrearAvisoCobroV2Response as element() (:: schema-element(ges:CrearAvisoCobroV2Response) ::), 
                                                          $inputVariable.GenerarAvisoCobroDemandaV2Request as element() (:: schema-element(ges:GenerarAvisoCobroDemandaV2Request) ::),
                                                          $avisoCounter as xs:unsignedInt,
                                                          $operacionCounter as xs:unsignedInt
                                                          ) 
                                                          as element() (:: schema-element(ges:CrearAvisoCobroV2Response) ::) {
    <ges:CrearAvisoCobroV2Response>
        <ges:AvisoCobro>
            <ges1:aviso>
                <ges1:codigoCliente>{fn:data($outInvokeCrearAvisoCobroV2.CrearAvisoCobroV2Response/ges:AvisoCobro/ges1:aviso[$avisoCounter]/ges1:codigoCliente)}</ges1:codigoCliente>
                <ges1:nombreCompleto>{fn:data($outInvokeCrearAvisoCobroV2.CrearAvisoCobroV2Response/ges:AvisoCobro/ges1:aviso[$avisoCounter]/ges1:nombreCompleto)}</ges1:nombreCompleto>
                <ges1:pais>{fn:data($outInvokeCrearAvisoCobroV2.CrearAvisoCobroV2Response/ges:AvisoCobro/ges1:aviso[$avisoCounter]/ges1:pais)}</ges1:pais>
                <ges1:xml>
                {
                    copy $aviso := $outInvokeCrearAvisoCobroV2.CrearAvisoCobroV2Response/ges:AvisoCobro/ges1:aviso[$avisoCounter]/ges1:xml
                    modify 
                    (
                        insert node (<OPERACION_SELECCIONADA>{fn:data($inputVariable.GenerarAvisoCobroDemandaV2Request/ges:parametrosAvisoCobro/ges1:operacion[$operacionCounter]/ope:idOperacion)}</OPERACION_SELECCIONADA>) into $aviso/CLIENTES/PARAMETROS,
                        for $p in $aviso/CLIENTES/CLIENTE/LINEA[OPERACION != fn:data($inputVariable.GenerarAvisoCobroDemandaV2Request/ges:parametrosAvisoCobro/ges1:operacion[$operacionCounter]/ope:idOperacion)]
                        return delete node $p 
                    )
                    return $aviso/CLIENTES
                }
                </ges1:xml>
            </ges1:aviso>
        </ges:AvisoCobro>
        <ges:Resultado>
            <res:result>{fn:data($outInvokeCrearAvisoCobroV2.CrearAvisoCobroV2Response/ges:Resultado/res:result)}</res:result>
            <res:message>{fn:data($outInvokeCrearAvisoCobroV2.CrearAvisoCobroV2Response/ges:Resultado/res:message)}</res:message>
            {
                if ($outInvokeCrearAvisoCobroV2.CrearAvisoCobroV2Response/ges:Resultado/res:error)
                then 
                    <res:error>
                        <err:errorCode>{fn:data($outInvokeCrearAvisoCobroV2.CrearAvisoCobroV2Response/ges:Resultado/res:error/err:errorCode)}</err:errorCode>
                        <err:errorDescription>{fn:data($outInvokeCrearAvisoCobroV2.CrearAvisoCobroV2Response/ges:Resultado/res:error/err:errorDescription)}</err:errorDescription>
                        <err:errorType>{fn:data($outInvokeCrearAvisoCobroV2.CrearAvisoCobroV2Response/ges:Resultado/res:error/err:errorType)}</err:errorType>
                    </res:error>
                else ()
            }
        </ges:Resultado>
    </ges:CrearAvisoCobroV2Response>
};

local:funcXqcrearavisocobrov2byoperacion($outInvokeCrearAvisoCobroV2.CrearAvisoCobroV2Response, $inputVariable.GenerarAvisoCobroDemandaV2Request, $avisoCounter, $operacionCounter)
