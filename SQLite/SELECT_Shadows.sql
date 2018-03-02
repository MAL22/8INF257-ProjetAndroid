SELECT s.FakeName as `Fake name`,s.RealName as `Real name`,s.History as `History`,a.Name as `Arcana`,p.Name AS `Personality` ,w.Nom as `Weakness`,r.Nom as `Resistance`

FROM Shadows AS 's',Arcana AS 'a',Personalities AS 'p',(select d.Nom from DamageType as 'd',Weaknesses as "w", Shadows as 's' where w.ID_Shadow = s.ID and w.ID_DamageType = d.ID) as 'w',(select d.Nom from DamageType as 'd',Resistances as "r", Shadows as 's' where r.ID_Shadow = s.ID and r.ID_DamageType = d.ID) as 'r'
WHERE s.Arcana_ID = a.ID 
	AND s.Personality_ID = p.ID 
	/*and w.ID_Shadow = s.ID 
	and w.ID_DamageType = d.ID
	and r.ID_Shadow = s.ID 
	and r.ID_DamageType = d.ID*/