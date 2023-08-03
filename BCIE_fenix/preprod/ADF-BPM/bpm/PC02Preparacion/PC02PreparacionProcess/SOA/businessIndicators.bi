<?xml version="1.0" encoding="UTF-8"?>
<ns0:businessIndicators xmlns:ns0="http://xmlns.oracle.com/bpm/analytics/businessIndicator" fileVersion="20130625">
   <ns0:businessIndicator name="totalDesperdicioPC02">
      <ns0:id>fe0ddb1b-decf-4930-85bd-6cf53d80e0f4</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="PC02PreparacionProcess" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter3')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="totalReprocesosPC02">
      <ns0:id>e90686b9-40c5-4583-b7a4-2b08eff5be7c</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="PC02PreparacionProcess" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter4')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="totalReprocesos">
      <ns0:id>56e3aba8-d4c4-4ade-8618-af78ad7f6ac7</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="PC02PreparacionProcess" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter1')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="totalDesperdicio">
      <ns0:id>9cbb4753-7196-452c-aef3-b6ff9028bf81</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="PC02PreparacionProcess" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter2')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idOperacion">
      <ns0:id>e71c7e5e-13dd-4545-821f-1017614061df</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PC02PreparacionProcess" componentType="BPMN">bpmn:getDataObject('idOperacion')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idOperacionPC02">
      <ns0:id>8cb4d3f8-e236-40bb-b418-a6c2b1db70d1</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PC02PreparacionProcess" componentType="BPMN">bpmn:getDataObject('idOperacionPC02')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idEstadoOperacion">
      <ns0:id>b92dc4ff-9ea6-47fb-bae7-a4b98ff47438</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PC02PreparacionProcess" componentType="BPMN">bpmn:getDataObject('idEstadoOperacion')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="raroc">
      <ns0:id>67485fc6-f323-4edd-8c32-3836a9afe80f</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Decimal</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PC02PreparacionProcess" componentType="BPMN">bpmn:getDataObject('raroc')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="nombreOperacionPC02">
      <ns0:id>7b6a9376-915a-4919-b169-bcf88b8c741f</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PC02PreparacionProcess" componentType="BPMN">bpmn:getDataObject('nombreOperacionPC02')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idEstadoOperacionPC02">
      <ns0:id>2d48d74f-d49d-4e6d-92f8-016c04f9cda1</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PC02PreparacionProcess" componentType="BPMN">bpmn:getDataObject('idEstadoOperacionPC02')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="rarocPC02">
      <ns0:id>bbcf09e7-f945-48b2-b34d-77d175ee1aca</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Decimal</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PC02PreparacionProcess" componentType="BPMN">bpmn:getDataObject('rarocPC02')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="accionTareaPC02">
      <ns0:id>20a42332-5e77-4274-9046-e651bc79f853</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PC02PreparacionProcess" componentType="BPMN">bpmn:getDataObject('accionTareaPC02')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="nombreOperacion">
      <ns0:id>26110441-39eb-4020-95c5-1793c3771db0</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PC02PreparacionProcess" componentType="BPMN">bpmn:getDataObject('nombreOperacion')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="accionTarea">
      <ns0:id>252ef5da-1051-4d7e-95d6-8c7cc6db60fc</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="PC02PreparacionProcess" componentType="BPMN">bpmn:getDataObject('accionTarea')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
</ns0:businessIndicators>
