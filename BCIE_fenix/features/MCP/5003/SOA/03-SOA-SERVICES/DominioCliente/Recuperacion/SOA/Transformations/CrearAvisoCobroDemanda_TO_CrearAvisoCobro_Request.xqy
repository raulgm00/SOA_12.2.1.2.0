xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace ges1 = "http://www.bcie.org/GestionCobroBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $inputVariable.GenerarAvisoCobroDemandaRequest as element() (:: schema-element(ges:GenerarAvisoCobroDemandaRequest) ::) external;

declare function local:funcCrearavisocobrodemanda_to_crearavisocobro_request($inputVariable.GenerarAvisoCobroDemandaRequest as element() (:: schema-element(ges:GenerarAvisoCobroDemandaRequest) ::)) as element() (:: schema-element(ges:CrearAvisoCobroDemandaRequest) ::) {
    <ges:CrearAvisoCobroDemandaRequest>
        <ges:Aviso>
            <ges1:fechaInicio>{fn:data($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:fechaInicial)}</ges1:fechaInicio>
            <ges1:fechaFin>{fn:data($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:fechaFinal)}</ges1:fechaFin>
            <ges1:esPublico>{
            if($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:cliente/cli:sector/cat:Descripcion != '')then
                if(fn:lower-case($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:cliente/cli:sector/cat:Descripcion) = 'sector público')then 'S'
                else('N')
              else('')
            }</ges1:esPublico>
            <ges1:tipoAviso>{ 
            if($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:tipoGeneracion = 'DETALLE')then 'D'
             else
               if($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:tipoGeneracion = 'CARATULA')then 'C'
             else
                if($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:tipoGeneracion = 'AMBAS')then 'A'
               else('A')
             }</ges1:tipoAviso>
            <ges1:linea>TD</ges1:linea>
            <ges1:cliente>{fn:data($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:cliente/cli:idFacturador)}</ges1:cliente>
            <ges1:moneda>TD</ges1:moneda>
            <ges1:pais>{fn:data($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:cliente/cli:pais/cat:codigoExterno)}</ges1:pais>
            <ges1:sector></ges1:sector>
            <ges1:periodicidad>{
            if($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:periodicidad = 'TODOS')then 'A' 
            else 
              if(fn:upper-case($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:periodicidad) = 'MENSUAL')then 'S'
              else('N')
            }</ges1:periodicidad>
            <ges1:tipoSaldo>{
            if($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:tipoSaldos != 'TODOS')then
              if(fn:upper-case($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:tipoSaldos) = 'VENCIDO')then 'V'
              else('N')
            else('A')
            }</ges1:tipoSaldo>
            <ges1:fondos>{
            if(fn:string($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:fondo[1]/cat:Id) != '')then
              fn:string-join(for $fondos in $inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:fondo
              return
                fn:data(fn:string($fondos/cat:Id)),",")
            else('TD')    
            }</ges1:fondos>
            <ges1:operacion>TD</ges1:operacion>
            <ges1:usuarioCreador>{fn:data($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:loginUsuario)}</ges1:usuarioCreador>
        </ges:Aviso>
    </ges:CrearAvisoCobroDemandaRequest>
};

local:funcCrearavisocobrodemanda_to_crearavisocobro_request($inputVariable.GenerarAvisoCobroDemandaRequest)
