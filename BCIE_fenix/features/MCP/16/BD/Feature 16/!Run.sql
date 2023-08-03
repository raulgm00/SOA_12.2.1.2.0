--El archivo ¡Run.sql debe contener un llamado a todos los otros archivos en el orden que deberían ser invocados
--Ejemplo
SET DEFINE OFF;

Prompt ./DDL/fenix.TCA_PRODUCTO_TAREA_CREATE_TABLEsql
       @ ./DDL/fenix.TCA_PRODUCTO_TAREA_CREATE_TABLE.sql

Prompt ./DDL/fenix.TCA_TAREA_CON_BTN_DIN_CREATE_TABLE.sql
     @ ./DDL/fenix.TCA_TAREA_CON_BTN_DIN_CREATE_TABLE.sql

Prompt ./Secuencias/fenix.TCA_TAREA_CON_BTN_DIN_SEQ.sql
       @ ./Secuencias/fenix.TCA_TAREA_CON_BTN_DIN_SEQ.sql
       
Prompt ./Triggers/fenix.TCA_TAREA_CON_BTN_DIN_TRG.sql
       @ ./ Triggers /fenix.TCA_TAREA_CON_BTN_DIN_TRG.sql


Prompt ./Datos/fenix.TCA_TAREA_CON_BTN_DIN_INSERT.sql
     @ ./Datos/fenix.TCA_TAREA_CON_BTN_DIN_INSERT.sql

Prompt ./Datos/fenix.TCA_PLANTILLA_CORREO_INSERT.sql
       @ ./Datos/fenix.TCA_PLANTILLA_CORREO_INSERT.sql
	   
Prompt ./Datos/fenix.TCO_TAREA_BPM_MENSAJE_INSERT.sql
       @ ./Datos/fenix.TCO_TAREA_BPM_MENSAJE_INSERT.sql

Prompt ./Datos/fenix.TRE_TAREA_BPM_MENSAJE_PRODUCTO_INSERT.sql
     @ ./Datos/fenix.TRE_TAREA_BPM_MENSAJE_PRODUCTO_INSERT.sql
       
Prompt ./Datos/fenix.TCA_TAREA_BPM_UPDATE.sql
       @ ./Datos/fenix.TCA_TAREA_BPM_UPDATE.sql

Prompt ./Datos/fenix.TCA_PRODUCTO_TAREA_INSERT.sql
     @ ./Datos/fenix.TCA_PRODUCTO_TAREA_INSERT.sql
       