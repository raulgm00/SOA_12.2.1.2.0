xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace mat="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)

declare namespace mat1 = "http://www.bcie.org/MatrizTCCBO";

declare variable $actualizarEstadoTCCRequest as element() (:: schema-element(mat:ActualizarEstadoTCCRequest) ::) external;

declare function local:func($actualizarEstadoTCCRequest as element() (:: schema-element(mat:ActualizarEstadoTCCRequest) ::)) as element() (:: schema-element(mat:ActualizarEstadoTCCRequest) ::) {
    <mat:ActualizarEstadoTCCRequest>
        {
            if ($actualizarEstadoTCCRequest/mat:ListaTCC)
            then 
                <mat:ListaTCC>
                    {
                        for $TCC in $actualizarEstadoTCCRequest/*:ListaTCC/*:TCC
                        return 
                        <mat1:TCC>
                            <mat1:id>{fn:data($TCC/*:id)}</mat1:id>
                            <mat1:tipo>{fn:data($TCC/*:tipo)}</mat1:tipo>
                            <mat1:estado>{fn:data($TCC/*:estado)}</mat1:estado>
                            <mat1:subEstado>{fn:data($TCC/*:subEstado)}</mat1:subEstado>
                            <mat1:banEstatus>{fn:data($TCC/*:banEstatus)}</mat1:banEstatus>
                        </mat1:TCC>
                    }
                </mat:ListaTCC>
            else ()
        }
    </mat:ActualizarEstadoTCCRequest>
};

local:func($actualizarEstadoTCCRequest)
