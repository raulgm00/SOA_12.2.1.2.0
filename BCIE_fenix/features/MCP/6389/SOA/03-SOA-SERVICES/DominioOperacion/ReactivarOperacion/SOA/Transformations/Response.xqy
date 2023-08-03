xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ope="http://www.bcie.org/OperacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outputVariable.response as element() (:: schema-element(ope:ReactivarOperacionResponse) ::) external;

declare function local:funcResponse($outputVariable.response as element() (:: schema-element(ope:ReactivarOperacionResponse) ::)) as element() (:: schema-element(ope:ReactivarOperacionResponse) ::) {
    <ope:ReactivarOperacionResponse>
        {
            if ($outputVariable.response/ope:Operacion)
            then <ope:Operacion>{fn:data($outputVariable.response/ope:Operacion)}</ope:Operacion>
            else ()
        }
        {
            for $ListaEstatus in $outputVariable.response/ope:ListaEstatus
            return 
            <ope:ListaEstatus>
                <ope:idProceso>{fn:data($ListaEstatus/ope:idProceso)}</ope:idProceso>
                <ope:nombreProceso>{fn:data($ListaEstatus/ope:nombreProceso)}</ope:nombreProceso>
                <ope:estatus>{fn:data($ListaEstatus/ope:estatus)}</ope:estatus>
                <ope:mensajeEstatus>{fn:data($ListaEstatus/ope:mensajeEstatus)}</ope:mensajeEstatus>
            </ope:ListaEstatus>
        }
        {
            if ($outputVariable.response/ope:Resultado)
            then 
                <ope:Resultado>
                    <res:result>{fn:data($outputVariable.response/ope:Resultado/res:result)}</res:result>
                    <res:message>{fn:data($outputVariable.response/ope:Resultado/res:message)}</res:message>
                    {
                        if ($outputVariable.response/ope:Resultado/res:error)
                        then 
                            <res:error>
                                <err:errorCode>{fn:data($outputVariable.response/ope:Resultado/res:error/err:errorCode)}</err:errorCode>
                                <err:errorDescription>{fn:data($outputVariable.response/ope:Resultado/res:error/err:errorDescription)}</err:errorDescription>
                                <err:errorType>{fn:data($outputVariable.response/ope:Resultado/res:error/err:errorType)}</err:errorType>
                            </res:error>
                        else ()
                    }
                </ope:Resultado>
            else ()
        }
    </ope:ReactivarOperacionResponse>
};

local:funcResponse($outputVariable.response)
