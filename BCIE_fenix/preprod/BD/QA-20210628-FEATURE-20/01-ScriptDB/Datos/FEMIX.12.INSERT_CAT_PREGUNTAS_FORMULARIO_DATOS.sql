DECLARE
idPreguntaVersion       INT;
idTipoPregunta          INT;
num_orden               INT;
idResponsable           INT;
total_pregunta          INT;
total_productos         INT;
type lista_preguntas IS VARRAY(15) OF VARCHAR2(200); 
preguntas  lista_preguntas;
type lista_productos IS VARRAY(5) OF VARCHAR2(100); 
productos  lista_productos;
idProducto              INT;
idPregunta              INT;
id_plantilla_correo     INT;
id_PREGUNTA_OPCION      INT;
idversioncuestionario    INT;
BEGIN
id_PREGUNTA_OPCION :=0;
id_plantilla_correo :=0;
idPregunta := 0;
idPreguntaVersion := 0;
idTipoPregunta := 0;
idResponsable := 0;
idProducto := 0;
idversioncuestionario :=0;
num_orden := 1;

preguntas := lista_preguntas ('Tasa de Inter�s m�xima (techo).'
    ,'Tasa de Inter�s M�nima (piso).'
    ,'Tasa de Inter�s dentro de un rango (collar).'
    ,'Su principal es variable.'
    ,'Opci�n que permite acelerar el repago del principal por un monto distinto al saldo vigente.'
    ,'Opci�n que permite extender el plazo de la operaci�n (a precios fuera de mercado).'
    ,'Contrato puede cancelarse en una moneda diferente a la original (en una tasa diferente a la de mercado).'
    ,'La tasa de inter�s hace referencia a acciones, �ndices accionarios, commodities, �ndices de desastres, producto interno bruto.'
    ,'Tasa de Inter�s est� referenciada a inflaci�n, riesgo de cr�dito del emisor u otro emisor, referenciada a la tasa de pol�tica monetaria o ajustada por variaciones en el tipo de cambio.'
    ,'Derecho sobre acciones (conversi�n de deuda a acciones).'
    ,'Cambio de Control accionario: el evento que suceda un cambio de control que cumpla con los par�metros delineados por el BCIE en el contrato.'
    ,'Clausula especial por fusi�n, disoluci�n, liquidaci�n o consolidaci�n con otra entidad.'
    ,'Prestamistas m�s favorecidos protecci�n crediticia'
    ,'�Exposici�n contingente o cobertura adicional?'
    ,'�Otra condici�n que no sea usual y que debiera ser evaluada?');
    
productos := lista_productos('Pr�stamo Tradicional'
    ,'Pr�stamo Estructurado'
    ,'L�nea Global de Cr�dito a IFI'
    ,'L�nea de Cr�dito para Entidades P�blicas Descentralizadas'
    ,'L�nea para Apoyar la Gesti�n de Liquidez de los Bancos Centrales de los Pa�ses Fundadores del BCIE');

total_pregunta := preguntas.count;
total_productos := productos.count;
    
    --Obtiene ID del tipo de Pregunta y el ID rol que responde las preguntas
    select id into idTipoPregunta from FENIX.TCO_TIPO_PREGUNTA where descripcion = 'Selecci�n �nica';
    select id into idResponsable from FENIX.TCA_ROL_BPM where descripcion_corta='FENIX_REPRESENTANTES';
    
    select id into idversioncuestionario from FENIX.TCA_VERSION_CUESTIONARIO WHERE descripcion_corta='FDI VERSION 1' AND Ban_Estatus=1;
    
    SELECT id INTO id_plantilla_correo FROM FENIX.TCA_PLANTILLA_CORREO WHERE descripcion = 'PLANTILLA_FORMULARIO_IMPLICITO' AND Ban_Estatus=1;

    
    FOR i in 1 .. total_pregunta LOOP 
        INSERT INTO fenix.CAT_PREGUNTAS (PREGUNTA, STATUS, TIPO,ID_RESPONSABLE, ID_TIPO_PREGUNTA, NUM_ORDEN, FECHA_REGISTRO) VALUES
            (preguntas(i)
            ,1
            , NULL
            , idResponsable
            , idTipoPregunta
            , num_orden, CURRENT_DATE);
        
        SELECT CAT_PREGUNTAS_SEQ.CURRVAL into idPregunta FROM DUAL;
        
        SELECT FENIX.TRE_PREGUNTA_VERSION_SEQ.NEXTVAL INTO idPreguntaVersion FROM DUAL;
       
        INSERT INTO FENIX.TRE_PREGUNTA_VERSION(id, id_pregunta, id_version, fec_creacion, ban_estatus) values
            (idPreguntaVersion, idPregunta, idversioncuestionario, CURRENT_DATE, 1);
                    
        INSERT INTO FENIX.TRE_PREGUNTA_OPCION (id_pregunta, desc_opcion, fec_creacion, ban_estatus) VALUES (
            idPregunta,'Si', CURRENT_DATE, 1);
        
        SELECT TRE_PREGUNTA_OPCION_SEQ.CURRVAL into id_PREGUNTA_OPCION FROM DUAL;

        INSERT INTO FENIX.TCO_PREGUNTA_OPCION_NOTIFICACION(ID_TRE_PREGUNTA_OPCION,ID_TCA_PLANTILLA_CORREO) VALUES(
            id_PREGUNTA_OPCION,id_plantilla_correo);
            
        INSERT INTO FENIX.TRE_PREGUNTA_OPCION (id_pregunta, desc_opcion, fec_creacion, ban_estatus) VALUES (
            idPregunta,'No', CURRENT_DATE, 1);
     
        FOR j in 1.. total_productos LOOP 
            SELECT ID INTO idProducto FROM FENIX.CAT_PRODUCTO WHERE DESCRIPCION = productos(j);
            
            INSERT INTO FENIX.PREGUNTA_PRODUCTO (ID_PREGUNTA, ID_PRODUCTO, fec_creacion, ban_estatus) values 
                (idPregunta, idProducto, CURRENT_DATE, 1);
        END LOOP;  
   END LOOP;

    COMMIT;
EXCEPTION
   WHEN OTHERS THEN
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_backtrace); 
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_call_stack); 
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_stack); 
   ROLLBACK;
END;