
	-- ##****************************************************************************************************************************##
	-- 20170616 Se ejecuta la modificación a la vista VCA_TASAS_FLEXCUBE, en base a la incidencia FNXII-5937, ya se ejecuto en DEV y QA, falta en prePROD y PROD
	-- ##****************************************************************************************************************************##


	CREATE OR REPLACE  VIEW VCA_TASAS_FLEXCUBE  AS 
		SELECT  VTH.CODIGO,
				VTH.DESCRIPCION,
				VTH.CODIGO_MONEDA,
				VTH.FECHA_EFECTIVA,
				VTH.VALOR_ACTUAL
		FROM (SELECT VTH.CODIGO,
					 VT.Descripcion,
					 VTH.CODIGO_MONEDA,
					 VTH.FECHA_EFECTIVA,
					 VTH.VALOR_ACTUAL
			  FROM MIDDLE.FC_V_TASA_HISTORICA VTH
			  LEFT JOIN MIDDLE.FC_V_TASA VT
			  ON VT.Codigo = VTH.Codigo AND VT.Codigo_Moneda = VTH.Codigo_Moneda) VTH
		WHERE VTH.Codigo IN ('1002', '1104')
		ORDER BY 1, 2;
		
	/
