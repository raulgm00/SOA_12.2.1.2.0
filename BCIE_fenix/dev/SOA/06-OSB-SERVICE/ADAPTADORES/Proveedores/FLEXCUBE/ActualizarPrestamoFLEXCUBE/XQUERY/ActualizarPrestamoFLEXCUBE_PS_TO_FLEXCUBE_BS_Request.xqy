xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace flex="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace typ="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";

declare namespace xsi="http://www.w3.org/2001/XMLSchema-instance";

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $ActualizarPrestamoFLEXCUBERequest as element() (:: schema-element(ns1:ActualizarPrestamoFLEXCUBERequest) ::) external;

declare function local:func($ActualizarPrestamoFLEXCUBERequest as element() (:: schema-element(ns1:ActualizarPrestamoFLEXCUBERequest) ::)) as element() (:: element(flex:actualizarContrato1) ::) {
        <flex:actualizarContrato1>
         <numeroLineaCredito>{fn:data($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:referencia)}</numeroLineaCredito>
         <fechaValor>{fn:data($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:fechaEfectiva)}</fechaValor>
         <moneda></moneda>
         <montoDelta></montoDelta>
         <fechaMadurez/>
         <listacomisiones>
            <typ:ComisionestypeUser>
               <typ:baseCalculo></typ:baseCalculo>
               <typ:fechaInicioRevision></typ:fechaInicioRevision>
               <typ:tipoComision>{fn:data($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:producto/des:Componente[des:TipoComponente/cat:codigoExterno = 'INTERES']/cat:codigoExterno)}</typ:tipoComision>
               {
               if($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno = 'F')then
               <typ:codigoTasa>{fn:data($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:tasaReferencia/cat:Id)}</typ:codigoTasa>
               else(<typ:codigoTasa xsi:nil="true"/>)
               }
               {
               if($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno = 'X')then
               <typ:valorComision>{fn:data($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:fija/des:valor)}</typ:valorComision>
               else(<typ:valorComision xsi:nil="true"/>)
               }
               <typ:frecuencia></typ:frecuencia>
               <typ:tasaMaxima></typ:tasaMaxima>
               <typ:tasaMinima></typ:tasaMinima>
               <typ:tipoFrecuenciaRevision></typ:tipoFrecuenciaRevision>
               <typ:tipoTasa>{fn:data($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)}</typ:tipoTasa>
               <typ:tipoFrecuencia></typ:tipoFrecuencia>
               {
               if($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno = 'F')then
               <typ:spread>{fn:data($ActualizarPrestamoFLEXCUBERequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:spread/des:valor)}</typ:spread>
               else(<typ:spread xsi:nil="true"/>)}
               <typ:spreadMora></typ:spreadMora>
               <typ:frecuenciaRevision></typ:frecuenciaRevision>
               <typ:fechaInicio></typ:fechaInicio>
               <typ:monto xsi:nil="true"/>
            </typ:ComisionestypeUser>
         </listacomisiones>
      </flex:actualizarContrato1>
};

local:func($ActualizarPrestamoFLEXCUBERequest)