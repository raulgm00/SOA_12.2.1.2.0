xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)
declare namespace m="http://org/bcie/ws/middle/LIM/LIM.wsdl";
declare namespace ns1="http://org/bcie/ws/middle/LIM/LIM.wsdl/types/";
(:: import schema at "../../WSDL/LIM.wsdl" ::)
declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace xsi="http://www.w3.org/2001/XMLSchema-instance";

declare variable $LimOperacionResponse as element() (:: element(*, ns1:LimiteTypeUserArray) ::) external;

declare function local:func($LimOperacionResponse as element(m:validarLimitesOperacionResponse) (:: element(m:validarLimitesOperacionResponse) ::)) as element() (:: schema-element(ns2:ValidarLimitesLIMResponse) ::) {
    <ns2:ValidarLimitesLIMResponse>
        <ns2:Regla>
        {
        let $estado := if(count($LimOperacionResponse/listaLimites_out/ns1:LimiteTypeUser[empty(@xsi:nil) or @xsi:nil != 'true']) = count($LimOperacionResponse/listaLimites_out/ns1:LimiteTypeUser[ns1:valor="NA" or ns1:valor="TRUE"])) then 'CUMPLIDA'
                       else if (count($LimOperacionResponse/listaLimites_out/ns1:LimiteTypeUser[ns1:valor="ERROR"])> 0 ) then  "ERROR"
                       else if(count($LimOperacionResponse/listaLimites_out/ns1:LimiteTypeUser[ns1:valor="FALSE"]) > 0) then "NO_CUMPLIDA"
                       else "ERROR"
          return            
            <reg:Estado>
                <cat:Id></cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta>{$estado}</cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </reg:Estado>

        }
        {
        for $reglas in $LimOperacionResponse/listaLimites_out/ns1:LimiteTypeUser[empty(@xsi:nil) or @xsi:nil != 'true']
        return
            <reg:DetalleRegla>
                <atr:id>{fn:data($reglas/ns1:id)}</atr:id>
                <atr:descripcion>{fn:data($reglas/ns1:descripcion)}</atr:descripcion>
                <atr:estatus>{fn:data($reglas/ns1:valor)}</atr:estatus>
                <atr:esPorcentaje>{fn:data($reglas/ns1:esPorcentaje)}</atr:esPorcentaje>
                <atr:difValor>{fn:data($reglas/ns1:difValor)}</atr:difValor>
                <atr:difPorcentaje>{fn:data($reglas/ns1:difPorcentaje)}</atr:difPorcentaje>
            </reg:DetalleRegla>
        }
        </ns2:Regla>
        {
        let $estado := if(count($LimOperacionResponse/listaLimites_out/ns1:LimiteTypeUser[empty(@xsi:nil) or @xsi:nil != 'true']) = count($LimOperacionResponse/listaLimites_out/ns1:LimiteTypeUser[ns1:valor="NA" or ns1:valor="TRUE"])) then 'CUMPLIDA'
                       else if (count($LimOperacionResponse/listaLimites_out/ns1:LimiteTypeUser[ns1:valor="ERROR"])> 0 ) then  "ERROR"
                       else if(count($LimOperacionResponse/listaLimites_out/ns1:LimiteTypeUser[ns1:valor="FALSE"]) > 0) then "NO_CUMPLIDA"
                       else "ERROR"
        return                
        if(string($LimOperacionResponse/codigoResultado_out) != '0') then 
          <ns2:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($LimOperacionResponse/mensaje_out)}</res:message>
          </ns2:Resultado>
        else if($estado = "ERROR") then 
          <ns2:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($LimOperacionResponse/listaLimites_out/ns1:LimiteTypeUser[ns1:valor="ERROR"]/ns1:descripcion)}</res:message>
          </ns2:Resultado>
        else 
          <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{if($estado = "NO_CUMPLIDA") then data($LimOperacionResponse/listaLimites_out/ns1:LimiteTypeUser[ns1:valor="FALSE"]/ns1:descripcion) else "Se realizo la operacion con exito"}</res:message>
          </ns2:Resultado>
        }
         
    </ns2:ValidarLimitesLIMResponse>
};

local:func($LimOperacionResponse)