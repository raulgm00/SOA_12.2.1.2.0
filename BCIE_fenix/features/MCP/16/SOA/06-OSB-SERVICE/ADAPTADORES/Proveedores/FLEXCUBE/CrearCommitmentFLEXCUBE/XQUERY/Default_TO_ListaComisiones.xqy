xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)

declare variable $baseCalculo as xs:string external;
declare variable $fechaInicioRevision as xs:string external;
declare variable $tipoComision as xs:string external;
declare variable $codigoTasa as xs:string external;
declare variable $valorComision as xs:string external;
declare variable $frecuencia as xs:string external;
declare variable $tasaMaxima as xs:string external;
declare variable $tasaMinima as xs:string external;
declare variable $tipoFrecuenciaRevision as xs:string external;
declare variable $tipoTasa as xs:string external;
declare variable $tipoFrecuencia as xs:string external;
declare variable $spread as xs:string external;
declare variable $spreadMora as xs:string external;
declare variable $frecuenciaRevision as xs:string external;
declare variable $fechaInicio as xs:string external;
declare variable $monto as xs:string external;

declare function local:func($baseCalculo as xs:string, 
                            $fechaInicioRevision as xs:string, 
                            $tipoComision as xs:string, 
                            $codigoTasa as xs:string, 
                            $valorComision as xs:string, 
                            $frecuencia as xs:string, 
                            $tasaMaxima as xs:string, 
                            $tasaMinima as xs:string, 
                            $tipoFrecuenciaRevision as xs:string, 
                            $tipoTasa as xs:string, 
                            $tipoFrecuencia as xs:string, 
                            $spread as xs:string, 
                            $spreadMora as xs:string, 
                            $frecuenciaRevision as xs:string, 
                            $fechaInicio as xs:string, 
                            $monto as xs:string) 
                            as element() (:: element(*, ns1:ComisionestypeUserArray) ::) {
    <ns1:ComisionestypeUserArray>
        <ns1:ComisionestypeUser>
            <ns1:baseCalculo>{fn:data($baseCalculo)}</ns1:baseCalculo>
            <ns1:fechaInicioRevision>{fn:data($fechaInicioRevision)}</ns1:fechaInicioRevision>
            <ns1:tipoComision>{fn:data($tipoComision)}</ns1:tipoComision>
            <ns1:codigoTasa>{fn:data($codigoTasa)}</ns1:codigoTasa>
            <ns1:valorComision>{fn:data($valorComision)}</ns1:valorComision>
            <ns1:frecuencia>{fn:data($frecuencia)}</ns1:frecuencia>
            <ns1:tasaMaxima>{fn:data($tasaMaxima)}</ns1:tasaMaxima>
            <ns1:tasaMinima>{fn:data($tasaMinima)}</ns1:tasaMinima>
            <ns1:tipoFrecuenciaRevision>{fn:data($tipoFrecuenciaRevision)}</ns1:tipoFrecuenciaRevision>
            <ns1:tipoTasa>{fn:data($tipoTasa)}</ns1:tipoTasa>
            <ns1:tipoFrecuencia>{fn:data($tipoFrecuencia)}</ns1:tipoFrecuencia>
            <ns1:spread>{fn:data($spread)}</ns1:spread>
            <ns1:spreadMora>{fn:data($spreadMora)}</ns1:spreadMora>
            <ns1:frecuenciaRevision>{fn:data($frecuenciaRevision)}</ns1:frecuenciaRevision>
            <ns1:fechaInicio>{fn:data($fechaInicio)}</ns1:fechaInicio>
            <ns1:monto>{fn:data($monto)}</ns1:monto>
        </ns1:ComisionestypeUser>
    </ns1:ComisionestypeUserArray>
};

local:func($baseCalculo, $fechaInicioRevision, $tipoComision, $codigoTasa, $valorComision, $frecuencia, $tasaMaxima, $tasaMinima, $tipoFrecuenciaRevision, $tipoTasa, $tipoFrecuencia, $spread, $spreadMora, $frecuenciaRevision, $fechaInicio, $monto)
