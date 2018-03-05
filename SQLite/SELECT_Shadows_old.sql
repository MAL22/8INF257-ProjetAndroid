SELECT sh.FakeName as `Fake name`,sh.RealName as `Real name`,sh.History as `History`,a.Name as `Arcana`,p.Name AS `Personality` ,w.Nom as `Weakness`,r.Nom as `Resistance`,
	st.Strength as `Strength`,st.Magic as `Magic`,st.Endurance as `Endurance`,st.Agility as `Agility`,st.Luck as `Luck`,sk.Name as `Skill`,sk.Effect as `Effect`
	
FROM Shadows AS 'sh',Arcana AS 'a',Personalities AS 'p',Stats as "st",Skills as 'sk',
	(select d.Nom from DamageType as 'd',Weaknesses as "w", Shadows as 's' where w.ID_Shadow = s.ID and w.ID_DamageType = d.ID) as 'w',
	(select d.Nom from DamageType as 'd',Resistances as "r", Shadows as 's' where r.ID_Shadow = s.ID and r.ID_DamageType = d.ID) as 'r'
	
WHERE sh.Arcana_ID = a.ID 
	AND sh.Personality_ID = p.ID 
	AND sh.Stats_ID = st.ID
	AND sk.ID_Shadow = sh.ID

GROUP BY `Weakness`,`Resistance`,`Skill`