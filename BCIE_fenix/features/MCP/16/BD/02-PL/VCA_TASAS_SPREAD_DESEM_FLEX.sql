


	CREATE OR REPLACE VIEW VCA_TASAS_SPREAD_DESEM_FLEX
	AS
	SELECT DESCRIPCION || '(' || CODIGO  || ')' AS DESCRIPCION_TASA,
		   CODIGO,
		   DESCRIPCION,
		   CODIGO_MONEDA,
		   CODIGO_BRANCH,
		   VALOR_ACTUAL,
		   FECHA_EFECTIVA,
		   AUTORIZADO,
		   REGISTRO
	  FROM middle.fc_v_Tasa, middle.fc_v_udtm_lov 
	 WHERE REGISTRO = 'O'      
	   AND AUTORIZADO = 'A'  
	   AND CODIGO = LOV 
	 ORDER BY 1;