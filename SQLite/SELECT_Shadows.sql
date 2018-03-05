SELECT sh.FakeName as `Fake name`,sh.RealName as `Real name`,sh.History as `History`,a.Name as `Arcana`,p.Name AS `Personality` ,
	st.Strength as `Strength`,st.Magic as `Magic`,st.Endurance as `Endurance`,st.Agility as `Agility`,st.Luck as `Luck`
	
FROM Shadows AS 'sh',Arcana AS 'a',Personalities AS 'p',Stats as "st"
	
WHERE sh.Stats_ID = st.ID
	and sh.Arcana_ID = a.ID
	and sh.Personality_ID = p.ID