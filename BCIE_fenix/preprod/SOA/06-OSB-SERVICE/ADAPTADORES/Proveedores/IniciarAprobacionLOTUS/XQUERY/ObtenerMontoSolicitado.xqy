xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $outConsultarOperacion as element() (:: schema-element(ns1:ConsultarOperacionResponse) ::) external;

declare function local:func($outConsultarOperacion as element() (:: schema-element(ns1:ConsultarOperacionResponse) ::)
                            ) as element() {
    <montoSolicitado>{
        for $montoOperacion in $outConsultarOperacion/ns1:Operacion/ope:montoOperacion/ope:montoOperacion            
        where $montoOperacion/ope:Descripcion = 'SOLICITADO'
            return               
                fn:data($montoOperacion/ope:monto)
    }
    </montoSolicitado>
};

local:func($outConsultarOperacion)
